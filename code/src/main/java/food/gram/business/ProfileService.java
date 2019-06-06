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
    FollowService followService;

    @Inject
    MessageRepository messageRepository;

    @Inject
    PostService postService;

    /**Find profile by ID*/
    public Profile viewProfile(int profileId){
        return profileRepository.findByProfileId(profileId);
    }

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

    /**Search for profile with previous conversation or followed profile*/
    public Profile searchForMatchName(Profile senderProfile, String profileName){
        List<Message> messages = messageRepository.findAllBySenderProfile(senderProfile);

        Profile receiverProfile = null;

        for(Message m : messages){
            if(m.getReceiverProfile().getProfileName().equals(profileName)){
                receiverProfile = m.getReceiverProfile().clone();

            }
        }
        if(receiverProfile != null) return receiverProfile;

        messages = messageRepository.findAllByReceiverProfile(senderProfile);
        for(Message m : messages){
            if(m.getSenderProfile().getProfileName().equals(profileName)){
                receiverProfile = m.getSenderProfile().clone();
            }
        }
        if(receiverProfile !=  null) return receiverProfile;

        List<Profile> followed = followService.viewAllFollowedProfiles(senderProfile);
        for(Profile p : followed){
            if(p.getProfileName().equals(profileName))
                return p;
        }
        return null;
    }
}
