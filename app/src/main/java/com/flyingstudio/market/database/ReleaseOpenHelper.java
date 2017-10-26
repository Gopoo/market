package com.flyingstudio.market.database;

import android.content.Context;

import com.flyingstudio.market.database.cart.DaoMaster;


/**
 * Created by guopu on 2017/10/17.
 */

public class ReleaseOpenHelper extends DaoMaster.OpenHelper {

    public ReleaseOpenHelper(Context context, String name) {
        super(context, name);
    }
}
