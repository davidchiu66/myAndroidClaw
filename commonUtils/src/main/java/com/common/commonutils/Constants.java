package com.common.commonutils;


import android.view.View;


/**
 * Constants
 * <p>
 * 常量命名应全部大写并以下划线分隔
 *
 * @author devliang
 */
public class Constants {

    public static final int FULL_SCREEN_FLAG = View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
            View.SYSTEM_UI_FLAG_FULLSCREEN;

    /**
     * 疗程结束时间
     */
    public static final String PERIOD_OF_TREATMENT_END_DATE = "period_of_treatment_end_date";

    public static final String TRAIN_TYPE = "train_type";

    public static final String DUE_TO_TIME = "due_to_time";



    /**
     * 完成当日训练
     */
    public static final int DONE_TRAINING_TYPE = 0;
    /**
     * 未完成当日训练
     */
    public static final int UNDONE_TRAINING_TYPE = 1;
    /**
     * 到院复诊
     */
    public static final int TO_HOSPITAL_TYPE = 2;
    /**
     * 在线评测
     */
    public static final int ONLINE_EVALUATION_TYPE = 3;

    public static final int WEEK_SUN = 0;

    public static final int WEEK_MON = 1;

    public static final int WEEK_TUE = 2;

    public static final int WEEK_WED = 3;

    public static final int WEEK_THU = 4;

    public static final int WEEK_FRI = 5;

    public static final int WEEK_SAT = 6;

    /**
     * 是否是训练周
     */
    public static final String IS_WEEK_TRAINING = "0";

    public static final String DATA_PATTERN_YYYY_MM_DD = "yyyy-MM-dd";

    public static final int MSG_TYPE_NORMAL = 1;
    public static final int MSG_TYPE_REPORT = 2;
    public static final int MSG_TYPE_RENEWAL = 3;
    public static final int MSG_TYPE_RETEST = 4;
    public static final int MSG_TYPE_OPERATION = 5;
    public static final int MSG_TYPE_NOTICE = 10;
    public static final String ACTION_POPUP = "POPUP";
    public static final String ACTION_ROUTER_PAGE = "ROUTER_PAGE";
    public static final String ACTION_OTHER = "OTHER";


    public static final int TOAST_Y_OFFSET = 130;

    public static final String HTTP_SCHEME = "http";

    public static final String HTTPS_SCHEME = "https";

//    public static final String URL_NAME = "urlName";
//
//    public static final String TRAIN_ZIP = "trainZip";
//
//    public static final String BASE_URL = "baseUrl";
}
