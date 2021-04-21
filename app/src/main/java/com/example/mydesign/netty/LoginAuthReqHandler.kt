package com.example.mydesign.netty

import android.util.Log
import com.example.mydesign.ui.main.MainActivity
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter

class LoginAuthReqHandler : ChannelInboundHandlerAdapter() {
    private val TAG = "LoginAuthReqHandler"

    @Throws(Exception::class)
    override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
        val message = msg as MessageProto.Message
        Log.d(TAG,"run1")
        if (message == null) {
            Log.d(TAG,"null")
            super.channelRead(ctx, msg)
        } else {
            Log.d(TAG,"message" + message.msgType)
            Log.d(TAG,"message" + message.body)
            if (Constant.USER_LOGIN == message.msgType) {
                if (message.body == Constant.SUCCESS) {
                    Log.d(TAG,"登录成功！")
                    NettyClient.channel = ctx.channel()
                    ctx.pipeline().remove(this)
                    ctx.fireChannelActive()
                } else {
                    Log.d(TAG,"登录失败")
                    ctx.channel().close()
                }
            }
        }
    }

    @Throws(Exception::class)
    override fun channelActive(ctx: ChannelHandlerContext) {
        Log.d(TAG,"channelActive run log1")
        val message = MessageFactory.getMessage(Constant.USER_LOGIN, MainActivity.USER_ACCOUNT_BEAN!!.usersAccountId, "logging")
        ctx.writeAndFlush(message)
    }

    @Throws(Exception::class)
    override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
        cause.printStackTrace()
        ctx.close()
    }
}