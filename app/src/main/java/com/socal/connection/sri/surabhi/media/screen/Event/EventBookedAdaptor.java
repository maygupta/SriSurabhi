package com.socal.connection.sri.surabhi.media.screen.Event;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.demo.com.single.activity.sample.R;
import com.socal.connection.sri.surabhi.media.controller.LoginActivity;
import com.socal.connection.sri.surabhi.media.screen.ExploreSriSubhi.dialog.ExploreDialogFragment;
import com.socal.connection.sri.surabhi.media.utils.Common;
import com.socal.connection.sri.surabhi.media.utils.CommonUtil;
import com.socal.connection.sri.surabhi.media.utils.SharedPref;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by vivek on 30/12/17.
 */

public class EventBookedAdaptor extends RecyclerView.Adapter<EventBookedAdaptor.MyViewHolder> {

    private List<EvantBookedModel> EvantBookedsList;
    private Activity context;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView item_event_name, item_event_place, item_event_close_date, total_booked_going;
        public ImageView itemPics;
        public Button book_now_btn, details_btn;


        public MyViewHolder(View view) {
            super(view);
            itemPics = (ImageView) view.findViewById(R.id.item_pics);
            item_event_name = (TextView) view.findViewById(R.id.item_event_name);
            item_event_place = (TextView) view.findViewById(R.id.item_event_place);
            item_event_close_date = (TextView) view.findViewById(R.id.item_event_close_date);
            total_booked_going = (TextView) view.findViewById(R.id.total_booked_going);
            book_now_btn = (Button) view.findViewById(R.id.book_now_btn);
            book_now_btn.setOnClickListener(this);

            details_btn = (Button) view.findViewById(R.id.details_btn);
            details_btn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.details_btn) {
                onDetailsDialog(getAdapterPosition());
            } else if (view.getId() == R.id.book_now_btn) {
                try {
                    JSONObject obj = new JSONObject(SharedPref.getString(context, Common.Login_CREDENTIALS_TAG));
                    onBookNowBtn(EvantBookedsList.get(getAdapterPosition()).getPlane_id(), obj.getString("email"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private void onDetailsDialog(int position) {
        EventDetailsPopupFragment alertDialogFragment = new EventDetailsPopupFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Message", EvantBookedsList.get(position).getOverview());
        alertDialogFragment.setArguments(bundle);
        alertDialogFragment.show(context.getFragmentManager(), "fragment_edit_name");
    }

    private void onBookNowBtn(String plane_id, String email) {
        //http://monkhub.com/isc/admin/json/signup.php?email=chandanjha.723@gmail.com&password=aa&name=aman%20sharma&phone=8587777
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = Common.BOOK_NOW_URL + "email=" + email + "&plan_id=" + plane_id;

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getString("status").equals("success")) {
                                onSignUpAPIAPI();
                                CommonUtil.showBasicAlert(context, "Success...", response.getString("status_text"));
                            } else {
                                CommonUtil.showBasicAlert(context, "Error...", response.getString("status_text"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("", "Error: " + error.getMessage());
                // hide the progress dialog
            }
        });

        queue.add(jsonObjReq);
    }

    private void onSignUpAPIAPI() throws JSONException {
        CommonUtil.progressShow(context, "Loading...");
        RequestQueue queue = Volley.newRequestQueue(context);
        JSONObject obj = new JSONObject(SharedPref.getString(context, Common.Login_CREDENTIALS_TAG));
        String url = Common.UPCOMING_EVENT_URL + "show_all=" + obj.getString("email");

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            boolean isRunning = true;
                            int count = 0;
                            EvantBookedsList.clear();
                            while (isRunning) {
                                if (response.has(String.valueOf(count))) {
                                    JSONObject jsonObject = response.getJSONObject(String.valueOf(count));
                                    EvantBookedsList.add(new EvantBookedModel(jsonObject.getString("plan_id"), jsonObject.getString("title"),
                                            jsonObject.getString("location1"), jsonObject.getString("book_close_time"),
                                            jsonObject.getString("total_booked"), jsonObject.getString("image_url"),
                                            jsonObject.getString("show_book"), jsonObject.getString("overview")));
                                    ++count;
                                } else {
                                    isRunning = false;
                                }
                            }
                            notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        CommonUtil.progressDismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                CommonUtil.progressDismiss();
                CommonUtil.showBasicAlert(context, context.getString(R.string.error_dot), error.getMessage());
                VolleyLog.d("", "Error: " + error.getMessage());
                // hide the progress dialog
            }
        });

        queue.add(jsonObjReq);
    }

    public EventBookedAdaptor(Activity context, List<EvantBookedModel> EvantBookedsList) {
        this.context = context;
        this.EvantBookedsList = EvantBookedsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_event_booked, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        EvantBookedModel EvantBooked = EvantBookedsList.get(position);
        holder.item_event_name.setText(EvantBooked.getTitle());
        holder.item_event_place.setText(EvantBooked.getLocation1());
        holder.item_event_close_date.setText(EvantBooked.getBook_close_time());
        holder.total_booked_going.setText(EvantBooked.getTotal_booked() + " going");
        Picasso.with(context).load(EvantBooked.getPics()).into(holder.itemPics);
        if (Integer.parseInt(EvantBooked.getBookNow()) == 0) {
            holder.book_now_btn.setVisibility(View.GONE);
        } else {
            holder.book_now_btn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return EvantBookedsList.size();
    }
}
