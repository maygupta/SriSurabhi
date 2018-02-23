package com.socal.connection.sri.surabhi.media.screen.Subscription;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.demo.com.single.activity.sample.R;

import com.socal.connection.sri.surabhi.media.payment.PayUmoneyOPR;
import com.socal.connection.sri.surabhi.media.utils.Common;
import com.socal.connection.sri.surabhi.media.utils.CommonUtil;
import com.socal.connection.sri.surabhi.media.utils.SharedPref;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by vivek on 06/01/18.
 */

public class SubscriptionAdaptor extends RecyclerView.Adapter<SubscriptionAdaptor.MyViewHolder> {
    private Activity activity;
    private List<SubscriptionModel> evantBookedsList;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView subItemTittle;
        public TextView subItemLastPayment;
        public TextView subItemJoined;
        public Button subItemBtn;

        public MyViewHolder(View view) {
            super(view);
            subItemTittle = (TextView) view.findViewById(R.id.sub_item_tittle);
            subItemLastPayment = (TextView) view.findViewById(R.id.sub_item_last_payment);
            subItemJoined = (TextView) view.findViewById(R.id.sub_item_joined);
            subItemBtn = (Button) view.findViewById(R.id.sub_item_btn);
            subItemBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String[] value = new String[2];
            value[0] = evantBookedsList.get(getAdapterPosition()).getRate();
            value[1] = evantBookedsList.get(getAdapterPosition()).getPlan_id();
            if (Integer.parseInt(value[0]) > 0) {
                String json = SharedPref.getString(activity, Common.Login_CREDENTIALS_TAG);
                try {
                    JSONObject obj = new JSONObject(json);
                    PayUmoneyOPR.launchPayUMoneyFlowDonation(activity, evantBookedsList.get(getAdapterPosition()).getTitle(),
                            evantBookedsList.get(getAdapterPosition()).getPlan_id(),
                            obj.getString("name"),
                            evantBookedsList.get(getAdapterPosition()).getRate(),
                            obj.getString("phone"),
                            obj.getString("email"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    onSubscriptionViewAllAPI(evantBookedsList.get(getAdapterPosition()).getPlan_id());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void onSubscriptionViewAllAPI(String id) throws JSONException {
        RequestQueue queue = Volley.newRequestQueue(activity);
        JSONObject obj = new JSONObject(SharedPref.getString(activity, Common.Login_CREDENTIALS_TAG));
        String url = Common.SUBSCRIPTION_VIEW_ALL_BTN_URL + "email=" + obj.getString("email") + "&plan_id=" + id;
        CommonUtil.progressShow(activity, activity.getString(R.string.loading_dot));
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Toast.makeText(activity, response.getString("status_text"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        CommonUtil.progressDismiss();
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d("", "Error: " + error.getMessage());
                        CommonUtil.progressDismiss();
                        CommonUtil.showBasicAlert(activity, activity.getString(R.string.error_dot), error.getMessage());
                        // hide the progress dialog
                    }
                });

        queue.add(jsonObjReq);
    }

    public SubscriptionAdaptor(Activity activity, List<SubscriptionModel> evantBookedsList) {
        this.activity = activity;
        this.evantBookedsList = evantBookedsList;
    }

    @Override
    public SubscriptionAdaptor.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_subscription, parent, false);

        return new SubscriptionAdaptor.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SubscriptionAdaptor.MyViewHolder holder, int position) {
        SubscriptionModel subscriptionModel = evantBookedsList.get(position);
        holder.subItemTittle.setText(subscriptionModel.getTitle());
        holder.subItemLastPayment.setText("Payment :: " + subscriptionModel.getLast_payment());
        holder.subItemJoined.setText("Joined :: " + subscriptionModel.getJoined());
        holder.subItemBtn.setText("Renew Now @ " + subscriptionModel.getRate());
    }

    @Override
    public int getItemCount() {
        return evantBookedsList.size();
    }
}
