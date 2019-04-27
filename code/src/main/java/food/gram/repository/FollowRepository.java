package food.gram.repository;

import food.gram.entity.Follow;
import food.gram.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow,Integer> {

    public Follow findByFollowId(int followId);
    public List<Follow> findAllByFollowedProfile(Profile followedProfile);
    public List<Follow> findAllByFollowerProfile(Profile followerProfile);
}
