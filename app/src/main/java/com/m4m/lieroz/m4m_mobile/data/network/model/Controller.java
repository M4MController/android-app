package com.m4m.lieroz.m4m_mobile.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Controller {

    public static class Payments {

        @Expose
        @SerializedName("charge")
        private double charge;

        @Expose
//        @SerializedName("overpayment")
        @SerializedName("overpaiment")
        private double overpayment;

        @Expose
//        @SerializedName("for_payment")
        @SerializedName("for_paiment")
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

    @Expose
    @SerializedName("status")
    private int status;

    @Expose
    @SerializedName("meta")
    private String meta;

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("object_id")
    private int objectId;

    @Expose
    @SerializedName("payments")
    private Payments payments;

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("deactivation_date")
    private Long deactivationDate;

    @Expose
    @SerializedName("mac")
    private String macAddress;

    @Expose
    @SerializedName("activation_date")
    private Long activationDate;

    @Expose
    @SerializedName("controller_type")
    private int controllerType;

    public int getStatus() {
        return status;
    }

    public String getMeta() {
        return meta;
    }

    public String getName() {
        return name;
    }

    public int getObjectId() {
        return objectId;
    }

    public Payments getPayments() {
        return payments;
    }

    public int getId() {
        return id;
    }

    public Long getDeactivationDate() {
        return deactivationDate;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public Long getActivationDate() {
        return activationDate;
    }

    public int getControllerType() {
        return controllerType;
    }
}