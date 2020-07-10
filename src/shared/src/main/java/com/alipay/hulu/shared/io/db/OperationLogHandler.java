/*
 * Copyright (C) 2015-present, Ant Financial Services Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.hulu.shared.io.db;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Pair;

import com.alibaba.fastjson.JSON;
import com.alipay.hulu.common.injector.InjectorService;
import com.alipay.hulu.common.utils.LogUtil;
import com.alipay.hulu.shared.io.OperationStepProcessor;
import com.alipay.hulu.shared.io.bean.GeneralOperationLogBean;
import com.alipay.hulu.shared.io.bean.RecordCaseInfo;
import com.alipay.hulu.shared.io.util.OperationStepUtil;
import com.alipay.hulu.shared.node.tree.export.bean.OperationStep;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 操作入库
 */
public class OperationLogHandler implements OperationStepProcessor {
    /**
     * notify update local cases
     */
    public static final String NEED_REFRESH_LOCAL_CASES_LIST = "NEED_REFRESH_LOCAL_CASES_LIST";

    private static final String TAG = "OperationLogHandler";

    protected RecordCaseInfo caseInfo;

    protected GeneralOperationLogBean generalOperation;
    protected PriorityQueue<Pair<Integer, OperationStep>> generalPriorityQueue;

    private ExecutorService dbOperationExecutor = Executors.newSingleThreadExecutor();

    /**
     * 更新用例保存信息
     * @param caseInfo
     */
    private void updateCase(@NonNull RecordCaseInfo caseInfo) {
        this.caseInfo = caseInfo;
    }

    /**
     * 开始录制
     * @param caseInfo
     */
    @Override
    public void onStartRecord(RecordCaseInfo caseInfo) {
        updateCase(caseInfo);
        generalOperation = new GeneralOperationLogBean();
        if (generalPriorityQueue == null) {
            generalPriorityQueue = new PriorityQueue<>(11, new Comparator<Pair<Integer, OperationStep>>() {
                @Override
                public int compare(Pair<Integer, OperationStep> lhs, Pair<Integer, OperationStep> rhs) {
                    return lhs.first - rhs.first;
                }
            });
        } else {
            generalPriorityQueue.clear();
        }
    }

    /**
     * 录制操作步骤
     * @param stepIdx
     * @param operation
     */
    @Override
    public void onOperationStep(int stepIdx, OperationStep operation) {
        generalPriorityQueue.add(new Pair<>(stepIdx, operation));
    }

    /**
     * 停止录制
     */
    @Override
    public boolean onStopRecord(Context context) {
        List<OperationStep> realList = new ArrayList<>(generalPriorityQueue.size());
        while (!generalPriorityQueue.isEmpty()) {
            realList.add(generalPriorityQueue.poll().second);
        }
        generalOperation.setSteps(realList);

        // 仅当录制步骤数超过0才会入库
        if (realList.size() > 0) {
            // store step to file
            OperationStepUtil.beforeStore(generalOperation);

            String jsonString = JSON.toJSONString(generalOperation);
            caseInfo.setOperationLog(jsonString);
            saveCaseInDB();
        }

        return false;
    }



    private void saveCaseInDB() {
        if (caseInfo != null) {
            dbOperationExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    caseInfo.setGmtCreate(System.currentTimeMillis());
                    caseInfo.setGmtModify(System.currentTimeMillis());
                    long newId = GreenDaoManager.getInstance().getRecordCaseInfoDao().insert(caseInfo);

                    LogUtil.i(TAG, "Save case with id %d", newId);

                    // update case list
                    InjectorService.g().pushMessage(NEED_REFRESH_LOCAL_CASES_LIST);
                }
            });
        }
    }
}