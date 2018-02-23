package com.socal.connection.sri.surabhi.media.screen.Subscription;

/**
 * Created by vivek on 06/01/18.
 */

public class SubscriptionModel {
    String joined;
    String last_payment;
    String plan_id;
    String title;
    String rate;

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public SubscriptionModel(String joined, String last_payment, String plan_id, String title,String rate) {
        this.joined = joined;
        this.last_payment = last_payment;
        this.plan_id = plan_id;
        this.title = title;
        this.rate=rate;
    }

    public String getJoined() {
        return joined;
    }

    public void setJoined(String joined) {
        this.joined = joined;
    }

    public String getLast_payment() {
        return last_payment;
    }

    public void setLast_payment(String last_payment) {
        this.last_payment = last_payment;
    }

    public String getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(String plan_id) {
        this.plan_id = plan_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
