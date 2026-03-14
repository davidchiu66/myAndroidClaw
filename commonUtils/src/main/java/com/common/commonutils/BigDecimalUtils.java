package com.common.commonutils;

import java.math.BigDecimal;

public class BigDecimalUtils {

    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double div(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static  String getPrice(int price){
        String newPrice = "0";
        float p1 = price / 100f;
        String p2 = String.valueOf(p1);
        String p3 = p2.substring(p2.length() - 1);
        if (p3.equals("0")) {
            int p4 = (int) p1;
            newPrice = String.valueOf(p4);
        }else {
            newPrice = p2;
        }
        return newPrice;
    }
}
