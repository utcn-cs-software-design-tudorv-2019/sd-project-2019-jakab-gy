package food.gram.business;

import food.gram.persistence.entity.*;
import food.gram.persistence.repository.*;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class ProfileService {
    @Inject
    ProfileRepository profileRepository;

    @Inject
    FollowRepository followRepository;

    @Inject
    MessageRepository messageRepository;

    @Inject
    PostService postService;

    /**Update profile*/
    public void updateProfile(Profile profile){
        profileRepository.save(profile);
    }

    /**Delete profile - In order to delete a profile you need to delete all other related entities first*/
    public void deleteProfile(Profile profile){
        List<Follow> followers = followRepository.findAllByFollowedProfile(profile);
        followers.forEach(f -> followRepository.delete(f));

        List<Follow> followed = followRepository.findAllByFollowerProfile(profile);
        followed.forEach(f -> followRepository.delete(f));

        List<Message> messages = messageRepository.findAllBySenderProfile(profile);
        messages.forEach(m -> messageRepository.delete(m));

        messages = messageRepository.findAllByReceiverProfile(profile);
        messages.forEach(m -> messageRepository.delete(m));

        postService.deleteAllPosts(profile);
    }

    /**View all profiles by profileName*/
    public List<Profile> viewAllThatMatchName(String profileName){
        return profileRepository.findAllByProfileName(profileName);
    }
}
