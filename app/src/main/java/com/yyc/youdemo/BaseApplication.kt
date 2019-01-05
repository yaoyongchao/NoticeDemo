package com.yyc.youdemo

import android.app.Application
import android.content.Context

/**
 * Created by Administrator on 2018/12/10.
 */

class BaseApplication : Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
    }
}
