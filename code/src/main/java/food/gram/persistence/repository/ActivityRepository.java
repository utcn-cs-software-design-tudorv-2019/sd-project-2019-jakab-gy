package food.gram.persistence.repository;

import food.gram.persistence.entity.Activity;
import food.gram.persistence.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity,Integer> {

    public Activity findByActivityId(int activityId);
    public List<Activity> findAllByProfile(Profile profile);
    public List<Activity> findAllByActivityType(String activityType);
}
