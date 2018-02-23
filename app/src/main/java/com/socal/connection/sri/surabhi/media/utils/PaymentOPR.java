//package com.socal.connection.sri.surabhi.media.utils;
//
//import android.app.Activity;
//import android.content.Intent;
//
//import com.payu.india.Extras.PayUChecksum;
//import com.payu.india.Model.PaymentParams;
//import com.payu.india.Model.PayuConfig;
//import com.payu.india.Model.PayuHashes;
//import com.payu.india.Model.PostData;
//import com.payu.india.Payu.PayuConstants;
//import com.payu.india.Payu.PayuErrors;
//import com.payu.payuui.Activity.PayUBaseActivity;
//
//public class PaymentOPR {
//    // This sets the configuration
//    private static PayuConfig payuConfig;
//    private static PaymentParams mPaymentParams;
//    // Used when generating hash from SDK
//    private static PayUChecksum checksum;
//
//    /**
//     * This method prepares all the payments params to be sent to PayuBaseActivity.java
//     */
//    public static void callPaymentProcess(Activity activity) {
//
//        String merchantKey = "jcbKgWCF";
//        String amount = "1.20";
//        String email = "vivekjhahsp@gmail.com";
//
////        String value = "1.20";
////        int environment;
////        String TEST_ENVIRONMENT = getResources().getString(R.string.test);
////        if (value.equals(TEST_ENVIRONMENT))
////            environment = PayuConstants.STAGING_ENV;
////        else
//        int environment = PayuConstants.PRODUCTION_ENV;
//
//        String userCredentials = merchantKey + ":" + email;
//
//        //TODO Below are mandatory params for hash genetation
//        mPaymentParams = new PaymentParams();
//        /**
//         * For Test Environment, merchantKey = "gtKFFx"
//         * For Production Environment, merchantKey should be your live key or for testing in live you can use "0MQaQP"
//         */
//        mPaymentParams.setKey(merchantKey);
//        mPaymentParams.setAmount(amount);
//        mPaymentParams.setProductInfo("test_product_info");
//        mPaymentParams.setFirstName("vivek");
//        mPaymentParams.setEmail(email);
//
//        /*
//        * Transaction Id should be kept unique for each transaction.
//        * */
//        mPaymentParams.setTxnId("" + System.currentTimeMillis());
//
//        /**
//         * Surl --> Success url is where the transaction response is posted by PayU on successful transaction
//         * Furl --> Failre url is where the transaction response is posted by PayU on failed transaction
//         */
//        mPaymentParams.setSurl("http://monkhub.com/isc/admin/json/money-added.php");
//        mPaymentParams.setFurl("http://monkhub.com/isc/admin/json/failure.php");
//
//        /*
//         * udf1 to udf5 are options params where you can pass additional information related to transaction.
//         * If you don't want to use it, then send them as empty string like, udf1=""
//         * */
//        mPaymentParams.setUdf1("udf1");
//        mPaymentParams.setUdf2("udf2");
//        mPaymentParams.setUdf3("udf3");
//        mPaymentParams.setUdf4("udf4");
//        mPaymentParams.setUdf5("udf5");
//
//        /**
//         * These are used for store card feature. If you are not using it then user_credentials = "default"
//         * user_credentials takes of the form like user_credentials = "merchant_key : user_id"
//         * here merchant_key = your merchant key,
//         * user_id = unique id related to user like, email, phone number, etc.
//         * */
//        mPaymentParams.setUserCredentials(userCredentials);
//
//        //TODO Pass this param only if using offer key
//        //mPaymentParams.setOfferKey("cardnumber@8370");
//
//        //TODO Sets the payment environment in PayuConfig object
//        payuConfig = new PayuConfig();
//        payuConfig.setEnvironment(environment);
//
//        //TODO It is recommended to generate hash from server only. Keep your key and salt in server side hash generation code.
////        generateHashFromServer(mPaymentParams);
//
//        /**
//         * Below approach for generating hash is not recommended. However, this approach can be used to test in PRODUCTION_ENV
//         * if your server side hash generation code is not completely setup. While going live this approach for hash generation
//         * should not be used.
//         * */
//        //String salt = "13p0PXZk";
//        PayuHashes hash =generateHashFromSDK(mPaymentParams, "RO0ihhsEfB");
////        PayuHashes hash = generateHashFromSDK(mPaymentParams, salt);
//        mPaymentParams.setHash(hash.getPaymentHash());
//
//        Intent intent = new Intent(activity, PayUBaseActivity.class);
//        intent.putExtra(PayuConstants.PAYU_CONFIG, payuConfig);
//        intent.putExtra(PayuConstants.PAYMENT_PARAMS, mPaymentParams);
//        intent.putExtra(PayuConstants.PAYU_HASHES, hash);
//        intent.putExtra(PayuConstants.STORE_ONE_CLICK_HASH, PayuConstants.STORE_ONE_CLICK_HASH_MOBILE);
//        activity.startActivityForResult(intent, PayuConstants.PAYU_REQUEST_CODE);
//    }
//
////    public static boolean callPaymentProcess(Activity activity) {
////        String pg = "CC";
////        String zip = "110096";
////        String success_url = "https://payu.herokuapp.com/success";
////        String failure_url = "https://payu.herokuapp.com/failure";
////        String UDF = "Please pay money"; // User Defined Fields, you can put any data here.
////        String merchantKey = "jcbKgWCF";
////        String userCredentials = merchantKey + ":" + "vivekjhahsp@gmail.com";
//////        Intent intent = new Intent(getActivity(), PayUBaseActivity.class);
////        mPaymentParams = new PaymentParams();
////        payuConfig = new PayuConfig();
////        mPaymentParams.setKey(merchantKey);
////        mPaymentParams.setAmount("10");
////        mPaymentParams.setProductInfo("product_info");
////        mPaymentParams.setFirstName("Vivek");
////
////        mPaymentParams.setPg(pg);
////        mPaymentParams.setEmail("vivekjhahsp@gmail.com");
////        mPaymentParams.setTxnId("" + System.currentTimeMillis());
////        mPaymentParams.setZipCode(zip);
////        mPaymentParams.setUdf1(UDF);
////        mPaymentParams.setUdf2(UDF);
////        mPaymentParams.setUdf3(UDF);
////        mPaymentParams.setUdf4(UDF);
////        mPaymentParams.setUdf5(UDF);
////        mPaymentParams.setPhone("9311256488");
////        mPaymentParams.setSurl(success_url);
////        mPaymentParams.setFurl(failure_url);
////        /**
////         * These are used for store card feature. If you are not using it then user_credentials = "default"
////         * user_credentials takes of the form like user_credentials = "merchant_key : user_id"
////         * here merchant_key = your merchant key,
////         * user_id = unique id related to user like, email, phone number, etc.
////         * */
////        mPaymentParams.setUserCredentials(userCredentials);
////
////        payuConfig.setEnvironment(PayuConstants.PRODUCTION_ENV);
////
////        PayuHashes hash = generateHashFromSDK(mPaymentParams, "RO0ihhsEfB");
////        mPaymentParams.setHash(hash.getPaymentHash());
////
////        Intent intent = new Intent(activity, PayUBaseActivity.class);
////        intent.putExtra(PayuConstants.PAYU_CONFIG, payuConfig);
////        intent.putExtra(PayuConstants.PAYMENT_PARAMS, mPaymentParams);
////        intent.putExtra(PayuConstants.PAYU_HASHES, hash);
////        intent.putExtra(PayuConstants.STORE_ONE_CLICK_HASH, PayuConstants.STORE_ONE_CLICK_HASH_MOBILE);
////        activity.startActivityForResult(intent, PayuConstants.PAYU_REQUEST_CODE);
////        return false;
////    }
//
//    public static PayuHashes generateHashFromSDK(PaymentParams mPaymentParams, String salt) {
//        PayuHashes payuHashes = new PayuHashes();
//        PostData postData = new PostData();
//
//        // payment Hash;
//        checksum = null;
//        checksum = new PayUChecksum();
//        checksum.setAmount(mPaymentParams.getAmount());
//        checksum.setKey(mPaymentParams.getKey());
//        checksum.setTxnid(mPaymentParams.getTxnId());
//        checksum.setEmail(mPaymentParams.getEmail());
//        checksum.setSalt(salt);
//        checksum.setProductinfo(mPaymentParams.getProductInfo());
//        checksum.setFirstname(mPaymentParams.getFirstName());
//        checksum.setUdf1(mPaymentParams.getUdf1());
//        checksum.setUdf2(mPaymentParams.getUdf2());
//        checksum.setUdf3(mPaymentParams.getUdf3());
//        checksum.setUdf4(mPaymentParams.getUdf4());
//        checksum.setUdf5(mPaymentParams.getUdf5());
//
//        postData = checksum.getHash();
//        if (postData.getCode() == PayuErrors.NO_ERROR) {
//            payuHashes.setPaymentHash(postData.getResult());
//        }
//
//        // checksum for payemnt related details
//        // var1 should be either user credentials or default
//        String var1 = mPaymentParams.getUserCredentials() == null ? PayuConstants.DEFAULT : mPaymentParams.getUserCredentials();
//        String key = mPaymentParams.getKey();
//
//        if ((postData = calculateHash(key, PayuConstants.PAYMENT_RELATED_DETAILS_FOR_MOBILE_SDK, var1, salt)) != null && postData.getCode() == PayuErrors.NO_ERROR) // Assign post data first then check for success
//            payuHashes.setPaymentRelatedDetailsForMobileSdkHash(postData.getResult());
//        //vas
//        if ((postData = calculateHash(key, PayuConstants.VAS_FOR_MOBILE_SDK, PayuConstants.DEFAULT, salt)) != null && postData.getCode() == PayuErrors.NO_ERROR)
//            payuHashes.setVasForMobileSdkHash(postData.getResult());
//
//        // getIbibocodes
//        if ((postData = calculateHash(key, PayuConstants.GET_MERCHANT_IBIBO_CODES, PayuConstants.DEFAULT, salt)) != null && postData.getCode() == PayuErrors.NO_ERROR)
//            payuHashes.setMerchantIbiboCodesHash(postData.getResult());
//
//        if (!var1.contentEquals(PayuConstants.DEFAULT)) {
//            // get user card
//            if ((postData = calculateHash(key, PayuConstants.GET_USER_CARDS, var1, salt)) != null && postData.getCode() == PayuErrors.NO_ERROR) // todo rename storedc ard
//                payuHashes.setStoredCardsHash(postData.getResult());
//            // save user card
//            if ((postData = calculateHash(key, PayuConstants.SAVE_USER_CARD, var1, salt)) != null && postData.getCode() == PayuErrors.NO_ERROR)
//                payuHashes.setSaveCardHash(postData.getResult());
//            // delete user card
//            if ((postData = calculateHash(key, PayuConstants.DELETE_USER_CARD, var1, salt)) != null && postData.getCode() == PayuErrors.NO_ERROR)
//                payuHashes.setDeleteCardHash(postData.getResult());
//            // edit user card
//            if ((postData = calculateHash(key, PayuConstants.EDIT_USER_CARD, var1, salt)) != null && postData.getCode() == PayuErrors.NO_ERROR)
//                payuHashes.setEditCardHash(postData.getResult());
//        }
//
//        if (mPaymentParams.getOfferKey() != null) {
//            postData = calculateHash(key, PayuConstants.OFFER_KEY, mPaymentParams.getOfferKey(), salt);
//            if (postData.getCode() == PayuErrors.NO_ERROR) {
//                payuHashes.setCheckOfferStatusHash(postData.getResult());
//            }
//        }
//
//        if (mPaymentParams.getOfferKey() != null && (postData = calculateHash(key, PayuConstants.CHECK_OFFER_STATUS, mPaymentParams.getOfferKey(), salt)) != null && postData.getCode() == PayuErrors.NO_ERROR) {
//            payuHashes.setCheckOfferStatusHash(postData.getResult());
//        }
//
//        // we have generated all the hases now lest launch sdk's ui
//
//        return payuHashes;
//    }
//
//    // deprecated, should be used only for testing.
//    private static PostData calculateHash(String key, String command, String var1, String salt) {
//        checksum = null;
//        checksum = new PayUChecksum();
//        checksum.setKey(key);
//        checksum.setCommand(command);
//        checksum.setVar1(var1);
//        checksum.setSalt(salt);
//        return checksum.getHash();
//    }
//}
