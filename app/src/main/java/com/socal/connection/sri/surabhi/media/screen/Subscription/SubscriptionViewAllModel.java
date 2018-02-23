package com.socal.connection.sri.surabhi.media.screen.Subscription;

/**
 * Created by vivek on 06/01/18.
 */

public class SubscriptionViewAllModel {
    String plan_id;
    String rate;
    String plan_type;
    String title;
    String subscriber_count;
    String my_plan;
    String overview;

    public SubscriptionViewAllModel(String plan_id, String rate, String plan_type, String title,
                                    String subscriber_count, String my_plan, String overview) {
        this.plan_id = plan_id;
        this.rate = rate;
        this.plan_type = plan_type;
        this.title = title;
        this.subscriber_count = subscriber_count;
        this.my_plan = my_plan;
        this.overview = overview;
    }



    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getPlan_type() {
        return plan_type;
    }

    public void setPlan_type(String plan_type) {
        this.plan_type = plan_type;
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

    public String getSubscriber_count() {
        return subscriber_count;
    }

    public void setSubscriber_count(String subscriber_count) {
        this.subscriber_count = subscriber_count;
    }

    public String getMy_plan() {
        return my_plan;
    }

    public void setMy_plan(String my_plan) {
        this.my_plan = my_plan;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }


}
