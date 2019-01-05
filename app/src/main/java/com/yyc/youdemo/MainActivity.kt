package com.yyc.youdemo

import android.app.ActivityManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        btn1.setOnClickListener {
//            var intent = Intent(this,TowActivity::class.java)
//            startActivity(intent)
            test()
//            sendNotify()
//            isFore()
//            Log.e("aa","-----:" + MyUtils.isAppForeground(this))
        }
    }

    private fun isFore() {
        if (Build.VERSION.SDK_INT >= 14) {
            var lifecycle = SimpleActivityLifecycle()
            var bool = lifecycle.isForeground
            Log.e("aa", " -- " + bool)
        }

    }

     fun sendNotify() {
        //获取NotificationManager实力
        var notifyManager:NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        //实例化NotificaitonCaompat.Builde并设置相关属性
        var builder = NotificationCompat.Builder(this).setSmallIcon(R.mipmap.ic_vc_logo)
                //设置通知标题
                .setContentTitle("通知测试")
                //设置通知内容
                .setContentText("只有小图标、标题、内容")
        notifyManager?.notify(1,builder.build())
         var intent = Intent(this,TowActivity::class.java)
         var pendingIntent = PendingIntent.getActivity(this,0,intent,0)
         builder.setContentIntent(pendingIntent)
    }



    val id = "channel_1"
    val name = "名字"
    var notificationUtils: NotificationUtils? = null
    fun test() {
        notificationUtils = NotificationUtils(this)
        notificationUtils?.sendNotification("测试","内容")

    }

}
