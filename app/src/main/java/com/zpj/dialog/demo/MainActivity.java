package com.zpj.dialog.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zpj.utils.ClickHelper;
import com.zpj.dialog.ZAlertDialog;
import com.zpj.dialog.ZBottomSheetDialog;
import com.zpj.dialog.ZCheckDialog;
import com.zpj.dialog.ZEditDialog;
import com.zpj.dialog.ZMenuDialog;
import com.zpj.dialog.ZSwitchDialog;
import com.zpj.dialog.base.IDialog;
import com.zpj.dialog.view.SmoothCheckBox;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        Button btn = findViewById(R.id.btn_alert);
        btn.setOnClickListener(this);
        Button btn1 = findViewById(R.id.btn_alert_plus);
        btn1.setOnClickListener(this);
        Button btn2 = findViewById(R.id.btn_edit);
        btn2.setOnClickListener(this);
        Button btn3 = findViewById(R.id.btn_check);
        btn3.setOnClickListener(this);
        Button btn4 = findViewById(R.id.btn_switch);
        btn4.setOnClickListener(this);
        Button btn5 = findViewById(R.id.btn_list_1);
        btn5.setOnClickListener(this);
        Button btn6 = findViewById(R.id.btn_list_2);
        btn6.setOnClickListener(this);
        Button btn7 = findViewById(R.id.btn_menu);
//        btn7.setOnClickListener(this);
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
        Button btn8 = findViewById(R.id.btn_select);
        btn8.setOnClickListener(this);
        Button btn9 = findViewById(R.id.btn_bottom_sheet);
        btn9.setOnClickListener(this);
        Button btn10 = findViewById(R.id.btn_dialog);
        btn10.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_alert:
                ZAlertDialog.with(MainActivity.this)
                        .setTitle("确定删除？")
                        .setContent("你将删除文件！")
                        .setPositiveButton(new IDialog.OnClickListener() {
                            @Override
                            public void onClick(IDialog dialog) {
                                Toast.makeText(MainActivity.this, "删除文件", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        })
                        .show();
                break;
            case R.id.btn_alert_plus:
                ZAlertDialog.with(MainActivity.this)
                        .setTitle("确定删除？")
                        .setContent("你将删除文件！")
                        .show();
                break;
            case R.id.btn_edit:
                String text = "我的文件.txt";
                ZEditDialog.with(MainActivity.this)
                        .setTitle("重命名")
                        .setEditText(text)
                        .setHint("请输入新文件名")
                        .setEmptyable(false)
                        .setSelection(0, text.length() - 4)
                        .setAutoShowKeyboard(true)
                        .setOnTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                Log.d(TAG, "onTextChanged s=" + s + " start=" + start + " before=" + before + " count=" + count);
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
                break;
            case R.id.btn_check:
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
                                dialog.dismiss();
                            }
                        })
                        .show();
                break;
            case R.id.btn_switch:
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
                break;
            case R.id.btn_list_1:

                break;
            case R.id.btn_list_2:

                break;
            case R.id.btn_select:

                break;
            case R.id.btn_bottom_sheet:
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
                break;
        }
    }
}
