package com.m4m.lieroz.m4m_mobile.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payments {

    @Expose
    @SerializedName("charge")
    private double charge;

    @Expose
    @SerializedName("overpayment")
    private double overpayment;

    @Expose
    @SerializedName("for_payment")
    private double forPayment;

    public double getCharge() {
        return charge;
    }

    public double getOverpayment() {
        return overpayment;
    }

    public double getForPayment() {
        return forPayment;
    }
}
