package com.example.homework22021.presenter

import android.os.Message
import com.example.homework22021.activity.MainActivity

class OnClickPresenter : IOnClickPresenter {
    override fun onButtonClick() {
        val msg = Message()
        msg.obj = "这里是发回去的信息"
        MainActivity.handler.sendMessage(msg)
    }
}