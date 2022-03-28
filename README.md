# 自动调整字体大小的 EditText

![](https://raw.githubusercontent.com/chinnsenn/BlogFigureBed/master/blogimg/untitled.gif)

## 使用方法

1、在 gradle 里引用:

`implementation 'com.chinnsenn:submitbutton:#last_version'` ([这里查看最新版本号][2])

2、在 xml 中添加控件

```xml
  <com.chinnsenn.widget.AutoResizingEditText
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="12839081230"
    android:textColor="@color/black"
    android:textSize="20sp"
    app:layout_constraintBottom_toTopOf="@id/gl"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:resizing_max_size="16sp" 
    app:resizing_min_size="5sp"
    app:resizing_step="1.5sp"
    app:resizing_threshold="3" />
```

属性介绍

| 属性名 | 描述 |
| --- | --- |
| resizing_max_size | 最大字体大小 |
| resizing_min_size | 最小字体大小 |
| resizing_threshold |触发调整字体阈值 |
| resizing_step | 调整步进 |

- 属性也可以在代码中设置

```kotlin
val autoResizingEditText:AutoResizingEditText = findViewById(R.id.submitbutton)
autoResizingEditText(13.spF(this), 2.spF(this))
```

## 更新计划

- 支持自定义光标和光标柄染色