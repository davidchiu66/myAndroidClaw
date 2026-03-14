package com.common.commonutils;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.orhanobut.logger.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.Locale;
import java.util.UUID;

/**
 * @ProjectName: 
 * @ClassName: DeviceInfoUtils
 * @Description:
 * @Author: 
 * @Email: 
 * @CreateDate: 2022/11/18 9:54 上午
 */
public class DeviceInfoUtils {
    private static final int INTERNAL_STORAGE = 0;
    private static final int EXTERNAL_STORAGE = 1;

    /**
     * 获取设备宽度（px）
     */
    public static int getDeviceWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取设备高度（px）
     */
    public static int getDeviceHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

//    /**
//     * 获取设备的唯一标识， 需要 “android.permission.READ_Phone_STATE”权限
//     */
//    public static String getIMEI(Context context) {
//        TelephonyManager tm = (TelephonyManager) context
//                .getSystemService(Context.TELEPHONY_SERVICE);
//        String deviceId = tm.getDeviceId();
//        if (deviceId == null) {
//            return "UnKnown";
//        } else {
//            return deviceId;
//        }
//    }

    /**
     * 获取厂商名
     **/
    public static String getDeviceManufacturer() {
        return Build.MANUFACTURER;
    }

    /**
     * 获取产品名
     **/
    public static String getDeviceProduct() {
        return Build.PRODUCT;
    }

    /**
     * 获取手机品牌
     */
    public static String getDeviceBrand() {
        return Build.BRAND;
    }

    /**
     * 获取手机型号
     */
    public static String getDeviceModel() {
        return Build.MODEL;
    }

    /**
     * 获取手机主板名
     */
    public static String getDeviceBoard() {
        return Build.BOARD;
    }

    /**
     * 设备名
     **/
    public static String getDeviceDevice() {
        return Build.DEVICE;
    }

    /**
     * fingerprit 信息
     **/
    public static String getDeviceFubgerprint() {
        return Build.FINGERPRINT;
    }

    /**
     * 硬件名
     **/
    public static String getDeviceHardware() {
        return Build.HARDWARE;
    }

    /**
     * 主机
     **/
    public static String getDeviceHost() {
        return Build.HOST;
    }

    /**
     * 显示ID
     **/
    public static String getDeviceDisplay() {
        return Build.DISPLAY;
    }

    /**
     * ID
     **/
    public static String getDeviceId() {
        return Build.ID;
    }

    /**
     * 获取手机用户名
     **/
    public static String getDeviceUser() {
        return Build.USER;
    }

    /**
     * 获取手机 硬件序列号
     **/
    public static String getDeviceSerial() {
        return Build.SERIAL;
    }

    /**
     * 获取手机Android 系统SDK
     *
     * @return
     */
    public static int getDeviceSDK() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获取手机Android 版本
     *
     * @return
     */
    public static String getDeviceAndroidVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取当前手机系统语言。
     */
    public static String getDeviceDefaultLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取当前系统上的语言列表(Locale列表)
     */
    public static String getDeviceSupportLanguage() {
        Log.e("wangjie", "Local:" + Locale.GERMAN);
        Log.e("wangjie", "Local:" + Locale.ENGLISH);
        Log.e("wangjie", "Local:" + Locale.US);
        Log.e("wangjie", "Local:" + Locale.CHINESE);
        Log.e("wangjie", "Local:" + Locale.TAIWAN);
        Log.e("wangjie", "Local:" + Locale.FRANCE);
        Log.e("wangjie", "Local:" + Locale.FRENCH);
        Log.e("wangjie", "Local:" + Locale.GERMANY);
        Log.e("wangjie", "Local:" + Locale.ITALIAN);
        Log.e("wangjie", "Local:" + Locale.JAPAN);
        Log.e("wangjie", "Local:" + Locale.JAPANESE);
        return Locale.getAvailableLocales().toString();
    }


//    /**
//     * @return 设备编号
//     */
//    public static String getSerialNumber() {
//        String serial = null;
//        try {
//            Class<?> c = Class.forName("android.os.SystemProperties");
//            Method get = c.getMethod("get", String.class);
//            serial = (String) get.invoke(c, "ro.serialno");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return serial;
//    }


