package com.common.commonutils;

import android.content.Context;

import java.io.File;

/**
 * @author devliang
 */
public class FileUtils {
    public static void creatFlie(File file) {
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public static void deleteFile(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                //否则如果它是一个目录
                File files[] = file.listFiles();
                //声明目录下所有的文件 files[];
                for (int i = 0; i < files.length; i++) {
                    //遍历目录下所有的文件
                    deleteFile(files[i]);
                }
            }
            file.delete();
        }
    }

    /**
     * 获取res/raw路径
     *
     * @param context
     * @return
     */
    public static String getRawPath(Context context) {
        return "android.resource://" + context.getPackageName() + "/";
    }
}
