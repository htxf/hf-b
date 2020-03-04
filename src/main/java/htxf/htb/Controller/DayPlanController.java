package htxf.htb.Controller;

import htxf.htb.Logic.DayPlanLogic;
import htxf.htb.VO.DayPlanVO;
import htxf.htb.VO.PlanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * day plan controller
 * htxf 20200302
 * */
@RestController
@RequestMapping("/dayPlans")
public class DayPlanController {
    @Autowired
    private DayPlanLogic dayPlanLogic;

    // 增
    @RequestMapping("/insert")
    public long insertPlan(@RequestBody PlanVO vo) {
        return dayPlanLogic.insertPlan(vo);
    }
    // 删
    @RequestMapping("/delete")
    public String deletePlan(@RequestParam String planId) {
        System.out.println(planId);
        long realPlanId = Long.valueOf(planId);
        return dayPlanLogic.deletePlan(realPlanId);
    }
    // 改
    @RequestMapping("/edit")
    public String editPlan(@RequestBody PlanVO vo) {
        return dayPlanLogic.editPlan(vo);
    }
    // 查
    @RequestMapping("/query")
    public List<DayPlanVO> queryDayPlan(@RequestParam String userId) {
        long realUserId = Long.valueOf(userId);
        return dayPlanLogic.queryDayPlan(realUserId);
    }
}
