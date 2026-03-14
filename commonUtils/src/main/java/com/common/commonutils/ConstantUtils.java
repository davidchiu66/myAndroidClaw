package com.common.commonutils;


import android.content.Context;

/**
 * @version[2021/5/20 14:58]
 **/
public class ConstantUtils {
    /**
     * js调取对象的方法名
     */
    public static final String JS_GAME_NAME = "android_host";

    /**
     * Fresco类加载本地图片的前缀路径
     */
    //public static final String FRESCO_RES = "res://" + App.getInstance().getPackageName() + "/";
    public static String getFescoRes(Context context) {
        return "res://" + context.getPackageName() + "/";
    }


    /**
     * 全局Logger使用
     */
    public static final String TAG = "LOGGER";
    /**
     * 登录页面隐私协议地址、用户协议
     * 用户协议：https://v4-1305887643.cos.ap-beijing.myqcloud.com/userProtocol/userProtocol_old.html
     * 隐私政策：https://v4-1305887643.cos.ap-beijing.myqcloud.com/userProtocol/privacyPolicy.html
     */
    public static final String TV_LOGIN_PRIVATE_URL = "https://v4-1305887643.cos.ap-beijing.myqcloud.com/userProtocol/privacyPolicy_pad.html";
    public static final String TV_LOGIN_PRIVATE_USER_URl = "https://v4-1305887643.cos.ap-beijing.myqcloud.com/userProtocol/userProtocol_old.html";
}
