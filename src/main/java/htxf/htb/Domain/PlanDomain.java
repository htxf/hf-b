package htxf.htb.Domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Plan Domain
 * htxf 20200302
 * */
@Entity
@Table(name = "plan")
public class PlanDomain implements Serializable {

    private static final long serialVersionUID = 1L;

    private long userId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long planId;
    // 0-每日 1-每周 2-每月
    private String type;
    private String content;
    private String startTime;
    private String stopTime;
    // 持续时间
    private String duration;
    private String createTime;
    // 0-未删除 1-已删除
    private String deleteFlag;
    private String deleteTime;
    private String lastEditTime;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getPlanId() {
        return planId;
    }

    public void setPlanId(long planId) {
        this.planId = planId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getLastEditTime() { return lastEditTime; }

    public void setLastEditTime(String lastEditTime) { this.lastEditTime = lastEditTime; }
}
