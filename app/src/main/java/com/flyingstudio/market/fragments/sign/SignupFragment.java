package com.flyingstudio.market.fragments.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.flyingstudio.fscore.app.Flying;
import com.flyingstudio.fscore.fragment.FlyingFragment;
import com.flyingstudio.fscore.net.HttpClient;
import com.flyingstudio.fscore.net.callback.ISuccess;
import com.flyingstudio.market.R;
import com.flyingstudio.market.helper.json.SignJsonHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by guopu on 2017/10/17.
 */

public class SignupFragment extends FlyingFragment {
    @BindView(R.id.et_username)
    TextInputEditText mName = null;
    @BindView(R.id.et_mail)
    TextInputEditText mEmail = null;
    @BindView(R.id.et_connect)
    TextInputEditText mConnect = null;
    @BindView(R.id.et_password)
    TextInputEditText mPassword = null;
    @BindView(R.id.et_password_again)
    TextInputEditText mRePassword = null;

    @Override
    public Integer setLayoutID() {
        return R.layout.fragment_signup;
    }

    @OnClick(R.id.link_signin)
    public void onClickToSignin(){
        start(new SigninFragment(),SINGLETASK);
    }
    @OnClick(R.id.signup)
    public void onClickSignup(){
        if (checkUserProfile()){
            HttpClient.builder()
                    .url("http://www.baidu.com/signin")
                    .params("username","123")
                    .params("asaa","asas")
                    .params("asasss","asasss")
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String responce) {
                            SignJsonHelper.onSignSuccess(responce);
                            Toast.makeText(Flying.getAppContext(),"注册成功",Toast.LENGTH_SHORT).show();
                        }
                    })
                    .build()
                    .post();
        }
    }
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
    }

    private boolean checkUserProfile(){
        final String name = mName.getText().toString().replaceAll(" ","");
        final String email = mEmail.getText().toString().replaceAll(" ","");
        final String connect = mConnect.getText().toString().replaceAll(" ","");
        final String password = mPassword.getText().toString().replaceAll(" ","");
        final String rePassword = mRePassword.getText().toString().replaceAll(" ","");

        boolean isPass = true;

        if (name.isEmpty()||name.length()< 6 || name.length()>20) {
            mName.setError("请输入6位以上用户名");
            isPass = false;
        } else {
            mName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (connect.isEmpty() || connect.length() < 6||connect.length()>20) {
            mConnect.setError("联系方式错误");
            isPass = false;
        } else {
            mConnect.setError(null);
        }

        if (password.isEmpty() || password.length() < 6|| password.length()> 20) {
            mPassword.setError("请输入6位以上密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        if (rePassword.isEmpty() || !(rePassword.equals(password))) {
            mRePassword.setError("密码验证错误");
            isPass = false;
        } else {
            mRePassword.setError(null);
        }
        return isPass;
    }
}
