package com.common.commonutils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class ScreenUtils {
  public static int originScreenWidth;
  public static int originScreenHeight;

  private ScreenUtils() {
    throw new UnsupportedOperationException("cannot be instantiated");
  }

  public static int getScreenWidth(Context context) {
    if (context == null) {
      return 0;
    }
    DisplayMetrics outMetrics = new DisplayMetrics();
    getDisplay(context).getMetrics(outMetrics);
    return outMetrics.widthPixels;
  }

  public static int getScreenHeight(Context context) {
    if (context == null) {
      return 0;
    }
    DisplayMetrics outMetrics = new DisplayMetrics();
    getDisplay(context).getMetrics(outMetrics);
    return outMetrics.heightPixels;
  }

  public static Display getDisplay(Context context) {
    WindowManager manager = (WindowManager)context.getSystemService("window");
    return manager.getDefaultDisplay();
  }

  public static void setVerticalScreen(Activity activity)
  {
    activity.setRequestedOrientation(1);
  }

  public static void setHorizontalScreen(Activity activity)
  {
    activity.setRequestedOrientation(0);
  }

  public static int getScreenBrightness(Activity activity) {
    int screenBrightness = 50;
    try {
      screenBrightness = Settings.System.getInt(activity.getContentResolver(), "screen_brightness");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return screenBrightness;
  }

  public static void setScreenBrightness(Activity activity, int brightnessValue) {
    Window localWindow = activity.getWindow();
    WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
    if ((brightnessValue > 15) && (brightnessValue <= 255)) {
      float f = brightnessValue / 255.0F;
      localLayoutParams.screenBrightness = f;
      localWindow.setAttributes(localLayoutParams);
    } else if ((brightnessValue >= 0) && (brightnessValue <= 15)) {
      float f = 0.0588235F;
      localLayoutParams.screenBrightness = f;
      localWindow.setAttributes(localLayoutParams);
    }
  }

  public static int getStatusHeight(Context context) {
    int statusHeight = -1;
    try {
      Class clazz = Class.forName("com.android.internal.R$dimen");
      Object object = clazz.newInstance();
      int height = Integer.parseInt(clazz.getField("status_bar_height")
        .get(object)
        .toString());
      statusHeight = context.getResources().getDimensionPixelSize(height);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return statusHeight;
  }

  public static Bitmap snapShotWithStatusBar(Activity activity) {
    View view = activity.getWindow().getDecorView();
    view.setDrawingCacheEnabled(true);
    view.buildDrawingCache();
    Bitmap bmp = view.getDrawingCache();
    int width = getScreenWidth(activity);
    int height = getScreenHeight(activity);
    Bitmap bp = null;
    bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
    view.destroyDrawingCache();
    return bp;
  }

  public static Bitmap snapShotWithoutStatusBar(Activity activity) {
    View view = activity.getWindow().getDecorView();
    view.setDrawingCacheEnabled(true);
    view.buildDrawingCache();
    Bitmap bmp = view.getDrawingCache();
    Rect frame = new Rect();
    activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
    int statusBarHeight = frame.top;

    int width = getScreenWidth(activity);
    int height = getScreenHeight(activity);
    Bitmap bp = null;
    bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height - statusBarHeight);
    view.destroyDrawingCache();
    return bp;
  }


  public static int getScreenWidthNew(Context context) {
    if (context == null) {
      return 0;
    }
    DisplayMetrics outMetrics = new DisplayMetrics();
    getDisplay(context).getRealMetrics(outMetrics);
    return outMetrics.widthPixels;
  }

  public static int getScreenHeightNew(Context context) {
    if (context == null) {
      return 0;
    }
    DisplayMetrics outMetrics = new DisplayMetrics();
    getDisplay(context).getRealMetrics(outMetrics);
    return outMetrics.heightPixels;
  }

}