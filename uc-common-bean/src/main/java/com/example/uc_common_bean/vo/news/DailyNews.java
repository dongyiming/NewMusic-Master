package com.example.uc_common_bean.vo.news;

import java.util.List;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/8/5 8:34
 */
public class DailyNews {

    public String date;

    public List<News> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<News> getStories() {
        return stories;
    }

    public void setStories(List<News> stories) {
        this.stories = stories;
    }

    @Override
    public String toString() {
        return "DailyNews{" +
                "date='" + date + '\'' +
                ", stories=" + stories +
                '}';
    }
}
