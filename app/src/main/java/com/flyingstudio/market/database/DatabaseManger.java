package com.flyingstudio.market.database;

import android.content.Context;

import com.flyingstudio.market.database.cart.CartProfileDao;

import com.flyingstudio.market.database.cart.DaoMaster;
import com.flyingstudio.market.database.cart.DaoSession;
import com.flyingstudio.market.database.user.UserProfileDao;

import org.greenrobot.greendao.database.Database;

/**
 * Created by guopu on 2017/10/17.
 */

public class DatabaseManger {
    private DaoSession mDaoSession = null;
    private UserProfileDao mUserDao = null;
    private CartProfileDao mCartDao = null;
    private DatabaseManger(){}
    private static final class Holder{
        private static final DatabaseManger MANGER = new DatabaseManger();
    }
    public final DatabaseManger initManger(Context context){
        init(context);
        return this;
    }
    public static final DatabaseManger newInstance(){
        return Holder.MANGER;
    }
    private final void init(Context context){
        final ReleaseOpenHelper openHelper = new ReleaseOpenHelper(context,"erhuo.db");
        Database db = openHelper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mUserDao = mDaoSession.getUserProfileDao();
        mCartDao = mDaoSession.getCartProfileDao();
    }
    public final UserProfileDao getUserDao(){
        return mUserDao;
    }
    public final CartProfileDao getCartDao(){
        return mCartDao;
    }
}
