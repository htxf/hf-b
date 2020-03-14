package htxf.htb.Dao;

import htxf.htb.Domain.PlanDomain;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DayPlanRepository extends CrudRepository<PlanDomain, Long> {
    public List<PlanDomain> findByUserId(long userId);

//    @Query("SELECT p FROM PlanDomain p WHERE p.userId = :userId and p.type = :type")
//    public List<PlanDomain> findByUserIdAndType(@Param("userId") long userId, @Param("type") String type);

    public List<PlanDomain> findByUserIdAndTypeAndDeleteFlag(long userId, String type, String deleteFlag);
}
