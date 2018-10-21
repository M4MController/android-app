package com.m4m.lieroz.m4m_mobile.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Object {

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("payments")
    private Payments payments;

    @Expose
    @SerializedName("user_id")
    private int user_id;

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
//        @SerializedName("address")
    @SerializedName("adres")
    private String address;

    public String getName() {
        return name;
    }

    public Payments getPayments() {
        return payments;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }
}