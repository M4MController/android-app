package com.m4m.lieroz.m4m_mobile.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ObjectRelationsResponse {

    @Expose
    @SerializedName("msg")
    private Message message;

    @Expose
    @SerializedName("code")
    private int statusCode;

    public Message getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public static class Message {

        @Expose
        @SerializedName("controllers")
        private List<Controller> controllers;

        @Expose
        @SerializedName("sensors")
        private List<Sensor> sensors;

        public List<Controller> getControllers() {
            return controllers;
        }

        public List<Sensor> getSensors() {
            return sensors;
        }
    }
}
