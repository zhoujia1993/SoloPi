# <img src="assets/icon.png" width="48" /> SoloPi

[![GitHub stars](https://img.shields.io/github/stars/soloPi/SoloPi.svg)](https://github.com/soloPi/SoloPi/stargazers) [![GitHub license](https://img.shields.io/github/license/soloPi/SoloPi.svg)](https://github.com/soloPi/SoloPi/blob/master/LICENSE) [![GitHub release](https://img.shields.io/github/release/alipay/SoloPi.svg)](https://github.com/soloPi/SoloPi/releases) [![API](https://img.shields.io/badge/API-18%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=18) [![TesterHome](https://img.shields.io/badge/TTF-TesterHome-2955C5.svg)](https://testerhome.com/opensource_projects/82)

### English Version: [README](./README_eng.md)

> SoloPi是一个无线化、非侵入式的Android自动化工具，公测版拥有录制回放、性能测试、一机多控三项主要功能，能为测试开发人员节省宝贵时间。


### 功能特性

#### 录制回放

![录制回放](assets/replay.gif)

**[游戏录制回放使用视频](https://gw.alipayobjects.com/mdn/rms_e29b5f/afts/file/A*ym07T6nACDIAAAAAAAAAAABkARQnAQ)**

**[Native应用录制回放使用视频](https://gw.alipayobjects.com/os/basement_prod/3472d35c-bd57-4c82-8112-5dcde42fcb32.mov)**

SoloPi拥有录制操作的能力，用户只需要通过SoloPi执行用例步骤，SoloPi就能够将用户的操作记录下来，并且支持在各个设备上进行回放，这一切都能够在手机上独立完成。详见[录制回放](../../wikis/RecordCase)一篇。

SoloPi JSON 可以转化为其他自动化脚本，目前支持 Appium 和 Macaca ，可以前往 https://github.com/soloPi/SoloPi-Convertor 下载体验，欢迎Watch、Star、Fork 三连。

#### 性能工具

![性能工具](assets/performance.gif)

**[性能工具使用视频](https://gw.alipayobjects.com/os/basement_prod/1996390b-9ec8-4046-8ce8-459afa05d6c5.mov)**

**[响应耗时计算使用视频](https://gw.alipayobjects.com/os/basement_prod/4e82ca85-13fc-4de2-82ff-a9079344f5ef.mov)**

SoloPi能够记录待测应用的各项指标，你可以在悬浮窗中观察实时更新的数据，也可以对性能数据进行录制，在录制结束后查看图表；同时，SoloPi还支持性能加压，能够对CPU、内存与网络环境进行限制，复现应用在性能较差、网络环境不佳场景下的表现。

除了常规性能指标，SoloPi还提供了启动耗时计算工具，测试同学只需要点击两次按钮，就可以得到最贴近用户体验的启动耗时数据。同时，启动耗时计算工具还可以通过广播调用，可以非常方便的与UI自动化测试打通。详见[性能工具](../../wikis/Performance)一篇。

#### 一机多控

![一机多控](assets/oneToMany.gif)

**[一机多控使用视频](https://gw.alipayobjects.com/os/basement_prod/971b5467-3db0-4781-86e3-15b3907323f6.mov)**

SoloPi支持通过操作一台主机设备来控制多台从机设备，不需要在各个设备上分别进行重复冗杂的兼容性测试，能够极大提升兼容性测试的效率。详见[一机多控](../../wikis/OneToMany)一篇。



### 构建

> 开源部分包含录制回放与性能测试工具，一机多控功能由于稳定性原因暂时我们还没有开源，后续我们会继续推进。

#### 编译环境：

- macOS 10.14.3
- Android Studio 3.2
- **Gradle 4.4（Android Studio打开项目时会提示升级Gradle版本，建议不要进行升级）**
- **CMake 3.6..4111459（建议不要进行升级）**
- Ndk 15.2.4203819
- TargetApi 25
- MinimumApi 18
- **注意，构建时请将Android Studio的instant run功能关闭，否则打出来的安装包会无法使用**



### 快速开始

#### 下载配置Android SDK路径

   前往<https://developer.android.com/studio/releases/platform-tools#downloads>下载对应系统版本的SDK Platform Tools，解压好后在系统环境变量中添加环境变量`ANDROID_SDK=${sdk解压路径}`。你也可以参考网上的一些adb配置文档进行准备，比如 [https://sspai.com/post/40471](https://sspai.com/post/40471)

> 对于Windows 10以上，配置完环境变量后就可以在新开启的命令行中生效，对于较老版本的Windows系统，需要重启PC才能生效，对于Linux和macOS系统，请通过`echo $ANDROID_SDK`的方式检验是否生效。

#### 开启手机的开发者模式

   请打开手机设置应用，在`关于手机->软件信息`菜单下，连续点击`编译编号`一项7次，系统会提示`您已进入开发者模式`或者类似文案（不同的系统版本开发者模式开启方式略有不同）。

   回到设置应用根页面，可以看到`开发者选项`一项，开启`USB调试`功能。

#### 常见问题

   ** 如果使用过程中有遇到问题，可以先去[FAQ](../../wiki/FAQ)查找是否有解决方法 **

   对于VIVO设备，如果在开发者选项中包含“USB安全操作”，需要手动进行开启，否则录制回放与一机多控功能可能会无法正常操作

   对于小米设备，需要开启开发者选项中的`USB安装`与`USB调试（安全设置）`，否则录制回放与一机多控功能会无法正常操作；此外，还需要手动开启SoloPi应用权限中的`后台弹出界面`选项，否则无法正常使用

   对于魅族设备，如果待测应用属于支付、金融类应用，需要在手机管家中关闭安全支付功能，否则录制回放与一机多控功能可能会无法正常操作

   对于华为设备，需要开启开发者选项中的 `"仅充电"模式下允许ADB调试`，否则断开USB线后会提示adb调试中断

   对于OPPO设备，系统会10分钟自动断开USB调试，导致SoloPi不可用。如果想要保持调试稳定，需要将设备连接到电脑
   
   **如果设备有安全输入法，请前往`系统设置->输入法`关闭安全输入法，否则例如密码等一些输入框无法正常输入**

#### 连接设备并开启wifi调试端口

   请先连接设备到PC，通过下方命令检查设备是否与电脑建立好连接。

   连接时，您的设备上会提示`是否允许USB调试`，请选择确定。

   Windows：

```bash
   %ANDROID_SDK%\platform-tools\adb.exe devices
```

   macOS或Linux：

```shell
   $ANDROID_SDK/platform-tools/adb devices
```

   如果命令行显示出对应的设备号（如下图所示），则表示连接成功。

   ![建立连接](../../wiki/FirstUse/genConnection.png)

> 对于Windows系统，需要安装Android设备的驱动程序才可以连接成功，可以前往手机厂商官网下载安装对应的驱动程序（通常厂商会将驱动程序集成在手机管家程序中，可以通过下载安装手机管家配置驱动）

   如果显示的不是`device`，请确认下您的设备是否已经安装好驱动，并且允许了USB调试，部分手机需要将连接模式设置为`传输图片（MTP）`模式才可正常连接。

   单机场景

   Windows：

```bash
   %ANDROID_SDK%\platform-tools\adb.exe tcpip 5555
```

   macOS或Linux：

```shell
   $ANDROID_SDK/platform-tools/adb tcpip 5555
```

   通常设备会显示`restarting in TCP mode port: 5555`来提示手机已开启无线ADB调试模式。

> 请确保设备处于安全的网络环境，不要随意允许ADB调试请求，以免给您带来不必要的损失。

   多机场景

   在设备号列表中找到您需要使用的设备，请记录下`device`字段之前的一段字母数字组合，这个是手机的序列号。

   Windows：

```bash
   %ANDROID_SDK%\platform-tools\adb.exe -s ${之前记录的序列号} tcpip 5555
```

   macOS或Linux：

```shell
   $ANDROID_SDK/platform-tools/adb -s ${之前记录的序列号} tcpip 5555
```

**下载打包好的SoloPi APK（SoloPi.apk文件），或者clone源码在本地编译，具体在SoloPi中的操作可以参考： [第一次使用](../../wikis/FirstUse)**



### 文档

- 如果你是第一次使用SoloPi，推荐你先了解SoloPi的一些[使用注意事项](../../wikis/FirstUse)
- Wiki文档： [Home](../../wikis/home)



### 代码导读

- app： 应用业务逻辑。
- shared: 应用核心功能，主要包含node（页面节点获取操作）、event（各类事件监控获取，包含辅助功能事件、触摸事件）、io（数据维护，数据库）、display（性能工具监控项）
- common: 应用框架功能，包含adb能力包装、全局Service能力、消息模块与常用工具。
- mdlibrary: ExportService对应的Proxy生成(引用)
- permission: 权限处理包(引用)
- AdbLib: ADB连接处理(引用)
- androidWebscokets: Android实现的WebSocket(引用)



### 内容讨论

面向行业测试相关从业人员，对工具有什么意见或者建议的话也欢迎Issue、PR或加群讨论。

- 钉钉群（一群已满，请添加二群）：

![group](https://gw.alipayobjects.com/mdn/rms_e29b5f/afts/img/A*K6wzRZfxDv8AAAAAAAAAAABkARQnAQ)

* 微信群：

  **目前微信群已满，推荐加入钉钉群**
  **除了钉钉群外，我们在TesterHome也有相关板块，可以在社区里留言回复 https://testerhome.com/topics/node152 **



### 如何贡献

  [代码贡献](CONTRIBUTING.md) : SoloPi 开发参与说明书

   独乐乐不如众乐乐，开源的核心还是在于技术的分享交流，当你对开源项目产生了一些想法时，有时还会有更加Smart的表达方式，比如(Thanks to uiautomator2)：

- 我们的业务需要这项功能 ==> 我加了个功能，可以在很多场景用到，已经提交MR了。
- 这块儿功能有更详细的文档吗？ ==> 这块内容我改了一下，更方便使用了，帮忙合并一下。
- 我在XXX上怎么用不了啊？ ==> 在XXX手机上功能有点问题，我已经修复了。
- 我刚用了XXX功能，怎么和文档上不一样啊？ ==> 我根据文档试用了一下，碰到了一些坑，这是我在ATA、Lark发的踩坑贴，有些内容可以补充一下。
- 这个是不是一直维护啊？ ==> 我能做些什么？

   当然，Star、Fork、Merge Request、Issue等功能也随时欢迎大家使用哈！

   如果你有什么好的想法，也可以与我们直接联系，进行更加深入的讨论，我们希望将这套移动端的测试工具框架进行更好的推广，欢迎大家多多宣传。



### 致谢

我们在开发过程中使用了一些第三方库，这些库的信息可以在 [版权信息](NOTICE.md) 中进行查看。



### 开源许可

This project is under the Apache 2.0 License. See the [LICENSE](LICENSE) file for the full license text.

```text
Copyright (C) 2015-present, Ant Financial Services Group

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

 	http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```



### Disclaimer (免责声明)

[免责声明](Disclaimer.md)

