package com.socal.connection.sri.surabhi.media.screen.News;

/**
 * Created by vivek on 30/01/18.
 */

public class NesModel {
    String headLine;
    String img;
    String news;

    public String getHeadLine() {
        return headLine;
    }

    public void setHeadLine(String headLine) {
        this.headLine = headLine;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public NesModel(String headLine, String img, String news, String author, String timings) {
        this.headLine = headLine;
        this.img = img;
        this.news = news;
        this.author = author;
        this.timings = timings;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    String timings;
    String author;
}
