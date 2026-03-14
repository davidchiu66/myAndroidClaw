package com.common.commonutils;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by  on 2019/9/4
 */


public class TimeCountUtils extends CountDownTimer {

    private TextView btn_count;
    private int type = -1;

    public TimeCountUtils(long millisInFuture, long countDownInterval, TextView btn_count) {
        super(millisInFuture, countDownInterval);
        this.btn_count = btn_count;
    }

    public TimeCountUtils(long millisInFuture, long countDownInterval, TextView btn_count , int type) {
        super(millisInFuture, countDownInterval);
        this.btn_count = btn_count;
        this.type = type;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        btn_count.setEnabled(false);
        if (type == -1) {
            btn_count.setText(millisUntilFinished / 1000 + "秒");
        }else {
            btn_count.setText(millisUntilFinished / 1000 + "秒后重新获取");
        }
    }

    @Override
    public void onFinish() {
        btn_count.setEnabled(true);
        btn_count.setText("重新获取");
    }

}