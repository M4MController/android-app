package com.m4m.lieroz.m4m_mobile.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SensorDataPeriodResponse {

    public static class Data {
        @Expose
        @SerializedName("value")
        private double value;

        @Expose
        @SerializedName("hash")
        private String hash;

        @Expose
        @SerializedName("sensor_id")
        private int sensorId;

        @Expose
        @SerializedName("date")
        private String date;

        public double getValue() {
            return value;
        }

        public String getHash() {
            return hash;
        }

        public int getSensorId() {
            return sensorId;
        }

        public String getDate() {
            return date;
        }
    }

    @Expose
    @SerializedName("code")
    private int code;

    @Expose
    @SerializedName("msg")
    private List<Data> data;

    public int getCode() {
        return code;
    }

    public List<Data> getData() {
        return data;
    }
}
