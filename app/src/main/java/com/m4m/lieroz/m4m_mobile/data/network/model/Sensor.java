package com.m4m.lieroz.m4m_mobile.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sensor {

    public class Payments {

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
            @SerializedName("address")
            private String address;

            @Expose
            @SerializedName("name")
            private String name;

            @Expose
            @SerializedName("bank_account_id")
            private String bankAccountId;

            @Expose
            @SerializedName("id")
            private int id;

            @Expose
            @SerializedName("phone")
            private String phone;

            public String getAddress() {
                return address;
            }

            public String getName() {
                return name;
            }

            public String getBankAccountId() {
                return bankAccountId;
            }

            public int getId() {
                return id;
            }

            public String getPhone() {
                return phone;
            }
        }

        public static class Tariff {

            @Expose
            @SerializedName("day")
            private double day;

            @Expose
            @SerializedName("night")
            private double night;

            public double getDay() {
                return day;
            }

            public double getNight() {
                return night;
            }
        }

        @Expose
        @SerializedName("payment_id")
        private String paymentId;

        @Expose
        @SerializedName("service_company")
        private ServiceCompany serviceCompany;

//        @Expose
//        @SerializedName("tariff")
//        private Tariff tariff;

        public String getPaymentId() {
            return paymentId;
        }

        public ServiceCompany getServiceCompany() {
            return serviceCompany;
        }

//        public Tariff getTariff() {
//            return tariff;
//        }
    }

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("payments")
    private Payments payments;

    @Expose
    @SerializedName("stats")
    private Stats stats;

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("controller_id")
    private int controllerId;

    @Expose
    @SerializedName("deactivation_date")
    private Long deactivationDate;

    @Expose
    @SerializedName("characteristics")
    private Characteristics characteristics;

    @Expose
    @SerializedName("finance")
    private Finance finance;

    @Expose
    @SerializedName("activation_date")
    private Long activationDate;

    @Expose
    @SerializedName("last_value")
    private double lastValue;

    public String getName() {
        return name;
    }

    public Payments getPayments() {
        return payments;
    }

    public Stats getStats() {
        return stats;
    }

    public int getId() {
        return id;
    }

    public int getControllerId() {
        return controllerId;
    }

    public Long getDeactivationDate() {
        return deactivationDate;
    }

    public Characteristics getCharacteristics() {
        return characteristics;
    }

    public Finance getFinance() {
        return finance;
    }

    public Long getActivationDate() {
        return activationDate;
    }

    public double getLastValue() {
        return lastValue;
    }
}
