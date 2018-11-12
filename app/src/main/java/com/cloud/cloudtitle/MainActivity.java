package com.cloud.cloudtitle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cloud.titlec.TitleBar;
import com.cloud.titlec.base.OnTitleBarListener;
import com.cloud.toastc.ToastUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ToastUtil.initToast(this.getApplication());


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
    }
}
