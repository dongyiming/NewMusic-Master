package com.example.uc_common_bean.vo.news;

import java.util.List;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/8/5 8:28
 */
public class LatestNews extends DailyNews {

    //顶部轮询数据
    private List<TitleNews> top_stories;

    public List<TitleNews> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TitleNews> top_stories) {
        this.top_stories = top_stories;
    }

    @Override
    public String toString() {
        return "LatestNews{" +
                ", top_stories=" + top_stories +
                '}';
    }
}
