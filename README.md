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
ZCheckDialog.with(MainActivity.this)
                        .setTitle("确认删除？")
                        .setContent("你将删除该下载任务！")
                        .setChecked(true)
                        .setCheckTitle("删除本地任务")
                        .setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
                                Log.d(TAG, "onCheckedChanged isChecked=" + isChecked);
                            }
                        })
                        .setPositiveButton(new ZCheckDialog.OnClickListener() {
                            @Override
                            public void onClick(IDialog dialog, boolean isChecked) {
                                Toast.makeText(MainActivity.this, "checked=" + isChecked, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
```

### 4.ZSwitchDialog
```java
ZSwitchDialog.with(MainActivity.this)
                        .setTitle("应用自启")
                        .setContent("如果您启用了应用自启，该应用将会在手机开机时自动启动！")
                        .setChecked(true)
                        .setPositiveButton(new ZSwitchDialog.OnClickListener() {
                            @Override
                            public void onClick(IDialog dialog, boolean isChecked) {
                                dialog.dismiss();
                                Toast.makeText(MainActivity.this, "checked=" + isChecked, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton(new ZSwitchDialog.OnClickListener() {
                            @Override
                            public void onClick(IDialog dialog, boolean isChecked) {
                                dialog.dismiss();
                            }
                        })
                        .show();
```

### 5.ZListDialog
```java

```

### 6.ZMenuDialog
```java
ClickHelper.with(btn7)
                .setOnClickListener(new ClickHelper.OnClickListener() {
                    @Override
                    public void onClick(View v, float x, float y) {
                        ZMenuDialog.with(MainActivity.this)
                                .addItems("item1", "item2", "item3", "item4", "item5")
                                .setOnItemClickListener(new ZMenuDialog.OnItemClickListener() {
                                    @Override
                                    public void onItemClicked(String title, int position) {
                                        Toast.makeText(MainActivity.this, title + " clicked", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .show(x, y);
                    }
                });
```

### 7.ZSelectDialog
```java

```

### 8.ZBottomSheettDialog
```java
ZBottomSheetDialog.with(MainActivity.this)
                        .setContentView(R.layout.layout_bottom_sheet)
                        .setOnViewCreateListener((dialog, view) -> {
                            ImageView icon = dialog.getView(R.id.item_icon);
                            TextView title = dialog.getView(R.id.item_name);
                            TextView info = dialog.getView(R.id.item_info);
                            TextView copy = dialog.getView(R.id.item_copy);
                            TextView delete = dialog.getView(R.id.item_delete);
                            TextView rename = dialog.getView(R.id.item_rename);
                            TextView move = dialog.getView(R.id.item_cut);
                            title.setText("我的文件.txt");
                            info.setText("100MB");
                            copy.setOnClickListener(v1 -> {
                                dialog.dismiss();
                                Toast.makeText(this, "Copy", Toast.LENGTH_SHORT).show();
                            });
                            delete.setOnClickListener(v12 -> {
                                dialog.dismiss();
                                ZAlertDialog.with(MainActivity.this)
                                        .setTitle("确定删除？")
                                        .setContent("你将删除文件！")
                                        .setPositiveButton(dialog1 -> {
                                            Toast.makeText(MainActivity.this, "删除文件", Toast.LENGTH_SHORT).show();
                                            dialog1.dismiss();
                                        })
                                        .show();
                            });
                            rename.setOnClickListener(v12 -> {
                                dialog.dismiss();
                                ZEditDialog.with(MainActivity.this)
                                        .setTitle("重命名")
                                        .setEditText(title.getText().toString())
                                        .setHint("请输入新文件名")
                                        .setEmptyable(false)
                                        .setSelection(0, title.getText().toString().length() - 4)
                                        .setAutoShowKeyboard(true)
                                        .setPositiveButton((dialog12, text1) -> {
                                            dialog12.dismiss();
                                            Toast.makeText(MainActivity.this, "新文件名：" + text1, Toast.LENGTH_SHORT).show();
                                        })
                                        .show();
                            });
                            move.setOnClickListener(v12 -> {
                                dialog.dismiss();
                                Toast.makeText(this, "Move", Toast.LENGTH_SHORT).show();
                            });
                        })
                        .show();
```

### 9.使用ZDialog自定义自己的Dialog
```java

```