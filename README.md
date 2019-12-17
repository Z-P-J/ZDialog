# ZDialog
 A simple and customizable dialog library.

## ZDialog在本人某个项目中的使用
<div>
    <img src="https://github.com/Z-P-J/ZDialog/raw/master/screenshots/screenshots1.jpg" height="330" width="190">
    <img src="https://github.com/Z-P-J/ZDialog/raw/master/screenshots/screenshots2.jpg" height="330" width="190">
    <img src="https://github.com/Z-P-J/ZDialog/raw/master/screenshots/screenshots3.jpg" height="330" width="190">
    <img src="https://github.com/Z-P-J/ZDialog/raw/master/screenshots/screenshots4.jpg" height="330" width="190">
    <img src="https://github.com/Z-P-J/ZDialog/raw/master/screenshots/screenshots5.jpg" height="330" width="190">
    <img src="https://github.com/Z-P-J/ZDialog/raw/master/screenshots/screenshots6.jpg" height="330" width="190">
    <img src="https://github.com/Z-P-J/ZDialog/raw/master/screenshots/screenshots7.jpg" height="330" width="190">
    <img src="https://github.com/Z-P-J/ZDialog/raw/master/screenshots/screenshots8.jpg" height="330" width="190">
    <img src="https://github.com/Z-P-J/ZDialog/raw/master/screenshots/screenshots9.jpg" height="330" width="190">
    <img src="https://github.com/Z-P-J/ZDialog/raw/master/screenshots/screenshots10.jpg" height="330" width="190">
    <img src="https://github.com/Z-P-J/ZDialog/raw/master/screenshots/screenshots11.jpg" height="330" width="190">
</div>

## How to use?
### 1.ZAlertDialog
```java
// 普通提示Dialog
ZAlertDialog.with(MainActivity.this)
                        .setTitle("确定删除？")
                        .setContent("你将删除文件！")
                        .setPositiveButton(new IDialog.OnClickListener() {
                            @Override
                            public void onClick(IDialog dialog) {
                                dialog.dismiss();
                                // dialog.dismissWithoutAnim();
                                // do something here
                            }
                        })
                        .setNegativeButton(new IDialog.OnClickListener() {
                            @Override
                            public void onClick(IDialog dialog) {
                                dialog.dismiss();
                                dialog.dismissWithoutAnim();
                                // do something
                            }
                        })
                        .show();


// 自定义AlertDialog
ZAlertDialog.with(MainActivity.this)
                        .setTitle(title text)
                        .setContentView(your costom view)
                        .setCancelableOutside(false)
                        .setCancelable(true)
                        .setPositiveButton(new IDialog.OnClickListener() {
                            @Override
                            public void onClick(IDialog dialog) {
                                dialog.dismiss();
                                // dialog.dismissWithoutAnim();
                                // do something here
                            }
                        })
                        .setNegativeButton(new IDialog.OnClickListener() {
                            @Override
                            public void onClick(IDialog dialog) {
                                dialog.dismiss();
                                dialog.dismissWithoutAnim();
                                // do something here
                            }
                        })
                        .setOnDismissListener(new IDialog.OnDismissListener() {
                            @Override
                            public void onDismiss(IDialog dialog) {
                                 // do something here
                            }
                        })
                        .show();
```

### 2.ZEditDialog
```java
String text = "我的文件.txt";
ZEditDialog.with(MainActivity.this)
                        .setTitle("重命名")
                        .setEditText(text)
                        .setHint("前请输入文件名")
                        .setEmptyable(false)
                        .setSelection(0, text.length() - 4)
                        .setAutoShowKeyboard(true)
                        .setOnTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                Log.d("onTextChanged", "s=" + s + " start=" + start + " before=" + before + " count=" + count);
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        })
                        .setPositiveButton(new IDialog.OnPositiveButtonClickListener() {
                            @Override
                            public void onClick(IDialog dialog, String text) {
                                dialog.dismiss();
                                Toast.makeText(MainActivity.this, "新文件名：" + text, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
```

### 3.ZCheckDialog
```java

```

### 4.ZSwitchDialog
```java

```

### 5.ZListDialog
```java

```

### 6.ZMenuDialog
```java

```

### 7.ZSelectDialog
```java

```

### 8.ZBottomSheettDialog
```java

```

### 9.使用ZDialog自定义自己的Dialog
```java

```