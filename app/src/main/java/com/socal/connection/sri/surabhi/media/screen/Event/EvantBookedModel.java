package com.socal.connection.sri.surabhi.media.screen.Event;

/**
 * Created by vivek on 01/01/18.
 */

public class EvantBookedModel {
    public String getPlane_id() {
        return plane_id;
    }

    public void setPlane_id(String plane_id) {
        this.plane_id = plane_id;
    }

    String plane_id;
    String title;
    String bookNow;
    String location1;
    String book_close_time;
    String total_booked;
    String pics;

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    String overview;

    public EvantBookedModel(String plane_id, String title, String location1, String book_close_time,
                            String total_booked, String pics, String bookNow, String overview) {
        this.plane_id = plane_id;
        this.title = title;
        this.location1 = location1;
        this.book_close_time = book_close_time;
        this.total_booked = total_booked;
        this.pics = pics;
        this.bookNow = bookNow;
        this.overview = overview;
    }

    public String getBookNow() {
        return bookNow;
    }

    public void setBookNow(String bookNow) {
        this.bookNow = bookNow;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation1() {
        return location1;
    }

    public void setLocation1(String location1) {
        this.location1 = location1;
    }

    public String getBook_close_time() {
        return book_close_time;
    }

    public void setBook_close_time(String book_close_time) {
        this.book_close_time = book_close_time;
    }

    public String getTotal_booked() {
        return total_booked;
    }

    public void setTotal_booked(String total_booked) {
        this.total_booked = total_booked;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }
}