    /**
     * 获得设备硬件标识
     *
     * @param context 上下文
     * @return 设备硬件标识
     */
    public static String getDeviceId(Context context) {
        StringBuilder sbDeviceId = new StringBuilder();

        //获得设备默认IMEI（>=6.0 需要ReadPhoneState权限）
        String imei = getIMEI(context);
        //获得AndroidId（无需权限）
        String androidId = getAndroidId(context);
        //获得设备序列号（无需权限）
        String serial = getSERIAL();
        //获得硬件uuid（根据硬件相关属性，生成uuid）（无需权限）
        String uuid = getDeviceUUID().replace("-", "");
        //追加imei
        if (imei != null && imei.length() > 0) {
            sbDeviceId.append(imei);
            sbDeviceId.append("|");
        }
        //追加androidid
        if (androidId != null && androidId.length() > 0) {
            sbDeviceId.append(androidId);
            sbDeviceId.append("|");
        }
        //追加serial
        if (serial != null && serial.length() > 0) {
            sbDeviceId.append(serial);
            sbDeviceId.append("|");
        }
        //追加硬件uuid
        if (uuid != null && uuid.length() > 0) {
            sbDeviceId.append(uuid);
        }

        //生成SHA1，统一DeviceId长度
        if (sbDeviceId.length() > 0) {
            try {
                byte[] hash = getHashByString(sbDeviceId.toString());
                String sha1 = bytesToHex(hash);
                if (sha1 != null && sha1.length() > 0) {
                    //返回最终的DeviceId
                    Logger.t("DeviceInfoUtils").i("getDeviceId------" + sha1);
                    return sha1;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        //如果以上硬件标识数据均无法获得，
        //则DeviceId默认使用系统随机数，这样保证DeviceId不为空
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获得设备硬件标识
     *
     * @param context 上下文
     * @return 设备硬件标识
     */
    public static String getDeviceId_New(Context context) {
        StringBuilder sbDeviceId = new StringBuilder();

        //获得设备默认IMEI（>=6.0 需要ReadPhoneState权限）
        String imei = getIMEI(context);
        //获得AndroidId（无需权限）
        String addressId = getLocalMacAddress();
        //获得设备序列号（无需权限）
        String serial = getSERIAL();
        //获得硬件uuid（根据硬件相关属性，生成uuid）（无需权限）
        String uuid = getDeviceUUID().replace("-", "");
        //追加imei
        if (imei != null && imei.length() > 0) {
            sbDeviceId.append(imei);
            sbDeviceId.append("|");
        }
        //追加androidid
        if (addressId != null && addressId.length() > 0) {
            sbDeviceId.append(addressId);
            sbDeviceId.append("|");
        }
        //追加serial
        if (serial != null && serial.length() > 0) {
            sbDeviceId.append(serial);
            sbDeviceId.append("|");
        }
        //追加硬件uuid
        if (uuid != null && uuid.length() > 0) {
            sbDeviceId.append(uuid);
        }
        Logger.t("DeviceInfoUtils").i("getDeviceId_new------" + getMD5(String.valueOf(sbDeviceId)).toUpperCase(Locale.CHINA));
        return getMD5(String.valueOf(sbDeviceId)).toUpperCase(Locale.CHINA);
    }

    public static String getMD5(String val) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            return val;
        }
        md5.update(val.getBytes());
        byte[] m = md5.digest();//加密
        return getString(m);
    }

    private static String getString(byte[] b) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            int a = b[i];
            if (a < 0)
                a += 256;
            if (a < 16)
                buf.append("0");
            buf.append(Integer.toHexString(a));
        }
        return buf.toString().substring(8, 24);
    }

