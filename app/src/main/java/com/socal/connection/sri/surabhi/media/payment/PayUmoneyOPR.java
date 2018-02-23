package com.socal.connection.sri.surabhi.media.payment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.com.single.activity.sample.R;
import com.payumoney.core.PayUmoneyConfig;
import com.payumoney.core.PayUmoneyConstants;
import com.payumoney.core.PayUmoneySdkInitializer;
import com.payumoney.core.entity.TransactionResponse;
import com.payumoney.sdkui.ui.activities.BaseActivity;
import com.payumoney.sdkui.ui.utils.PPConfig;
import com.payumoney.sdkui.ui.utils.PayUmoneyFlowManager;
import com.payumoney.sdkui.ui.utils.ResultModel;
import com.socal.connection.sri.surabhi.media.controller.SriSurabhiActivity;
import com.socal.connection.sri.surabhi.media.controller.SriSurabhiApp;
import com.socal.connection.sri.surabhi.media.payment.PayUmoney.AppEnvironment;
import com.socal.connection.sri.surabhi.media.payment.PayUmoney.AppPreference;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PayUmoneyOPR {

    private static PayUmoneySdkInitializer.PaymentParam mPaymentParams;

    public static String hashCal(String str) {
        byte[] hashseq = str.getBytes();
        StringBuilder hexString = new StringBuilder();
        try {
            MessageDigest algorithm = MessageDigest.getInstance("SHA-512");
            algorithm.reset();
            algorithm.update(hashseq);
            byte messageDigest[] = algorithm.digest();
            for (byte aMessageDigest : messageDigest) {
                String hex = Integer.toHexString(0xFF & aMessageDigest);
                if (hex.length() == 1) {
                    hexString.append("0");
                }
                hexString.append(hex);
            }
        } catch (NoSuchAlgorithmException ignored) {
        }
        return hexString.toString();
    }

    /**
     * This function prepares the data for payment and launches payumoney plug n play sdk
     */
    public static void launchPayUMoneyFlowDonation(Activity activity, String title, String statusMessage, String name,
                                           String amountValue, String phone, String email) {

        PayUmoneyConfig payUmoneyConfig = PayUmoneyConfig.getInstance();

        //Use this to set your custom text on result screen button
        payUmoneyConfig.setDoneButtonText(statusMessage);

        //Use this to set your custom title for the activity
        payUmoneyConfig.setPayUmoneyActivityTitle(title);

        PayUmoneySdkInitializer.PaymentParam.Builder builder = new PayUmoneySdkInitializer.PaymentParam.Builder();

        double amount = 0;
        try {
            amount = Double.parseDouble(amountValue);

        } catch (Exception e) {
            e.printStackTrace();
        }
        String txnId = System.currentTimeMillis() + "";
//        String productName = SriSurabhiActivity.getInstance().mAppPreference.getProductInfo();
        String udf1 = "";
        String udf2 = "";
        String udf3 = "";
        String udf4 = "";
        String udf5 = "";
        String udf6 = "";
        String udf7 = "";
        String udf8 = "";
        String udf9 = "";
        String udf10 = "";

        AppEnvironment appEnvironment = ((SriSurabhiApp) activity.getApplication()).getAppEnvironment();
        builder.setAmount(amount)
                .setTxnId(txnId)
                .setPhone(phone)
                .setProductName(statusMessage)
                .setFirstName(name)
                .setEmail(email)
                .setsUrl(appEnvironment.surl())
                .setfUrl(appEnvironment.furl())
                .setUdf1(udf1)
                .setUdf2(udf2)
                .setUdf3(udf3)
                .setUdf4(udf4)
                .setUdf5(udf5)
                .setUdf6(udf6)
                .setUdf7(udf7)
                .setUdf8(udf8)
                .setUdf9(udf9)
                .setUdf10(udf10)
                .setIsDebug(appEnvironment.debug())
                .setKey(appEnvironment.merchant_Key())
                .setMerchantId(appEnvironment.merchant_ID());

        try {
            mPaymentParams = builder.build();

            /*
             *//**
             * Do not use below code when going live
             * Below code is provided to generate hash from sdk.
             * It is recommended to generate hash from server side only.
             * *//*
             * */
            mPaymentParams = calculateServerSideHashAndInitiatePayment1(activity, mPaymentParams);
            PayUmoneyFlowManager.startPayUMoneyFlow(mPaymentParams, activity,
                    R.style.AppTheme_default, SriSurabhiActivity.getInstance().mAppPreference.isOverrideResultScreen());

        } catch (Exception e) {
            // some exception occurred
            Toast.makeText(activity, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * This function prepares the data for payment and launches payumoney plug n play sdk
     */
    public static void launchPayUMoneyFlow(Activity activity, String title, String statusMessage,
                                           String amountValue, String phone, String email) {

        PayUmoneyConfig payUmoneyConfig = PayUmoneyConfig.getInstance();

        //Use this to set your custom text on result screen button
        payUmoneyConfig.setDoneButtonText(statusMessage);

        //Use this to set your custom title for the activity
        payUmoneyConfig.setPayUmoneyActivityTitle(title);

        PayUmoneySdkInitializer.PaymentParam.Builder builder = new PayUmoneySdkInitializer.PaymentParam.Builder();

        double amount = 0;
        try {
            amount = Double.parseDouble(amountValue);

        } catch (Exception e) {
            e.printStackTrace();
        }
        String txnId = System.currentTimeMillis() + "";
        String productName = SriSurabhiActivity.getInstance().mAppPreference.getProductInfo();
        String firstName = SriSurabhiActivity.getInstance().mAppPreference.getFirstName();
        String udf1 = "";
        String udf2 = "";
        String udf3 = "";
        String udf4 = "";
        String udf5 = "";
        String udf6 = "";
        String udf7 = "";
        String udf8 = "";
        String udf9 = "";
        String udf10 = "";

        AppEnvironment appEnvironment = ((SriSurabhiApp) activity.getApplication()).getAppEnvironment();
        builder.setAmount(amount)
                .setTxnId(txnId)
                .setPhone(phone)
                .setProductName(productName)
                .setFirstName(firstName)
                .setEmail(email)
                .setsUrl(appEnvironment.surl())
                .setfUrl(appEnvironment.furl())
                .setUdf1(udf1)
                .setUdf2(udf2)
                .setUdf3(udf3)
                .setUdf4(udf4)
                .setUdf5(udf5)
                .setUdf6(udf6)
                .setUdf7(udf7)
                .setUdf8(udf8)
                .setUdf9(udf9)
                .setUdf10(udf10)
                .setIsDebug(appEnvironment.debug())
                .setKey(appEnvironment.merchant_Key())
                .setMerchantId(appEnvironment.merchant_ID());

        try {
            mPaymentParams = builder.build();

            /*
             *//**
             * Do not use below code when going live
             * Below code is provided to generate hash from sdk.
             * It is recommended to generate hash from server side only.
             * *//*
             * */
            mPaymentParams = calculateServerSideHashAndInitiatePayment1(activity, mPaymentParams);
            PayUmoneyFlowManager.startPayUMoneyFlow(mPaymentParams, activity,
                    R.style.AppTheme_default, SriSurabhiActivity.getInstance().mAppPreference.isOverrideResultScreen());

        } catch (Exception e) {
            // some exception occurred
            Toast.makeText(activity, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Thus function calculates the hash for transaction
     *
     * @param paymentParam payment params of transaction
     * @return payment params along with calculated merchant hash
     */
    private static PayUmoneySdkInitializer.PaymentParam
    calculateServerSideHashAndInitiatePayment1(Activity activity, final PayUmoneySdkInitializer.PaymentParam paymentParam) {

        StringBuilder stringBuilder = new StringBuilder();
        HashMap<String, String> params = paymentParam.getParams();
        stringBuilder.append(params.get(PayUmoneyConstants.KEY) + "|");
        stringBuilder.append(params.get(PayUmoneyConstants.TXNID) + "|");
        stringBuilder.append(params.get(PayUmoneyConstants.AMOUNT) + "|");
        stringBuilder.append(params.get(PayUmoneyConstants.PRODUCT_INFO) + "|");
        stringBuilder.append(params.get(PayUmoneyConstants.FIRSTNAME) + "|");
        stringBuilder.append(params.get(PayUmoneyConstants.EMAIL) + "|");
        stringBuilder.append(params.get(PayUmoneyConstants.UDF1) + "|");
        stringBuilder.append(params.get(PayUmoneyConstants.UDF2) + "|");
        stringBuilder.append(params.get(PayUmoneyConstants.UDF3) + "|");
        stringBuilder.append(params.get(PayUmoneyConstants.UDF4) + "|");
        stringBuilder.append(params.get(PayUmoneyConstants.UDF5) + "||||||");

        AppEnvironment appEnvironment = ((SriSurabhiApp) activity.getApplication()).getAppEnvironment();
        stringBuilder.append(appEnvironment.salt());

        String hash = hashCal(stringBuilder.toString());
        paymentParam.setMerchantHash(hash);

        return paymentParam;
    }
}
