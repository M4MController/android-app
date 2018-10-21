package com.m4m.lieroz.m4m_mobile.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sensor {

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
//                @SerializedName("address")
            @SerializedName("addres")
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
//            @SerializedName("payment_id")
        @SerializedName("paiment_id")
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
