package food.gram.repository;

import food.gram.entity.Activity;
import food.gram.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity,Integer> {

    public Activity findByActivityId(int activityId);
    public List<Activity> findAllByProfile(Profile profile);
    public List<Activity> findAllByActivityType(String activityType);
}
