package com.example.xx.act;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.xx.R;
import com.example.xx.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 输入框
 */
public class InputActivity extends AppCompatActivity {

    @BindView(R.id.til_phone)
    TextInputLayout tilPhone;
    @BindView(R.id.til_password)
    TextInputLayout tilPassword;
    @BindView(R.id.et_two_dicimal)
    EditText etTwoDicimal;
    private EditText mEtPhone;
    private EditText mEtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        setTitle("输入框");
        ButterKnife.bind(this);

        etTwoDicimal.setFilters(new InputFilter[]{getTwoDecimalFilter()});

        mEtPhone = tilPhone.getEditText();
        mEtPassword = tilPassword.getEditText();

        mEtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String phone = s.toString();
                if (!UiUtils.checkPhone(phone)) {
                    tilPhone.setError("手机号输入错误");
                } else {
                    tilPhone.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mEtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password = s.toString();
                if (!UiUtils.checkPassword(password)) {
                    tilPassword.setError("密码输入有误(6-16位字母数字组合)");
                } else {
                    tilPassword.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


    public InputFilter getTwoDecimalFilter() {
        InputFilter inputFilter = new InputFilter() {
            /**
             * @param source 每次输入的字符
             * @param start 开始位置
             * @param end 结束位置
             * @param dest 已输入的字符串
             * @param dstart
             * @param dend
             * @return 返回单个字符
             */
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if ("".equals(source.toString())) {
                    return null;
                }
                String dValue = dest.toString();
                String[] splitArray = dValue.split("\\.");
                if (splitArray.length > 1) {
                    String dotValue = splitArray[1];
                    int diff = dotValue.length() + 1 - 2;
                    if (diff > 0) {
                        return source.subSequence(start, end - diff);
                    }
                }
                return null;
            }
        };
        return inputFilter;
    }
}
