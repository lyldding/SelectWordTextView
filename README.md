Android SelectWordTextView 支持点击选中单词。

**支持长文，避免分词创建大量对象，减少内存占用**。
目前只支持单选，可自定义选择颜色。

```
app:selectColor="@color/colorAccent" 
or
setSelectedColor(@ColorInt int selectedColor)
```
CSDN ：https://blog.csdn.net/lylddingHFFW/article/details/89154053

GitHub ： https://github.com/lyldding/SelectWordTextView

<div align=center>
<img src="https://img-blog.csdnimg.cn/20190409170038177.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2x5bGRkaW5nSEZGVw==,size_16,color_FFFFFF,t_70" width="50%" height="50%" />

gradle使用：
```
   maven { url 'https://www.jitpack.io' }

   implementation 'com.github.lyldding:SelectWordTextView:1.0.0'
```