package food.gram.business;

import food.gram.persistence.entity.*;
import food.gram.persistence.repository.DescriptionRepository;
import food.gram.persistence.repository.FollowRepository;
import food.gram.persistence.repository.PostRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PostService {
    @Inject
    PostRepository postRepository;

    @Inject
    DescriptionRepository descriptionRepository;

    @Inject
    HashtagService hashtagService;

    @Inject
    FollowRepository followRepository;

    public Post viewLastPost(Profile profile){
        List<Post> posts = postRepository.findAllByProfile(profile);
        Collections.sort(posts);
        return posts.get(posts.size() - 1);
    }

    public List<Post> viewAllPosts(Profile profile){
        List<Post> posts = postRepository.findAllByProfile(profile);
        Collections.sort(posts);
        return posts;
    }

    public void createPost(Profile profile,byte[] image,String location, String hashtags,Description description){
        Post post = new Post(profile, Timestamp.valueOf(LocalDateTime.now()), image, location, hashtags);
        post = postRepository.save(post);

        description.setPost(post);
        descriptionRepository.save(description);

        hashtagService.analiseHashtags(hashtags);
    }

    public List<Profile> viewFollower(Profile profile){
        List<Follow> followers = followRepository.findAllByFollowedProfile(profile);
        List<Profile> profiles = new ArrayList<>();

        followers.forEach(f->{
            profiles.add(f.getFollowerProfile());
        });
        return profiles;
    }

    public List<Profile> viewFollowed(Profile profile){
        List<Follow> followed = followRepository.findAllByFollowerProfile(profile);
        List<Profile> profiles = new ArrayList<>();

        followed.forEach(f->{
            profiles.add(f.getFollowedProfile());
        });
        return profiles;
    }
}
