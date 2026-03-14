package com.common.commonutils;

import android.text.TextUtils;

import androidx.annotation.NonNull;

public class SimInfo {
    private String simCountryIso = "nosim";
    private String simOperator = "nosim";
    private String simOperatorName = "nosim";
    private String simSerialNumber = "nosim";
    private String subscriberId = "nosim";

    public String getSimCountryIso() {
        return simCountryIso;
    }

    public void setSimCountryIso(String simCountryIso) {
        if (TextUtils.isEmpty(simCountryIso)) {
            return;
        }
        this.simCountryIso = simCountryIso;
    }

    public String getSimOperator() {
        return simOperator;
    }

    public void setSimOperator(String simOperator) {
        if (TextUtils.isEmpty(simOperator)) {
            return;
        }
        this.simOperator = simOperator;
    }

    public String getSimOperatorName() {
        return simOperatorName;
    }

    public void setSimOperatorName(String simOperatorName) {
        if (TextUtils.isEmpty(simOperatorName)) {
            return;
        }
        this.simOperatorName = simOperatorName;
    }

    public String getSimSerialNumber() {
        return simSerialNumber;
    }

    public void setSimSerialNumber(String simSerialNumber) {
        if (TextUtils.isEmpty(simSerialNumber)) {
            return;
        }
        this.simSerialNumber = simSerialNumber;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        if (TextUtils.isEmpty(subscriberId)) {
            return;
        }
        this.subscriberId = subscriberId;
    }

    @NonNull
    @Override
    public String toString() {
        return "SimInfo{" +
                "simCountryIso='" + simCountryIso + '\'' +
                ", simOperator='" + simOperator + '\'' +
                ", simOperatorName='" + simOperatorName + '\'' +
                ", simSerialNumber='" + simSerialNumber + '\'' +
                ", subscriberId='" + subscriberId + '\'' +
                '}';
    }
}
