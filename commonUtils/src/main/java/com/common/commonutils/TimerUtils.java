package com.common.commonutils;

import static com.common.commonutils.ConstantUtils.TAG;

import com.orhanobut.logger.Logger;

import java.util.Timer;

public class TimerUtils {

    public static final long timeN = 10 * 1000;
//    public static final long timeN = 1000;

    public static void timePause(Timer timer) {
        if (timer != null) {
            Logger.t(TAG).d("timePause");
            timer.cancel();
            timer = null;
        }
    }
}
