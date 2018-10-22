package com.m4m.lieroz.m4m_mobile.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiError {

    public static class Message {

        @Expose
        @SerializedName("msg")
        private String message;

        public String getMessage() {
            return message;
        }
    }

    @Expose
    @SerializedName("code")
    private String statusCode;

    @Expose
    @SerializedName("msg")
    private Message message;

    public String getStatusCode() {
        return statusCode;
    }

    public Message getMessage() {
        return message;
    }

    @Override
    public boolean equals(java.lang.Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        ApiError apiError = (ApiError) object;

        if (statusCode != null ? !statusCode.equals(apiError.statusCode) : apiError.statusCode != null)
            return false;
        return message != null ? message.equals(apiError.message) : apiError.message == null;

    }
}
