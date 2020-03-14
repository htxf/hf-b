package htxf.htb.Controller;

import htxf.htb.Logic.DayPlanLogic;
import htxf.htb.VO.DayPlanVO;
import htxf.htb.VO.PlanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * day plan controller
 * htxf 20200302
 * */
@RestController
@RequestMapping("/dayPlans")
@CrossOrigin
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
    // 前段 'planIds':'1,2..'
    public String deletePlan(@RequestBody Map<String,String> map) {
        String planIds = map.get("planIds");
        return dayPlanLogic.deletePlans(planIds);
    }
    // 改
    @RequestMapping("/edit")
    // 之前错误情况 @RequestBody String planId 接收到的是 { "planId": "1" }，解析不到planId，前端给的是json字符串，
    // 且content-type是application/json，后端接收是是一个map？
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
