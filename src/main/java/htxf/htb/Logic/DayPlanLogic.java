package htxf.htb.Logic;

import htxf.htb.Dao.DayPlanRepository;
import htxf.htb.Domain.PlanDomain;
import htxf.htb.VO.DayPlanVO;
import htxf.htb.VO.PlanVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DayPlanLogic {

    @Autowired
    private DayPlanRepository dayPlanRepository;

    // 增
    public long insertPlan(PlanVO vo) {
        PlanDomain planDomain = new PlanDomain();
        BeanUtils.copyProperties(vo, planDomain);
        // 设置一些默认值
        // 设置创建日期 TODO 创建时间用什么格式记录
        planDomain.setCreateTime(new Date().toString());
        // TODO same as create time
        planDomain.setLastEditTime(new Date().toString());
        // 设置类型 新增的每日计划type为 0-日
        planDomain.setType("0");
        // 设置删除标识 新增的肯定是还未删除
        planDomain.setDeleteFlag("0");
        // TODO planId需要发号获得
        // planId数据库自增
        PlanDomain insertedPlanDomain = dayPlanRepository.save(planDomain);
        return insertedPlanDomain.getPlanId();
    }
    // 删
    public String deletePlan(long planId) {
        // TODO 删除成功条数 or 删除成功与否
        try {
            // TODO 删除应改为逻辑删除而不是物理删除 其实相当于edit了
            dayPlanRepository.deleteById(planId);
        } catch (IllegalArgumentException e) {
            // TODO 统一的异常处理
            // 待删除的对象不存在 the given entity is null
            return "delete error";
        }
        return "success";
    }
    // 改
    public String editPlan(PlanVO vo) {
        PlanDomain planDomain = new PlanDomain();
        BeanUtils.copyProperties(vo, planDomain);
        // set last_edit_time
        // TODO make sure some good format
        planDomain.setLastEditTime(new Date().toString());
        // can only edit content or startTime or stopTime
        // find the other info
        Optional<PlanDomain> oldPlanDomainOptional = dayPlanRepository.findById(planDomain.getPlanId());
        planDomain.setCreateTime(oldPlanDomainOptional.get().getCreateTime());
        planDomain.setType(oldPlanDomainOptional.get().getType());
        planDomain.setDeleteFlag(oldPlanDomainOptional.get().getDeleteFlag());
        // can edit so deleteTime is null
        // planDomain.setDeleteTime(oldPlanDomainOptional.get().getDeleteTime());
        // TODO duration need to calculate from start and stop time

        // 修改成功与否
        try {
            dayPlanRepository.save(planDomain);
        } catch (IllegalArgumentException e) {
            // TODO 统一的异常处理
            // 待编辑的对象不存在 the given entity is null
            return "edit error";
        }
        return "success";
    }
    // 查
    public List<DayPlanVO> queryDayPlan(long userId) {
        List<PlanDomain> planDomainList = null;
        try {
//            planDomainList = dayPlanRepository.findByUserId(userId);
            // 只查找type为0的
            // TODO need to filter deleted plan
            planDomainList = dayPlanRepository.findByUserIdAndType(userId,"0");
        } catch (IllegalArgumentException e) {
            // TODO 统一的异常处理
            // 自定义的findByUserId出现异常
        }
        List<PlanVO> planVOList = new ArrayList<>();
        for (PlanDomain planDomain: planDomainList) {
            PlanVO planVO = new PlanVO();
            BeanUtils.copyProperties(planDomain, planVO);
            planVOList.add(planVO);
        }

        // 需要根据startTime对dayPlanList分区、排序
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
            if (getIntegerFromTimeString(planVO.getStartTime()) <= 1200) {
                morningPlanVOList.add(planVO);
            } else if (getIntegerFromTimeString(planVO.getStartTime()) > 1200 && getIntegerFromTimeString(planVO.getStartTime()) <= 1900) {
                afternoonPlanVOList.add(planVO);
            } else if (getIntegerFromTimeString(planVO.getStartTime()) > 1900) {
                nightPlanVOList.add(planVO);
            }
        }

        // 根据plan的startTime排序 idea 推荐改为lambda表达式，又推荐改为如下
        Comparator<PlanVO> dayPlanComparator = Comparator.comparingInt(o -> getIntegerFromTimeString(o.getStartTime()));

//        Comparator<PlanVO> dayPlanComparator = new Comparator<PlanVO>() {
//            @Override
//            public int compare(PlanVO o1, PlanVO o2) {
//                return getIntergerFromTimeString(o1.getStartTime()) - getIntergerFromTimeString(o2.getStartTime());
//            }
//        };

        // 分别排序上午、下午、晚上三个dayPlanVO
        morningPlanVOList.sort(dayPlanComparator);
        afternoonPlanVOList.sort(dayPlanComparator);
        nightPlanVOList.sort(dayPlanComparator);

        dayPlanVOList.add(morningDayPlanVO);
        dayPlanVOList.add(afternoonDayPlanVO);
        dayPlanVOList.add(nightDayPlanVO);
    }

    /**
     * 将数据库中存储的类似20:30字符串转换为2030Integer类型
     * @param timeString 带:的时间字符串
     * */
    private Integer getIntegerFromTimeString(String timeString) {
        String [] timeStringArr = timeString.split(":");
        String realTimeString = "";
        for (int i = 0 ;i < timeStringArr.length; i++) {
            realTimeString += timeStringArr[i];
        }
        return Integer.parseInt(realTimeString);
    }

}
