package com.zpj.zdialog.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zpj.zdialog.ZAlertDialog;
import com.zpj.zdialog.ZEditDialog;
import com.zpj.zdialog.base.IDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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
        Button btn7 = findViewById(R.id.btn_dialog);
        btn7.setOnClickListener(this);
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
                break;
        }
    }
}
