
package com.vironit.android.weather.dto.belrosstrah;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WorktimeArr {

    @SerializedName("day")
    @Expose
    private Integer day;
    @SerializedName("starttime")
    @Expose
    private String starttime;
    @SerializedName("finishtime")
    @Expose
    private String finishtime;

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(String finishtime) {
        this.finishtime = finishtime;
    }

}
