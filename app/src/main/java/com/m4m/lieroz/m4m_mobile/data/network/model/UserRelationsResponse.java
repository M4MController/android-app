package com.m4m.lieroz.m4m_mobile.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserRelationsResponse {

    @Expose
    @SerializedName("code")
    private int statusCode;

    @Expose
    @SerializedName("msg")
    private Message message;

    public int getStatusCode() {
        return statusCode;
    }

    public Message getMessage() {
        return message;
    }

    public static class Message {

        @Expose
        @SerializedName("sensors")
        private List<Sensor> sensors;

        @Expose
        @SerializedName("controllers")
        private List<Controller> controllers;

        @Expose
        @SerializedName("objects")
        private List<Object> objects;

        public List<Sensor> getSensors() {
            return sensors;
        }

        public List<Controller> getControllers() {
            return controllers;
        }

        public List<Object> getObjects() {
            return objects;
        }
    }

    public static class Sensor {

        @Expose
        @SerializedName("name")
        private String name;

        @Expose
        @SerializedName("id")
        private int id;

        @Expose
        @SerializedName("sensor_type")
        private int sensorType;

        @Expose
        @SerializedName("deactivation_date")
        private Long deactivationDate;

        @Expose
        @SerializedName("company")
        private String company;

        @Expose
        @SerializedName("activation_date")
        private Long activationDate;

        @Expose
        @SerializedName("last_value")
        private int lastValue;

        @Expose
        @SerializedName("controller_id")
        private int controllerId;

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public int getSensorType() {
            return sensorType;
        }

        public Long getDeactivationDate() {
            return deactivationDate;
        }

        public String getCompany() {
            return company;
        }

        public Long getActivationDate() {
            return activationDate;
        }

        public int getLastValue() {
            return lastValue;
        }

        public int getControllerId() {
            return controllerId;
        }
    }

    public static class Controller {

        @Expose
        @SerializedName("name")
        private String name;

        @Expose
        @SerializedName("mac")
        private String macAddress;

        @Expose
        @SerializedName("deactivation_date")
        private Long deactivationDate;

        @Expose
        @SerializedName("id")
        private int id;

        @Expose
        @SerializedName("controller_type")
        private int controllerType;

        @Expose
        @SerializedName("object_id")
        private int objectId;

        @Expose
        @SerializedName("activation_date")
        private Long activationDate;

        @Expose
        @SerializedName("meta")
        private String meta;

        @Expose
        @SerializedName("status")
        private int status;

        public String getName() {
            return name;
        }

        public String getMacAddress() {
            return macAddress;
        }

        public Long getDeactivationDate() {
            return deactivationDate;
        }

        public int getId() {
            return id;
        }

        public int getControllerType() {
            return controllerType;
        }

        public int getObjectId() {
            return objectId;
        }

        public Long getActivationDate() {
            return activationDate;
        }

        public String getMeta() {
            return meta;
        }

        public int getStatus() {
            return status;
        }
    }

    public static class Object {

        @Expose
        @SerializedName("adres")
        private String address;

        @Expose
        @SerializedName("id")
        private int id;

        @Expose
        @SerializedName("name")
        private String name;

        @Expose
        @SerializedName("user_id")
        private int user_id;

        public String getAddress() {
            return address;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getUser_id() {
            return user_id;
        }
    }
}
