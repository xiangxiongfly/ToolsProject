package com.example.xx.act;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xx.R;
import com.example.xx.utils.CaptchaUtils;
import com.example.xx.widget.VerifyCodeButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.xx.utils.CaptchaUtils.TYPE.CHARS;

/**
 * 验证码相关功能
 */
public class VerifyCodeActivity extends AppCompatActivity {
    @BindView(R.id.btn_verify_code)
    VerifyCodeButton btnVerifyCode;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.iv_code)
    ImageView ivCode;
    @BindView(R.id.btn_get_code)
    Button btnGetCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_code);
        ButterKnife.bind(this);
        setTitle("验证码");
    }

    @OnClick({R.id.btn_verify_code, R.id.btn_get_code})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.btn_verify_code:
                btnVerifyCode.start();
                break;
            case R.id.btn_get_code:
                CaptchaUtils.build()
                        .setBacgroundColor(0xffffff)
                        .setCodeLength(4)
                        .setFontSize(40)
                        .setLineNumber(4)
                        .setSize(200, 70)
                        .setType(CHARS)
                        .into(ivCode);

                tvCode.setText(CaptchaUtils.build().getCode());
                break;
        }
    }
}
