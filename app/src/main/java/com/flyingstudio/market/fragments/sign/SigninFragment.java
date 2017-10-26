package com.flyingstudio.market.fragments.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.View;

import com.flyingstudio.fscore.app.AccountManger;
import com.flyingstudio.fscore.fragment.FlyingFragment;
import com.flyingstudio.market.R;
import com.flyingstudio.market.database.DatabaseManger;
import com.flyingstudio.market.database.user.UserProfile;
import com.flyingstudio.market.database.user.UserProfileDao;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by guopu on 2017/10/17.
 */

public class SigninFragment extends FlyingFragment {

    @BindView(R.id.et_username)
    TextInputEditText mUsername = null;
    @BindView(R.id.et_password)
    TextInputEditText mPassword = null;

    @Override
    public Integer setLayoutID() {
        return R.layout.fragment_signin;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }



    @OnClick(R.id.link_signup)
    public void toSignup(){
        start(new SignupFragment(),SINGLETASK);
    }
    @OnClick(R.id.signin)
    public void onClickSignin(){
        if (checkUserProfile()){
//            HttpClient.builder()
//                    .url("")
//                    .params("username",mUsername.getText().toString().replaceAll(" ",""))
//                    .params("password",mPassword.getText().toString().replaceAll(" ",""))
//                    .success(new ISuccess() {
//                        @Override
//                        public void onSuccess(String responce) {
//                            SignJsonHelper.onSignSuccess(responce);
//                        }
//                    })
//                    .build()
//                    .get();
            UserProfileDao dao = DatabaseManger.newInstance().getUserDao();
            UserProfile profile = mockProfile();
            dao.insertOrReplace(profile);
            AccountManger.setAccountSignin();
            this.pop();
        }
    }
    private UserProfile mockProfile(){
        UserProfile profile =  new  UserProfile();
        profile.setId(123456);
        profile.setAvatar("https://b-ssl.duitang.com/uploads/item/201704/04/20170404094238_icXZe.thumb.700_0.jpeg");
        profile.setEmail("572807239@qq.com");
        profile.setToken("2222222");
        profile.setUsername("praia123");
        profile.setWechat("Group_H");
        return profile;
    }
    private boolean checkUserProfile(){
        boolean success = true;
        final String username = mUsername.getText().toString().replaceAll(" ","");
        final String password = mPassword.getText().toString().replaceAll(" ","");
        if (username.isEmpty() || username.length()<6){
            mUsername.setError("用户名输入不正确");
            success =false;
        }else {
            mUsername.setError(null);
            success =true;
        }
        if (password.isEmpty() || password.length()<6){
            mPassword.setError("用户名输入不正确");
            success =false;
        }else {
            mPassword.setError(null);
            success =true;
        }
        return success;
    }
}
