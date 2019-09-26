package com.zpj.zdialog.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.zpj.zdialog.IDialog;
import com.zpj.zdialog.ZAlertDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        Button btn = findViewById(R.id.btn_delete);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ZAlertDialog.with(MainActivity.this)
                        .setTitle("确定删除？")
                        .setContent("你将删除文件！")
                        .setPositiveButton(new IDialog.OnClickListener() {
                            @Override
                            public void onClick(IDialog dialog) {
                                dialog.dismiss();
//                                dialog.dismissWithoutAnim();
                                // do something
                            }
                        })
                        .setNegativeButton(new IDialog.OnClickListener() {
                            @Override
                            public void onClick(IDialog dialog) {
                                dialog.dismiss();
//                                dialog.dismissWithoutAnim();
                                // do something
                            }
                        })
                        .show();
            }
        });
    }
}
