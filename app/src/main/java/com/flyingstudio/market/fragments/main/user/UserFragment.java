package com.flyingstudio.market.fragments.main.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyingstudio.fscore.app.AccountManger;
import com.flyingstudio.fscore.app.ISigninCallBack;
import com.flyingstudio.fscore.fragment.bottom.ItemFragment;
import com.flyingstudio.fscore.utils.GlideUtil;
import com.flyingstudio.market.R;
import com.flyingstudio.market.database.DatabaseManger;
import com.flyingstudio.market.database.user.UserProfile;
import com.flyingstudio.market.database.user.UserProfileDao;
import com.flyingstudio.market.fragments.goods.OrderListFragment;
import com.flyingstudio.market.fragments.sign.SigninFragment;
import com.flyingstudio.market.fragments.userprofile.UserProfileFragment;
import com.flyingstudio.market.helper.adapter.OptionItemAdapter;
import com.flyingstudio.market.helper.bean.OptionsFiled;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by guopu on 2017/10/11.
 */

public class UserFragment extends ItemFragment {

    @BindView(R.id.rv_options)
    RecyclerView mRecyclerView = null;
    private List<OptionsFiled> options;

    @BindView(R.id.civ_user_avatar)
    CircleImageView userAvatar = null;

    @BindView(R.id.tv_tips)
    TextView tips = null;

    @BindView(R.id.tb_toolbar)
    CollapsingToolbarLayout layout;

    @OnClick(R.id.civ_user_avatar)
    public void onClickAvatar(){
        if (AccountManger.getSigninState()){
            getParentDelegate().getSupportDelegate().start(new UserProfileFragment());
        }else{
            onClickNotSigninTip();
        }
    }
    @OnClick(R.id.tv_tips)
    public void onClickNotSigninTip(){
        getParentDelegate().getSupportDelegate().start(new SigninFragment());
    }
    @Override
    public Integer setLayoutID() {
        return R.layout.fragment_user;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRecycleView();
        AccountManger.isAccountanSignin(new ISigninCallBack() {
            @Override
            public void onSignin() {
                tips.setText("欢迎您！");
                UserProfileDao dao = DatabaseManger.newInstance().getUserDao();
                UserProfile p = dao.loadAll().get(0);
                layout.setTitle(p.getUsername());
                GlideUtil.loadImageViewSize(getContext()
                    ,p.getAvatar(),150,150,userAvatar);
            }
            @Override
            public void onNotSignin() {
                getParentDelegate().getSupportDelegate().start(new SigninFragment());
            }
        });
    }
    private void startOrderFragmentByType(int type,String title){
        OrderListFragment fragment = OrderListFragment.newInstance(type,title);
        getParentDelegate().getSupportDelegate().start(fragment);
    }
    private void initRecycleView(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setItemAnimator(null);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.HORIZONTAL));
        options =  OptionsFiled.creator()
                .add(0,R.mipmap.my_subbmit,"我发布的")
                .add(1,R.mipmap.my_selled,"我卖出的")
                .add(2,R.mipmap.my_bought,"我买到的")
                .add(3,R.mipmap.my_advise,"提出建议")
                .add(4,R.mipmap.my_set,"设置")
                .add(5,R.mipmap.my_tips,"关于")
                .add(6,R.mipmap.my_share,"分享")
                .crate();
        OptionItemAdapter adapter = OptionItemAdapter.creat(options, new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position<3){
                    OptionsFiled optionsFiled = options.get(position);
                    startOrderFragmentByType(optionsFiled.getType(),optionsFiled.getContent());
                }
            }
        });

        mRecyclerView.setAdapter(adapter);

    }
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
    }
}
