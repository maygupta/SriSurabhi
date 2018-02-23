package com.socal.connection.sri.surabhi.media.payment.PayUmoney;

/**
 * Created by Rahul Hooda on 14/7/17.
 */

/**
 * This class keeps all the app prefernces
 * */
public class AppPreference {

    private String productInfo = "product_info";
    private String firstName = "firstname";
    private boolean isOverrideResultScreen = false;
 
    public boolean isOverrideResultScreen() {
        return isOverrideResultScreen;
    }


    public String getProductInfo() {
        return productInfo;
    }

    public String getFirstName() {
        return firstName;
    }

}
