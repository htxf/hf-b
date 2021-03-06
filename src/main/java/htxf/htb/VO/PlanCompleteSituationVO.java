package htxf.htb.VO;

/**
 * plan complete situation VO
 * htxf 20200302
 **/
public class PlanCompleteSituationVO {
    private long planId;
    // 后续同样planId的内容可能被修改？
    private String recordContent;
    private String recordDate;
    // 0-未完成 1-完成
    private String completeFlag;
    private String completeTime;

    public long getPlanId() {
        return planId;
    }

    public void setPlanId(long planId) {
        this.planId = planId;
    }

    public String getRecordContent() {
        return recordContent;
    }

    public void setRecordContent(String recordContent) {
        this.recordContent = recordContent;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public String getCompleteFlag() {
        return completeFlag;
    }

    public void setCompleteFlag(String completeFlag) {
        this.completeFlag = completeFlag;
    }

    public String getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(String completeTime) {
        this.completeTime = completeTime;
    }
}
