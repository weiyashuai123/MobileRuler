# MobileRuler——移动标尺
 * 背景： </br>
 有一天作者在逛淘宝的时候想买一个桌子，看着淘宝店的桌子歇着80cm*60cm，作者看了看自己的手，发现自己并不知道80cm有多长（看手有毛用啊！！），于是就想到用手机来当尺子量一下自己的桌子有多长，但手机上没有刻度，于是乎这个APP就诞生啦~！
 * 说明： </br>1.由于二进制计算、单位转换、数据类型转换等原因，该标尺精确度有限（精确度约为0.01mm），不可用于精确测量。</br>
 2.该项目所用IDE为Eclipse ====》2018-5-28 迁移至Android studio
 * 实现效果：</br>
 ![](https://github.com/weiyashuai123/MobileRuler/blob/master/git_res/ruler_0.jpg)
 ![](https://github.com/weiyashuai123/MobileRuler/blob/master/git_res/ruler_1.jpg)
 ![](https://github.com/weiyashuai123/MobileRuler/blob/master/git_res/ruler_2.jpg)
 ![](https://github.com/weiyashuai123/MobileRuler/blob/master/git_res/ruler_4.jpg)
## 更新记录：</br>
#### 2018-5-22:
* 1.修复虚拟键占屏导致刻度出错的bug。
* 2.修正算法，提高精确度
* 2.新增可选标尺类型-直尺与三角尺。
#### 2018-5-28:
* 迁移项目至Android studio，原Eclipse项目点击 [这里](https://github.com/weiyashuai123/MobileRuler/raw/master/git_res/MobileRuler.zip) 下载
* 封装工具类，现在可以通过添加依赖来使用该工具。如下：</br>
  1.在工程根目录的build.gradle加入：</br>
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
2.添加dependency：</br>
```
dependencies {
    implementation 'com.github.weiyashuai123:Views:1.2'
}
```
## 控件使用方式：
```
<com.wys.ruler.RulerView
        android:layout_width="80dp"
        android:layout_height="match_parent"
        app:ruler_type="Straight"
        app:ruler_backgroundImage="@drawable/black"
        app:ruler_scaleColor="@android:color/black"
        app:ruler_scaleLength="10dp"
        />
属性说明：
ruler_type:直尺类型 Straight为直尺，Triangle为三角尺
ruler_backgroundImage:直尺背景图片
ruler_scaleColor:刻度颜色
ruler_scaleLength:基本刻度长度
```

## 作者信息
* QQ：592693857</br>
 E-mail：subcakewei@outlook.com</br>
 GitHub：[github.com/weiyashuai123](https://github.com/weiyashuai123)</br>
 ![](https://github.com/weiyashuai123/Code-specification/blob/master/icon120.png)
 ![](https://github.com/weiyashuai123/TeacherAssiatant-detailed/blob/master/image/wechat.png)
 ![](https://github.com/weiyashuai123/TeacherAssiatant-detailed/blob/master/image/icon120.png)</br>
* 万水千山总是情，点个Star行不行~
