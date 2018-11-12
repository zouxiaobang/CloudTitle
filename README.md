# CloudTitle
## 特点
1、可设置夜间模式
2、可设置透明模式
3、可使用默认的返回按钮、设置按钮等
4、左右可替换文字
5、可自定义背景
6、可直接将该TitleBar作为一个ViewGroup，往里面填充内容
7、拥有左右两个区域及标题区域的点击事件
## 用法
### 在项目的gradle文件中：
```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

### 在模块的gradle文件中：
```
dependencies {
  implementation 'com.github.zouxiaobang:CloudTitle:Tag'
}
```

### 在XML中使用：
```
        <com.cloud.titlec.TitleBar
            android:id="@+id/tb_title"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="20dp"
            app:bar_style="night"
            app:title="夜间样式标题"
            app:title_left="返回"
            app:title_right="设置"/>

        <com.cloud.titlec.TitleBar
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:title="默认的标题"/>
        ...
```

### 在Java中设置点击事件
```
      ((TitleBar)findViewById(R.id.tb_title)).setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClicked(View view) {
                finish();
            }

            @Override
            public void onTitleClicked(View view) {
                ToastUtil.show("点击了标题");
            }

            @Override
            public void onRightClicked(View view) {
                ToastUtil.show("点击了设置");
            }
        });
```
