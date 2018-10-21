package com.m4m.lieroz.m4m_mobile.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserRelationsResponse {

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

        @Expose
        @SerializedName("objects")
        private List<Object> objects;

        public List<Controller> getControllers() {
            return controllers;
        }

        public List<Sensor> getSensors() {
            return sensors;
        }

        public List<Object> getObjects() {
            return objects;
        }
    }
}
