package htxf.htb.Logic;

import htxf.htb.Dao.DayPlanDao;
import htxf.htb.Domain.PlanDomain;
import htxf.htb.VO.DayPlanVO;
import htxf.htb.VO.PlanVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DayPlanLogic {
    @Autowired
    private DayPlanDao dayPlanDao;

    // 增
    public String insertPlan(PlanVO vo) {
        PlanDomain planDomain = new PlanDomain();
        BeanUtils.copyProperties(vo, planDomain);
        // TODO planId需要发号获得
        String insertedPlanId = "TODO";
        planDomain.setPlanId(insertedPlanId);
        dayPlanDao.insertPlan(planDomain);
        return insertedPlanId;
    }
    // 删
    public String deletePlan(String planId) {
        // TODO 删除成功条数 or 删除成功与否
        return dayPlanDao.deletePlan(planId);
    }
    // 改
    public String editPlan(PlanVO vo) {
        PlanDomain planDomain = new PlanDomain();
        BeanUtils.copyProperties(vo, planDomain);
        // 修改成功与否
        return dayPlanDao.editPlan(planDomain);
    }
    // 查
    public List<DayPlanVO> queryDayPlan(String userId) {
        List<PlanDomain> planDomainList = dayPlanDao.queryDayPlan(userId);
        List<PlanVO> planVOList = new ArrayList<>();

        // 需要根据startTIme对dayPlanList分区、排序
        // 只会有三个，上午、中午、下午 dayPlanVO
        List<DayPlanVO> dayPlanVOList = new ArrayList<>();

        splitSortDayPlan(planVOList, dayPlanVOList);

        return dayPlanVOList;
    }

    /**
     * 将planVOList整理为dayPlanVOList
     * @param planVOList 查询数据库得到的某用户所有plan;
     * @param dayPlanVOList 整理好的dayPlan；
     * */
    private void splitSortDayPlan(List<PlanVO> planVOList, List<DayPlanVO> dayPlanVOList) {
        // 构造上午、下午、晚上三个 dayPlanVO
        DayPlanVO morningDayPlanVO = new DayPlanVO();
        morningDayPlanVO.setTimeZone("上午");
        List<PlanVO> morningPlanVOList = new ArrayList<>();
        morningDayPlanVO.setPlanList(morningPlanVOList);

        DayPlanVO afternoonDayPlanVO = new DayPlanVO();
        afternoonDayPlanVO.setTimeZone("下午");
        List<PlanVO> afternoonPlanVOList = new ArrayList<>();
        afternoonDayPlanVO.setPlanList(afternoonPlanVOList);

        DayPlanVO nightDayPlanVO = new DayPlanVO();
        nightDayPlanVO.setTimeZone("晚上");
        List<PlanVO> nightPlanVOList = new ArrayList<>();
        nightDayPlanVO.setPlanList(nightPlanVOList);

        // 找到相应的planVO
        for (PlanVO planVO: planVOList) {
            if (Integer.parseInt(planVO.getStartTime()) <= 12) {
                morningPlanVOList.add(planVO);
            } else if (Integer.parseInt(planVO.getStartTime()) > 12 && Integer.parseInt(planVO.getStartTime()) <= 19) {
                afternoonPlanVOList.add(planVO);
            } else if (Integer.parseInt(planVO.getStartTime()) > 19) {
                nightPlanVOList.add(planVO);
            }
        }

        // 根据plan的startTime排序 idea 推荐改为lambda表达式，又推荐改为如下
        Comparator<PlanVO> dayPlanComparator = Comparator.comparingInt(o -> Integer.parseInt(o.getStartTime()));

//        Comparator<PlanVO> dayPlanComparator = new Comparator<PlanVO>() {
//            @Override
//            public int compare(PlanVO o1, PlanVO o2) {
//                return Integer.parseInt(o1.getStartTime()) - Integer.parseInt(o2.getStartTime());
//            }
//        };

        // 分别排序上午、下午、晚上三个dayPlanVO
        morningPlanVOList.sort(dayPlanComparator);
        afternoonPlanVOList.sort(dayPlanComparator);
        nightPlanVOList.sort(dayPlanComparator);
    }

}
