package com.socal.connection.sri.surabhi.media.screen.News;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.com.single.activity.sample.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by vivek on 30/12/17.
 */

public class NewsAdaptor extends RecyclerView.Adapter<NewsAdaptor.MyViewHolder> {

    private List<NesModel> newsModelList;
    private Activity context;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title_text_view, details_text_view;
        public TextView author_text_view, time_text_view;
        public ImageView new_img_details;


        public MyViewHolder(View view) {
            super(view);
            new_img_details = (ImageView) view.findViewById(R.id.new_img_details);
            title_text_view = (TextView) view.findViewById(R.id.title_text_view);
            details_text_view = (TextView) view.findViewById(R.id.details_text_view);
            author_text_view = (TextView) view.findViewById(R.id.author_text_view);
            time_text_view = (TextView) view.findViewById(R.id.time_text_view);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.details_btn) {
//                onDetailsDialog(getAdapterPosition());
            } else if (view.getId() == R.id.book_now_btn) {
//                try {
//                    JSONObject obj = new JSONObject(SharedPref.getString(context, Common.Login_CREDENTIALS_TAG));
//                    onBookNowBtn(newsModelList.get(getAdapterPosition()).getPlane_id(), obj.getString("email"));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }

        }
    }

    public NewsAdaptor(Activity context, List<NesModel> EvantBookedsList) {
        this.context = context;
        this.newsModelList = EvantBookedsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news_details, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NesModel EvantBooked = newsModelList.get(position);
        Picasso.with(context).load(EvantBooked.getImg()).into(holder.new_img_details);
        holder.title_text_view.setText(EvantBooked.getHeadLine());
        holder.details_text_view.setText(EvantBooked.getNews());
        holder.author_text_view.setText("Author : "+EvantBooked.getAuthor());
        holder.time_text_view.setText(EvantBooked.getTimings());
    }

    @Override
    public int getItemCount() {
        return newsModelList.size();
    }
}
