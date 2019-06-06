package food.gram.business;

import food.gram.persistence.entity.*;
import food.gram.persistence.repository.FollowRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FollowService {

    @Inject
    FollowRepository followRepository;

    /**Create follow*/
    public void createFollow(Profile profile1, Profile profile2){
        Follow follow = new Follow();
        follow.setFollowerProfile(profile1);
        follow.setFollowedProfile(profile2);
        follow.setFollowTime(Timestamp.valueOf(LocalDateTime.now()));

        followRepository.save(follow);
    }

    /**View number of follower profiles*/
    public long viewNumberOfFollowers(Profile profile){
        List<Profile> followerProfiles = viewAllFollowerProfiles(profile);
        if(followerProfiles == null) return 0;
        if (followerProfiles.isEmpty()) return 0;
        return followerProfiles.size();
    }

    /**View numbers of followed profiles*/
    public long viewNumberOfFollowed(Profile profile){
        List<Profile> followedProfiles = viewAllFollowedProfiles(profile);
        if(followedProfiles == null) return 0;
        if(followedProfiles.isEmpty()) return 0;
        return followedProfiles.size();
    }

    /**View all follower profiles*/
    public List<Profile> viewAllFollowerProfiles(Profile profile){
        List<Follow> followers = followRepository.findAllByFollowedProfile(profile);
        if(followers == null) return new ArrayList<>();

        List<Profile> profiles = new ArrayList<>();
        followers.forEach(f-> profiles.add(f.getFollowerProfile()));
        return profiles;
    }


    /**View all followed profiles*/
    public List<Profile> viewAllFollowedProfiles(Profile profile){
        List<Follow> followed = followRepository.findAllByFollowerProfile(profile);
        if(followed == null) return new ArrayList<>();

        List<Profile> profiles = new ArrayList<>();
        followed.forEach(f-> profiles.add(f.getFollowedProfile()));
        return profiles;
    }
}
