package com.flyingstudio.market.fragments.userprofile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyingstudio.fscore.fragment.FlyingFragment;
import com.flyingstudio.fscore.utils.GlideUtil;
import com.flyingstudio.market.R;
import com.flyingstudio.market.database.DatabaseManger;
import com.flyingstudio.market.database.user.UserProfile;
import com.flyingstudio.market.database.user.UserProfileDao;
import com.flyingstudio.market.helper.adapter.ProfilesItemAdapter;
import com.flyingstudio.market.helper.bean.UserProfileFileds;

import java.util.List;

import butterknife.BindView;

/**
 * Created by guopu on 2017/10/25.
 */

public class UserProfileFragment extends FlyingFragment {

    @BindView(R.id.rv_user_profiles)
    RecyclerView recyclerView = null;
    @BindView(R.id.iv_user_avatar)
    ImageView userAvatar = null;

    @Override
    public Integer setLayoutID() {
        return R.layout.fragment_userprofile;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        UserProfileDao dao = DatabaseManger.newInstance().getUserDao();
        UserProfile p = dao.loadAll().get(0);
        GlideUtil.loadImageViewSize(getContext(),p.getAvatar()
        ,300,200,userAvatar);
        initRecycleview(p);
    }
    private void initRecycleview(UserProfile profile){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<UserProfileFileds> list = UserProfileFileds.creator()
                .add("用户名",profile.getUsername())
                .add("邮箱",profile.getEmail())
                .add("地址","暂未设置")
                .add("支付宝","暂未设置")
                .add("联系方式",profile.getWechat())
                .crate();
        ProfilesItemAdapter adapter = ProfilesItemAdapter.creat(list, new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        recyclerView.setAdapter(adapter);
    }

}
