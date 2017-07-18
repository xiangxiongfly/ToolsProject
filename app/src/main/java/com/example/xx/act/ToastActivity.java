package com.example.xx.act;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.xx.R;
import com.example.xx.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ToastActivity extends AppCompatActivity {

    @BindView(R.id.btn_error_toast)
    Button btnErrorToast;
    @BindView(R.id.btn_success_toast)
    Button btnSuccessToast;
    @BindView(R.id.btn_info_toast)
    Button btnInfoToast;
    @BindView(R.id.btn_warning_toast)
    Button btnWarningToast;
    @BindView(R.id.btn_normal_toast_w_icon)
    Button btnNormalToastWIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        ButterKnife.bind(this);
        setTitle("Toast");
    }

    @OnClick({R.id.btn_error_toast, R.id.btn_success_toast, R.id.btn_info_toast, R.id.btn_warning_toast, R.id.btn_normal_toast_w_icon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_error_toast:
                ToastUtils.error(this, "错误提示").show();
                break;
            case R.id.btn_success_toast:
                ToastUtils.success(this, "成功提示").show();
                break;
            case R.id.btn_info_toast:
                ToastUtils.info(this, "信息提示").show();
                break;
            case R.id.btn_warning_toast:
                ToastUtils.warning(this, "警告提示").show();
                break;
            case R.id.btn_normal_toast_w_icon:
                ToastUtils.normal("普通提示1");
//                ToastUtils.normal(this, "普通提示2").show();
                break;
        }
    }
}
