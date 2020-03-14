package htxf.htb.VO;

import java.util.List;

/**
 * dayPlan VO
 * htxf 20200302
 **/
public class DayPlanVO {
    // 上午、下午、晚上
    private String timeZone;
    private List<PlanVO> plans;

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public List<PlanVO> getPlans() {
        return plans;
    }

    public void setPlans(List<PlanVO> plans) {
        this.plans = plans;
    }
}
