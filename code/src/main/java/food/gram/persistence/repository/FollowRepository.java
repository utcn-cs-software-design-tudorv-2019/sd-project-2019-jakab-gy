package food.gram.persistence.repository;

import food.gram.persistence.entity.Follow;
import food.gram.persistence.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow,Integer> {

    public Follow findByFollowId(int followId);
    public List<Follow> findAllByFollowedProfile(Profile followedProfile);
    public List<Follow> findAllByFollowerProfile(Profile followerProfile);
}
