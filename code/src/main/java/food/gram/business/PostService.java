package food.gram.business;

import food.gram.persistence.entity.*;
import food.gram.persistence.repository.CommentaryRepository;
import food.gram.persistence.repository.DescriptionRepository;
import food.gram.persistence.repository.FollowRepository;
import food.gram.persistence.repository.PostRepository;
import org.springframework.stereotype.Service;
import sun.security.krb5.internal.crypto.Des;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
    FollowService followService;

    @Inject
    CommentaryRepository commentaryRepository;

    /**View All Recent Posts*/
    public List<Post> viewAllRecentPosts(Profile profile){
        Timestamp currentMoment = Timestamp.valueOf(LocalDateTime.now());
        Timestamp currentDayStart = generateTodayStart();

        List<Post> posts = postRepository.findAllByPostTimeIsBetween(currentDayStart,currentMoment);
        List<Post> recentPosts = new ArrayList<>();

        posts.forEach(p -> {
            if(p.getProfile().getProfileId() != profile.getProfileId() && p.getProfile().getStatus().equals("PUBLIC"))
                recentPosts.add(p);
        });

        Collections.sort(recentPosts);
        return recentPosts;
    }

    /**View All posts for a specific profile*/
    public List<Post> viewAllPosts(Profile profile){
        return postRepository.findAllByProfile(profile);
    }

    /**View All Recent Posts of followed profiles*/
    public List<Post> viewAllRecentFollowedPosts(Profile profile){
        List<Profile> followed = followService.viewAllFollowedProfiles(profile);
        if(followed == null) return null;

        Timestamp currentMoment = Timestamp.valueOf(LocalDateTime.now());
        Timestamp currentDayStart = generateTodayStart();

        List<Post> recentPosts = new ArrayList<>();
        followed.forEach(p -> {
            List<Post> posts = postRepository.findAllByProfileAndPostTimeIsBetween(p,currentDayStart,currentMoment);
            recentPosts.addAll(posts);
        });

        List<Post> personalPosts = postRepository.findAllByProfileAndPostTimeIsBetween(profile,currentDayStart,currentMoment);
        recentPosts.addAll(personalPosts);

        Collections.sort(recentPosts);

        return recentPosts;
    }

    /**Create post*/
    public void createPost(Post post, Profile profile, Description description){
        post.setProfile(profile);
        post.setPostTime(Timestamp.valueOf(LocalDateTime.now()));
        hashtagService.analiseHashtags(post.getHashtags());

        post = postRepository.save(post);

        description.setPost(post);
        descriptionRepository.save(description);
    }

    /**Update post*/
    public void updatePost(Post post,Description description){
        postRepository.save(post);
        descriptionRepository.save(description);
    }

    /**Delete post - delete all related objects - description + comments*/
    public void deletePost(Post post){
        Description description = descriptionRepository.findByPost(post);
        descriptionRepository.delete(description);

        List<Commentary> comments = commentaryRepository.findAllByPost(post);
        comments.forEach(c -> commentaryRepository.delete(c));

        postRepository.delete(post);
    }

    /**Delete All post of profile*/
    public void deleteAllPosts(Profile profile){
        List<Post> posts = postRepository.findAllByProfile(profile);
        posts.forEach(p -> deletePost(p));
    }

    private Timestamp generateTodayStart(){
        LocalDateTime today = LocalDateTime.now();

        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();

        StringBuilder sb = new StringBuilder();
        sb.append(year);

        sb.append("-");
        if(month < 9)sb.append("0");
        sb.append(month);

        sb.append("-");
        if(day < 9) sb.append("0");
        sb.append(day);

        sb.append(" 00:00:00");
        System.out.println(sb);
        return Timestamp.valueOf(sb.toString());
    }


}