    //从Linux底层获取MAC
    public static String getLocalMacAddress() {
        String macSerial = null;
        String str = "";
        try {
            Process pp = Runtime.getRuntime().exec(
                    "cat /sys/class/net/wlan0/address");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);


            for (; null != str; ) {
                str = input.readLine();
                if (str != null) {
                    macSerial = str.trim();// 去空格
                    break;
                }
            }
        } catch (IOException ex) {
            // 赋予默认值
            ex.printStackTrace();
        }
        return macSerial;
    }

    //需要获得READ_PHONE_STATE权限，>=6.0，默认返回null
    public static String getIMEI(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager)
                    context.getSystemService(Context.TELEPHONY_SERVICE);
            return tm.getDeviceId();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    /**
     * 获得设备的AndroidId
     *
     * @param context 上下文
     * @return 设备的AndroidId
     */
    private static String getAndroidId(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(),
                    Settings.Secure.ANDROID_ID);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    /**
     * 获得设备序列号（如：WTK7N16923005607）, 个别设备无法获取
     *
     * @return 设备序列号
     */
    private static String getSERIAL() {
        try {
            return Build.SERIAL;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    /**
     * 获得设备硬件uuid
     * 使用硬件信息，计算出一个随机数
     *
     * @return 设备硬件uuid
     */
    private static String getDeviceUUID() {
        try {
            String dev = "3883756" +
                    Build.BOARD.length() % 10 +
                    Build.BRAND.length() % 10 +
                    Build.DEVICE.length() % 10 +
                    Build.HARDWARE.length() % 10 +
                    Build.ID.length() % 10 +
                    Build.MODEL.length() % 10 +
                    Build.PRODUCT.length() % 10 +
                    Build.SERIAL.length() % 10;
            return new UUID(dev.hashCode(),
                    Build.SERIAL.hashCode()).toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    /**
     * 取SHA1
     *
     * @param data 数据
     * @return 对应的hash值
     */
    private static byte[] getHashByString(String data) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.reset();
            messageDigest.update(data.getBytes("UTF-8"));
            return messageDigest.digest();
        } catch (Exception e) {
            return "".getBytes();
        }
    }

    /**
     * 转16进制字符串
     *
     * @param data 数据
     * @return 16进制字符串
     */
    private static String bytesToHex(byte[] data) {
        StringBuilder sb = new StringBuilder();
        String stmp;
        for (int n = 0; n < data.length; n++) {
            stmp = (Integer.toHexString(data[n] & 0xFF));
            if (stmp.length() == 1)
                sb.append("0");
            sb.append(stmp);
        }
        return sb.toString().toUpperCase(Locale.CHINA);
    }

    public static String getDeviceAllInfo(Context context) {

        return "\n\n1. IMEI:\n\t\t" + getIMEI(context)

                + "\n\n2. 设备宽度:\n\t\t" + getDeviceWidth(context)

                + "\n\n3. 设备高度:\n\t\t" + getDeviceHeight(context)

                + "\n\n4. 是否有内置SD卡:\n\t\t" + isSDCardMount()

                + "\n\n5. RAM 信息:\n\t\t" + getRAMInfo(context)

                + "\n\n6. 内部存储信息\n\t\t" + getStorageInfo(context, 0)

                + "\n\n7. SD卡 信息:\n\t\t" + getStorageInfo(context, 1)

//                + "\n\n8. 是否联网:\n\t\t" + Utils.isNetworkConnected(context)
//
//                + "\n\n9. 网络类型:\n\t\t" + Utils.GetNetworkType(context)

                + "\n\n10. 系统默认语言:\n\t\t" + getDeviceDefaultLanguage()

                + "\n\n11. 硬件序列号(设备名):\n\t\t" + Build.SERIAL

                + "\n\n12. 手机型号:\n\t\t" + Build.MODEL

                + "\n\n13. 生产厂商:\n\t\t" + Build.MANUFACTURER

                + "\n\n14. 手机Fingerprint标识:\n\t\t" + Build.FINGERPRINT

                + "\n\n15. Android 版本:\n\t\t" + Build.VERSION.RELEASE

                + "\n\n16. Android SDK版本:\n\t\t" + Build.VERSION.SDK_INT

                + "\n\n17. 安全patch 时间:\n\t\t" + Build.VERSION.SECURITY_PATCH

//                + "\n\n18. 发布时间:\n\t\t" + Utils.Utc2Local(android.os.Build.TIME)

                + "\n\n19. 版本类型:\n\t\t" + Build.TYPE

                + "\n\n20. 用户名:\n\t\t" + Build.USER

                + "\n\n21. 产品名:\n\t\t" + Build.PRODUCT

                + "\n\n22. ID:\n\t\t" + Build.ID

                + "\n\n23. 显示ID:\n\t\t" + Build.DISPLAY

                + "\n\n24. 硬件名:\n\t\t" + Build.HARDWARE

                + "\n\n25. 产品名:\n\t\t" + Build.DEVICE

                + "\n\n26. Bootloader:\n\t\t" + Build.BOOTLOADER

                + "\n\n27. 主板名:\n\t\t" + Build.BOARD

                + "\n\n28. CodeName:\n\t\t" + Build.VERSION.CODENAME
                + "\n\n29. 语言支持:\n\t\t" + getDeviceSupportLanguage();

    }

    /**
     * 获取 手机 RAM 信息
     */
    public static String getRAMInfo(Context context) {
        long totalSize = 0;
        long availableSize = 0;

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(context.ACTIVITY_SERVICE);

        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        totalSize = memoryInfo.totalMem;
        availableSize = memoryInfo.availMem;

        return "可用/总共：" + Formatter.formatFileSize(context, availableSize)
                + "/" + Formatter.formatFileSize(context, totalSize);
    }


    /**
     * 判断SD是否挂载
     */
    public static boolean isSDCardMount() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取手机存储 ROM 信息
     * <p>
     * type：用于区分内置存储于外置存储的方法
     * <p>
     * 内置SD卡 ：INTERNAL_STORAGE = 0;
     * <p>
     * 外置SD卡：EXTERNAL_STORAGE = 1;
     **/
    public static String getStorageInfo(Context context, int type) {

        String path = getStoragePath(context, type);
        /**
         * 无外置SD 卡判断
         * **/
        if (isSDCardMount() == false || TextUtils.isEmpty(path) || path == null) {
            return "无外置SD卡";
        }

        File file = new File(path);
        StatFs statFs = new StatFs(file.getPath());
        String stotageInfo;

        long blockCount = statFs.getBlockCountLong();
        long bloackSize = statFs.getBlockSizeLong();
        long totalSpace = bloackSize * blockCount;

        long availableBlocks = statFs.getAvailableBlocksLong();
        long availableSpace = availableBlocks * bloackSize;

        stotageInfo = "可用/总共："
                + Formatter.formatFileSize(context, availableSpace) + "/"
                + Formatter.formatFileSize(context, totalSpace);

        return stotageInfo;

    }

    /**
     * 使用反射方法 获取手机存储路径
     **/
    public static String getStoragePath(Context context, int type) {

        StorageManager sm = (StorageManager) context
                .getSystemService(Context.STORAGE_SERVICE);
        try {
            Method getPathsMethod = sm.getClass().getMethod("getVolumePaths",
                    (Class<?>) null);
            String[] path = (String[]) getPathsMethod.invoke(sm, (Object[]) null);

            switch (type) {
                case INTERNAL_STORAGE:
                    return path[type];
                case EXTERNAL_STORAGE:
                    if (path.length > 1) {
                        return path[type];
                    } else {
                        return null;
                    }

                default:
                    break;
            }

        } catch (Exception e) {
            // e.printStackTrace(); 打印太多无用log了
            // nope
        }
        return null;
    }

    /**
     * 获取 手机 RAM 信息 方法 一
     */
    public static String getTotalRAM(Context context) {
        long size = 0;
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo outInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(outInfo);
        size = outInfo.totalMem;

        return Formatter.formatFileSize(context, size);
    }

    /**
     * 手机 RAM 信息 方法 二
     */
    public static String getTotalRAMOther(Context context) {
        String path = "/proc/meminfo";
        String firstLine = null;
        int totalRam = 0;
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader br = new BufferedReader(fileReader, 8192);
            firstLine = br.readLine().split("\\s+")[1];
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (firstLine != null) {

            totalRam = (int) Math.ceil((new Float(Float.valueOf(firstLine)
                    / (1024 * 1024)).doubleValue()));

            long totalBytes = 0;

        }

        return Formatter.formatFileSize(context, totalRam);
    }

    /**
     * 获取 手机 可用 RAM
     */
    public static String getAvailableRAM(Context context) {
        long size = 0;
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo outInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(outInfo);
        size = outInfo.availMem;

        return Formatter.formatFileSize(context, size);
    }

    /**
     * 获取手机内部存储空间
     *
     * @param context
     * @return 以M, G为单位的容量
     */
    public static String getTotalInternalMemorySize(Context context) {
        File file = Environment.getDataDirectory();
        StatFs statFs = new StatFs(file.getPath());
        long blockSizeLong = statFs.getBlockSizeLong();
        long blockCountLong = statFs.getBlockCountLong();
        long size = blockCountLong * blockSizeLong;
        return Formatter.formatFileSize(context, size);
    }

    /**
     * 获取手机内部可用存储空间
     *
     * @param context
     * @return 以M, G为单位的容量
     */
    public static String getAvailableInternalMemorySize(Context context) {
        File file = Environment.getDataDirectory();
        StatFs statFs = new StatFs(file.getPath());
        long availableBlocksLong = statFs.getAvailableBlocksLong();
        long blockSizeLong = statFs.getBlockSizeLong();
        return Formatter.formatFileSize(context, availableBlocksLong
                * blockSizeLong);
    }

    /**
     * 获取手机外部存储空间
     *
     * @param context
     * @return 以M, G为单位的容量
     */
    public static String getTotalExternalMemorySize(Context context) {
        File file = Environment.getExternalStorageDirectory();
        StatFs statFs = new StatFs(file.getPath());
        long blockSizeLong = statFs.getBlockSizeLong();
        long blockCountLong = statFs.getBlockCountLong();
        return Formatter
                .formatFileSize(context, blockCountLong * blockSizeLong);
    }

    /**
     * 获取手机外部可用存储空间
     *
     * @param context
     * @return 以M, G为单位的容量
     */
    public static String getAvailableExternalMemorySize(Context context) {
        File file = Environment.getExternalStorageDirectory();
        StatFs statFs = new StatFs(file.getPath());
        long availableBlocksLong = statFs.getAvailableBlocksLong();
        long blockSizeLong = statFs.getBlockSizeLong();
        return Formatter.formatFileSize(context, availableBlocksLong
                * blockSizeLong);
    }

    /**
     * SD 卡信息
     */

    public static String getSDCardInfo() {

        SDCardInfo sd = new SDCardInfo();
        if (!isSDCardMount())
            return "SD card 未挂载!";

        sd.isExist = true;
        StatFs sf = new StatFs(Environment.getExternalStorageDirectory()
                .getPath());

        sd.totalBlocks = sf.getBlockCountLong();
        sd.blockByteSize = sf.getBlockSizeLong();
        sd.availableBlocks = sf.getAvailableBlocksLong();
        sd.availableBytes = sf.getAvailableBytes();
        sd.freeBlocks = sf.getFreeBlocksLong();
        sd.freeBytes = sf.getFreeBytes();
        sd.totalBytes = sf.getTotalBytes();
        return sd.toString();
    }

    public static class SDCardInfo {
        boolean isExist;
        long totalBlocks;
        long freeBlocks;
        long availableBlocks;
        long blockByteSize;
        long totalBytes;
        long freeBytes;
        long availableBytes;

        @Override
        public String toString() {
            return "isExist=" + isExist + "\ntotalBlocks=" + totalBlocks
                    + "\nfreeBlocks=" + freeBlocks + "\navailableBlocks="
                    + availableBlocks + "\nblockByteSize=" + blockByteSize
                    + "\ntotalBytes=" + totalBytes + "\nfreeBytes=" + freeBytes
                    + "\navailableBytes=" + availableBytes;
        }
    }

    // add start by wangjie for SDCard TotalStorage
    public static String getSDCardTotalStorage(long totalByte) {

        double byte2GB = totalByte / 1024.00 / 1024.00 / 1024.00;
        double totalStorage;
        if (byte2GB > 1) {
            totalStorage = Math.ceil(byte2GB);
            if (totalStorage > 1 && totalStorage < 3) {
                return 2.0 + "GB";
            } else if (totalStorage > 2 && totalStorage < 5) {
                return 4.0 + "GB";
            } else if (totalStorage >= 5 && totalStorage < 10) {
                return 8.0 + "GB";
            } else if (totalStorage >= 10 && totalStorage < 18) {
                return 16.0 + "GB";
            } else if (totalStorage >= 18 && totalStorage < 34) {
                return 32.0 + "GB";
            } else if (totalStorage >= 34 && totalStorage < 50) {
                return 48.0 + "GB";
            } else if (totalStorage >= 50 && totalStorage < 66) {
                return 64.0 + "GB";
            } else if (totalStorage >= 66 && totalStorage < 130) {
                return 128.0 + "GB";
            }
        } else {
            // below 1G return get values
            totalStorage = totalByte / 1024.00 / 1024.00;

            if (totalStorage >= 515 && totalStorage < 1024) {
                return 1 + "GB";
            } else if (totalStorage >= 260 && totalStorage < 515) {
                return 512 + "MB";
            } else if (totalStorage >= 130 && totalStorage < 260) {
                return 256 + "MB";
            } else if (totalStorage > 70 && totalStorage < 130) {
                return 128 + "MB";
            } else if (totalStorage > 50 && totalStorage < 70) {
                return 64 + "MB";
            }
        }

        return totalStorage + "GB";
    }


    static String NETWORK_OTHER = "other";
    static String NETWORK_WIFI = "wifi";
    static String NETWORK_2G = "2g";
    static String NETWORK_3G = "3g";
    static String NETWORK_4G = "4g";
    static String NETWORK_5G = "5g";

    public static String getNetworkState(Context context) {

        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE); // 获取网络服务

        if (null == connManager) { // 为空则认为无网络

            return NETWORK_OTHER;

        }

        // 获取网络类型，如果为空，返回无网络

        NetworkInfo activeNetInfo = connManager.getActiveNetworkInfo();

        if (activeNetInfo == null || !activeNetInfo.isAvailable()) {

            return NETWORK_OTHER;

        }

        // 判断是否为WIFI

        NetworkInfo wifiInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (null != wifiInfo) {

            NetworkInfo.State state = wifiInfo.getState();

            if (null != state) {

                if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {

                    return NETWORK_WIFI;

                }

            }

        }

        // 若不是WIFI，则去判断是2G、3G、4G网

        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        if (telephonyManager == null) return NETWORK_OTHER;

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return NETWORK_OTHER;
        } else {

            int networkType = telephonyManager.getNetworkType();

            switch (networkType) {

                // 2G网络

                case TelephonyManager.NETWORK_TYPE_GPRS:

                case TelephonyManager.NETWORK_TYPE_CDMA:

                case TelephonyManager.NETWORK_TYPE_EDGE:

                case TelephonyManager.NETWORK_TYPE_1xRTT:

                case TelephonyManager.NETWORK_TYPE_IDEN:

                    return NETWORK_2G;

                // 3G网络

                case TelephonyManager.NETWORK_TYPE_EVDO_A:

                case TelephonyManager.NETWORK_TYPE_UMTS:

                case TelephonyManager.NETWORK_TYPE_EVDO_0:

                case TelephonyManager.NETWORK_TYPE_HSDPA:

                case TelephonyManager.NETWORK_TYPE_HSUPA:

                case TelephonyManager.NETWORK_TYPE_HSPA:

                case TelephonyManager.NETWORK_TYPE_EVDO_B:

                case TelephonyManager.NETWORK_TYPE_EHRPD:

                case TelephonyManager.NETWORK_TYPE_HSPAP:

                    return NETWORK_3G;

                // 4G网络

                case TelephonyManager.NETWORK_TYPE_LTE:

                case 19:// 聚波载合 4G+

                    return NETWORK_4G;

                // 5G

                // case TelephonyManager.NETWORK_TYPE_NR:// 需要 SdkVersion>=29

                case 20:// 当 SdkVersion<=28 直接写20

                    return NETWORK_5G;

                default:

                    return NETWORK_OTHER;

            }
        }

    }

    public static SimInfo getSimInfo(Context context) {
        SimInfo simInfo = new SimInfo();
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            simInfo.setSimCountryIso(tm.getSimCountryIso());
            simInfo.setSimOperator(tm.getSimOperator());
            simInfo.setSimOperatorName(tm.getSimOperatorName());
            simInfo.setSimSerialNumber(tm.getSimSerialNumber());
            simInfo.setSubscriberId(tm.getSubscriberId());
            Logger.t("DeviceInfoUtils").i("getSimInfo------" + simInfo);
        } catch (Exception e) {
            simInfo.setSimOperator("unknown");
            simInfo.setSimOperatorName("unknown");
            simInfo.setSimSerialNumber("unknown");
            simInfo.setSubscriberId("unknown");
            simInfo.setSimCountryIso("unknown");
            Logger.t("DeviceInfoUtils").e("getSimInfo------" + e.getMessage());
            Logger.t("DeviceInfoUtils").i("getSimInfo------" + simInfo);
        }
        return simInfo;
    }
}
