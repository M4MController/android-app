package com.m4m.lieroz.m4m_mobile.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Object {

    public static class Payments {

        @Expose
        @SerializedName("current_month")
        private double currentMonth;

        @Expose
        @SerializedName("prev_year")
        private double prevYear;

        @Expose
        @SerializedName("year_avg")
        private double yearAvg;

        public double getCurrentMonth() {
            return currentMonth;
        }

        public double getPrevYear() {
            return prevYear;
        }

        public double getYearAvg() {
            return yearAvg;
        }
    }

    @Expose
    @SerializedName("address")
    private String address;

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("user_id")
    private int userId;

    @Expose
    @SerializedName("payments")
    private Payments payments;

    @Expose
    @SerializedName("id")
    private int id;

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }

    public Payments getPayments() {
        return payments;
    }

    public int getId() {
        return id;
    }
}