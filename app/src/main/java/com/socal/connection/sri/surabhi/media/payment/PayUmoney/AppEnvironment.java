package com.socal.connection.sri.surabhi.media.payment.PayUmoney;


/**
 * Created by Rahul Hooda on 14/7/17.
 */
public enum AppEnvironment {

    PRODUCTION {
        @Override
        public String merchant_Key() {
            return "LSn0fHu5";
        }

        @Override
        public String merchant_ID() {
            return "4819816";
        }

        @Override
        public String furl() {
            return "https://www.payumoney.com/mobileapp/payumoney/failure.php";
        }

        @Override
        public String surl() {
            return "https://www.payumoney.com/mobileapp/payumoney/success.php";
        }

        @Override
        public String salt() {
            return "pYQUlQyWat";
        }

        @Override
        public boolean debug() {
            return false;
        }
    };

    public abstract String merchant_Key();

    public abstract String merchant_ID();

    public abstract String furl();

    public abstract String surl();

    public abstract String salt();

    public abstract boolean debug();


}
