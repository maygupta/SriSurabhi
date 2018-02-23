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

public class SubscriptionViewAllAdaptor extends RecyclerView.Adapter<SubscriptionViewAllAdaptor.MyViewHolder> {

    private List<SubscriptionViewAllModel> evantBookedsList;
    private Activity activity;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView subAllTitle;
        public TextView subAllDescription;
        public TextView subAllRatePlane;
        public TextView subAllNoOfMember;
        public Button subAllBtn;

        public MyViewHolder(View view) {
            super(view);
            subAllTitle = (TextView) view.findViewById(R.id.sub_all_title);
            subAllDescription = (TextView) view.findViewById(R.id.sub_all_description);
            subAllRatePlane = (TextView) view.findViewById(R.id.sub_all_rate_type);
            subAllNoOfMember = (TextView) view.findViewById(R.id.sub_all_no_of_member);
            subAllBtn = (Button) view.findViewById(R.id.sub_all_btn);
            subAllBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            String[] value = new String[2];
            value[0] = evantBookedsList.get(getAdapterPosition()).getRate();
            value[1] = evantBookedsList.get(getAdapterPosition()).getPlan_id();
            if(Integer.parseInt(value[0])>0) {
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
            }else {
                try {
                    onSubscriptionViewAllAPI(evantBookedsList.get(getAdapterPosition()).getPlan_id());
                } catch (JSONException e) {
                    e.printStackTrace();
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
    }


    public SubscriptionViewAllAdaptor(Activity activity, List<SubscriptionViewAllModel> EvantBookedsList) {
        this.activity = activity;
        this.evantBookedsList = EvantBookedsList;
    }

    @Override
    public SubscriptionViewAllAdaptor.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_subscription_view_all, parent, false);

        return new SubscriptionViewAllAdaptor.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SubscriptionViewAllAdaptor.MyViewHolder holder, int position) {
        SubscriptionViewAllModel subscriptionViewAllModel = evantBookedsList.get(position);
        holder.subAllTitle.setText(subscriptionViewAllModel.getTitle().trim());
        holder.subAllDescription.setText(subscriptionViewAllModel.getOverview().trim());
        holder.subAllRatePlane.setText("Rs. " + subscriptionViewAllModel.getRate() + " (" + subscriptionViewAllModel.getPlan_type() + ")");
        holder.subAllNoOfMember.setText(subscriptionViewAllModel.getSubscriber_count() + " Members");
        if (!subscriptionViewAllModel.getMy_plan().equals("0")) {
            holder.subAllBtn.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return evantBookedsList.size();
    }
}
