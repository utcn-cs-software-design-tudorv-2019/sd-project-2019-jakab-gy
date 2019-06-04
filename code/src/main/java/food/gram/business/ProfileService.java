package food.gram.business;

import food.gram.persistence.repository.*;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class ProfileService {
    @Inject
    ProfileRepository profileRepository;

    @Inject
    PostRepository postRepository;

    @Inject
    FollowRepository followRepository;

    @Inject
    ActivityRepository activityRepository;

}
