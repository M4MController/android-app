package com.m4m.lieroz.m4m_mobile.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserRelationsResponse {

    public static class Payments {

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

    public static class Controller {

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

    public static class Sensor {

        public static class Stats {

            @Expose
            @SerializedName("prev_year")
            private double prevYear;

            @Expose
            @SerializedName("prev_month")
            private double prevMonth;

            @Expose
            @SerializedName("month")
            private double month;

            public double getPrevYear() {
                return prevYear;
            }

            public double getPrevMonth() {
                return prevMonth;
            }

            public double getMonth() {
                return month;
            }
        }

        public static class Characteristics {

            @Expose
            @SerializedName("sensor_type")
            private int sensorType;

            @Expose
            @SerializedName("unit_of_measurement")
            private String unitOfMeasurement;

            public int getSensorType() {
                return sensorType;
            }

            public String getUnitOfMeasurement() {
                return unitOfMeasurement;
            }
        }

        public static class Finance {

            public static class ServiceCompany {

                @Expose
                @SerializedName("name")
                private String name;

                @Expose
                @SerializedName("phone")
                private String phone;

                @Expose
                @SerializedName("address")
                private String address;

                @Expose
                @SerializedName("id")
                private int id;

                @Expose
                @SerializedName("bank_account_id")
                private String bankAccountId;

                public String getName() {
                    return name;
                }

                public String getPhone() {
                    return phone;
                }

                public String getAddress() {
                    return address;
                }

                public int getId() {
                    return id;
                }

                public String getBankAccountId() {
                    return bankAccountId;
                }
            }

            @Expose
            @SerializedName("service_company")
            private ServiceCompany serviceCompany;

            @Expose
            @SerializedName("payment_id")
            private String paymentId;

            @Expose
            @SerializedName("tariff")
            private int tariff;

            public ServiceCompany getServiceCompany() {
                return serviceCompany;
            }

            public String getPaymentId() {
                return paymentId;
            }

            public int getTariff() {
                return tariff;
            }
        }

        @Expose
        @SerializedName("stats")
        private Stats stats;

        @Expose
        @SerializedName("last_value")
        private int lastValue;

        @Expose
        @SerializedName("payments")
        private Payments payments;

        @Expose
        @SerializedName("controller_id")
        private int controllerId;

        @Expose
        @SerializedName("id")
        private int id;

        @Expose
        @SerializedName("finance")
        private Finance finance;

        @Expose
        @SerializedName("characteristics")
        private Characteristics characteristics;

        @Expose
        @SerializedName("name")
        private String name;

        @Expose
        @SerializedName("activation_date")
        private Long activationDate;

        @Expose
        @SerializedName("deactivation_date")
        private Long deactivationDate;

        public Stats getStats() {
            return stats;
        }

        public int getLastValue() {
            return lastValue;
        }

        public Payments getPayments() {
            return payments;
        }

        public int getControllerId() {
            return controllerId;
        }

        public int getId() {
            return id;
        }

        public Finance getFinance() {
            return finance;
        }

        public Characteristics getCharacteristics() {
            return characteristics;
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

    public static class Object {

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
        @SerializedName("address")
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
}
