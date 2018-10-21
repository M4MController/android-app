package com.m4m.lieroz.m4m_mobile.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Controller {

    @Expose
    @SerializedName("object_id")
    private int objectId;

    @Expose
    @SerializedName("meta")
    private String meta;

    @Expose
    @SerializedName("payments")
    private Payments payments;

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("controller_type")
    private int controllerType;

    @Expose
    @SerializedName("mac")
    private String macAddress;

    @Expose
    @SerializedName("status")
    private int status;

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("activation_date")
    private Long activationDate;

    @Expose
    @SerializedName("deactivation_date")
    private Long deactivationDate;

    public int getObjectId() {
        return objectId;
    }

    public String getMeta() {
        return meta;
    }

    public Payments getPayments() {
        return payments;
    }

    public int getId() {
        return id;
    }

    public int getControllerType() {
        return controllerType;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public int getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public Long getActivationDate() {
        return activationDate;
    }

    public Long getDeactivationDate() {
        return deactivationDate;
    }
}