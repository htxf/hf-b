package htxf.htb.VO;

import java.util.List;

/**
 * dayPlan VO
 * htxf 20200302
 **/
public class DayPlanVO {
    // 上午、下午、晚上
    private String timeZone;
    private List<PlanVO> planList;

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public List<PlanVO> getPlanList() {
        return planList;
    }

    public void setPlanList(List<PlanVO> planList) {
        this.planList = planList;
    }
}
