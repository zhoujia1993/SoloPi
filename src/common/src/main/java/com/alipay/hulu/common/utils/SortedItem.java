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
package com.alipay.hulu.common.utils;

import android.support.annotation.NonNull;

/**
 * Created by qiaoruikai on 2019/11/8 11:40 PM.
 */
public class SortedItem<T> implements Comparable<SortedItem<T>>{
    private T item;
    private int priority;

    public SortedItem(T item, int priority) {
        if (item == null) {
            throw new NullPointerException("Item is null");
        }

        this.item = item;
        this.priority = priority;
    }

    @Override
    public int compareTo(@NonNull SortedItem<T> o) {
        return priority - o.priority;
    }

    public T getItem() {
        return item;
    }

    public int getPriority() {
        return priority;
    }
}
