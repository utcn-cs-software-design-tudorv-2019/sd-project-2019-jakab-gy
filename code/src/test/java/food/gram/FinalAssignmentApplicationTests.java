package food.gram;

import food.gram.persistence.entity.*;
import food.gram.persistence.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FinalAssignmentApplicationTests {
    @Inject
    AccountRepository accountRepository;

    @Inject
    ActivityRepository activityRepository;

    @Inject
    CommentaryRepository commentaryRepository;

    @Inject
    DescriptionRepository descriptionRepository;

    @Inject
    FollowRepository followRepository;

    @Inject
    HashtagRepository hashtagRepository;

    @Inject
    MessageRepository messageRepository;

    @Inject
    PostRepository postRepository;

    @Inject
    ProfileRepository profileRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testAccountRepository(){
        //Account account = new Account("jakabgyonci97","jakabgyongyi8@gmail.com", "1997jakab");
        Account account = new Account("veresLorand43","veres_lorand8@gmail.com", "1997veres");
        System.out.println("THIS WILL BE SAVED:\n" + account);
        account = accountRepository.save(account);
        System.out.println("THIS IS THE SAVED VERSION:\n"+account);

        /**Test all repository operations*/
        Account foundResult = accountRepository.findByAccountId(account.getAccountId());
        System.out.println("FIND BY ID RESULT : \n" + foundResult);

        foundResult = accountRepository.findByAccountName(account.getAccountName());
        System.out.println("FIND BY USERNAME RESULT : \n" + foundResult);

        foundResult = accountRepository.findByAccountEmail(account.getAccountEmail());
        System.out.println("FIND BY EMAIL ADDRESS RESULT : \n" + foundResult);

        foundResult = accountRepository.findByAccountNameAndAccountPassword(account.getAccountName(), account.getAccountPassword());
        System.out.println("FIND BY USERNAME AND PASSWORD RESULT : \n" + foundResult);

        foundResult = accountRepository.findByAccountEmailAndAccountPassword(account.getAccountEmail(), account.getAccountPassword());
        System.out.println("FIND BY EMAIL ADDRESS AND PASSWORD RESULT : \n" + foundResult);
    }

    @Test
    public void testProfileRepository(){
        Account account = new Account("veresLorand43","veres_lorand8@gmail.com", "1997veres");
        //Account account = new Account("jakabgyonci97","jakabgyongyi8@gmail.com", "1997jakab");
        System.out.println("LOGIN DATA: \n"+account);
        account = accountRepository.findByAccountNameAndAccountPassword(account.getAccountName(), account.getAccountPassword());

        /**create default profile based on account data*/
        Profile profile = new Profile();
        profile.setAccount(account);
        profile.setProfileName(account.getAccountName());
        profile.setStatus("PUBLIC");
        System.out.println("THIS WILL BE SAVED: \n" + profile);

        profile = profileRepository.save(profile);
        System.out.println("THIS IS THE SAVED VERSION : \n" + profile);

        /**Test all repository operations*/
        Profile foundResult = profileRepository.findByProfileId(profile.getProfileId());
        System.out.println("FIND BY ID RESULT : \n" + foundResult);

        foundResult = profileRepository.findByAccount(account);
        System.out.println("FIND BY ACCOUNT ID: \n" + foundResult);

        List<Profile> findResultList = profileRepository.findAllByProfileName(profile.getProfileName());
        System.out.println("FIND ALL BY PROFILE NAME RESULT :");
        findResultList.forEach(p-> System.out.println(p));

        findResultList = profileRepository.findAllByStatus("PUBLIC");
        System.out.println("FIND ALL BY STATUS RESULT");
        findResultList.forEach(p-> System.out.println(p));
    }

    @Test
    public void testPostRepository(){
        Account account = new Account("jakabgyonci97","jakabgyongyi8@gmail.com", "1997jakab");
        account = accountRepository.findByAccountNameAndAccountPassword(account.getAccountName(), account.getAccountPassword());

        Profile profile = profileRepository.findByAccount(account);
        Post post = new Post();
        post.setProfile(profile);
        post.setLocation("Cluj-Napoca");
        post.setPostTime(Timestamp.valueOf(LocalDateTime.now()));
        post.setHashtags("#lovefood #foodlover");

        post = postRepository.save(post);

        Post foundResult = postRepository.findByPostId(post.getPostId());
        System.out.println("FIND BY ID RESULT : \n" + foundResult);

        List <Post> foundResultList = postRepository.findAllByProfile(profile);
        System.out.println("FIND ALL BY PROFILE RESULT : \n");
        foundResultList.forEach(p-> System.out.println(p));

        foundResultList = postRepository.findAllByLocation("Cluj-Napoca");
        System.out.println("FIND ALL BY LOCATION RESULT : ");
        foundResultList.forEach(p-> System.out.println(p));

        foundResultList = postRepository.findAllByPostTimeIsBetween(Timestamp.valueOf("2019-06-03 14:19:35"), Timestamp.valueOf("2019-06-05 14:19:35"));
        System.out.println("FIND ALL BY TIME INTERVAL RESULT : ");
        foundResultList.forEach(p-> System.out.println(p));

    }

    @Test
    public void testMessageRepository(){
        Account account1 = accountRepository.findByAccountEmailAndAccountPassword("veres_lorand8@gmail.com","1997veres");
        Account account2 = accountRepository.findByAccountEmailAndAccountPassword("jakabgyongyi8@gmail.com","1997jakab");

        Profile profile1 = profileRepository.findByAccount(account1);
        Profile profile2 = profileRepository.findByAccount(account2);

        List<Message> messages = new ArrayList<>();
        messages.add(new Message(profile1,profile2,"Send me your project pls",Timestamp.valueOf(LocalDateTime.now())));
        messages.add(new Message(profile1,profile2,"Pretty please",Timestamp.valueOf(LocalDateTime.now())));
        messages.add(new Message(profile2,profile1,"Sorry, this is something you need to do on your own",Timestamp.valueOf(LocalDateTime.now())));
        messages.add(new Message(profile2,profile1,"But if you have questions, I may help you",Timestamp.valueOf(LocalDateTime.now())));

        messages.forEach(m->messageRepository.save(m));

        messages = messageRepository.findAllBySenderProfile(profile1);
        System.out.println("FIND ALL BY SENDER RESULT : \n SENDER : \n" + profile1);
        messages.forEach(m-> System.out.println(m));

        messages = messageRepository.findAllByReceiverProfile(profile1);
        System.out.println("FIND ALL BY RECEIVER RESULT : \n RECEIVER : \n" + profile1);
        messages.forEach(m -> System.out.println(m));

        messages = messageRepository.findAllBySenderProfileAndReceiverProfile(profile1,profile2);
        System.out.println("FIND ALL BY SENDER AND RECEIVER RESULT : \n SENDER : \n" + profile1 + " RECEIVER : \n" + profile2);
        messages.forEach(m -> System.out.println(m));

        messages = messageRepository.findAllBySendingTimeIsBetween(Timestamp.valueOf("2019-06-03 14:19:35"), Timestamp.valueOf("2019-06-05 14:19:35"));
        System.out.println("FIND ALL BY SENDING TIME INTERVAL RESULT : ");
        messages.forEach(m -> System.out.println(m));
    }

    @Test
    public void testCommentRepository(){
        Account account = accountRepository.findByAccountEmailAndAccountPassword("jakabgyongyi8@gmail.com","1997jakab");
        Profile profile = profileRepository.findByAccount(account);
        List<Post> posts = postRepository.findAllByProfile(profile);
        Post post = posts.get(0);

        List<Commentary> comments = new ArrayList<>();
        comments.add(new Commentary(post,profile,"Cool pic", Timestamp.valueOf(LocalDateTime.now())));
        comments.add(new Commentary(post,profile,"I really would like to try this out", Timestamp.valueOf(LocalDateTime.now())));
        comments.add(new Commentary(post,profile,"look delicious", Timestamp.valueOf(LocalDateTime.now())));
        comments.forEach(c -> commentaryRepository.save(c));

        comments = commentaryRepository.findAllByPost(post);
        System.out.println("FIND ALL BY POST RESULT : \n POST : \n" + post);
        comments.forEach(c -> System.out.println(c));

        comments = commentaryRepository.findAllByProfile(profile);
        System.out.println("FIND ALL BY PROFILE RESULT : \n PROFILE : \n" + profile);
        comments.forEach(c -> System.out.println(c));
    }

    @Test
    public void testDescriptionRepository(){
        Account account = accountRepository.findByAccountEmailAndAccountPassword("jakabgyongyi8@gmail.com","1997jakab");
        Profile profile = profileRepository.findByAccount(account);
        List<Post> posts = postRepository.findAllByProfile(profile);
        Post post = posts.get(0);

        Description description = new Description();
        description.setPost(post);
        description.setIngredients("nonstick cooking spray, for greasing\n" +
                "2 ½ cups  cookies n cream ice cream, melted (375 g)\n" +
                "1 box white cake mix\n" +
                "4 large egg whites\n" +
                "4 cups  white frosting, whipped (460 g)\n" +
                "10 chocolate sandwich cookies, coarsely crushed, divided, plus 8, whole");
        description.setDirections("Preheat the oven to 350˚F (180˚C). Grease 2 8-inch (20 cm) round cake pans with nonstick spray.\n" +
                "In a large bowl, combine the melted ice cream, cake mix, and egg whites. Whisk until smooth.\n" +
                "Divide the cake batter evenly between the prepared pans. Bake according to the package instructions, about 30 minutes, or until a toothpick inserted in the center of the cakes comes out clean. Let cool completely, about 30 minutes.\n" +
                "Place 1 cake on a cake stand or platter. Spread an even layer of frosting over the cake. Sprinkle 6 of the crushed chocolate sandwich cookies evenly over the frosting. Top with the other cake layer. Spread a thin layer of frosting over the top and sides of the cake.\n" +
                "Chill the cake in the refrigerator for 15 minutes.\n" +
                "Coat the top and sides of the cake with frosting. Transfer the remaining frosting to a piping bag or resealable plastic bag fitted with a medium star tip. Pipe 8 1½-inch (20 cm) swirls of frosting around the outside of the cake. Place a chocolate sandwich cookie vertically in each swirl. Sprinkle the remaining 4 crushed cookies in the center.");
        description = descriptionRepository.save(description);

        description = descriptionRepository.findByPost(post);
        System.out.println("FIND BY POST RESULT : \n POST : \n" + post + " \n" + description);

        description = descriptionRepository.findByDescriptionId(description.getDescriptionId());
        System.out.println("FIND BY ID RESULT : \n" + description);


    }

    @Test
    public void testFollowRepository(){
        Account account1 = accountRepository.findByAccountEmailAndAccountPassword("veres_lorand8@gmail.com","1997veres");
        Account account2 = accountRepository.findByAccountEmailAndAccountPassword("jakabgyongyi8@gmail.com","1997jakab");

        Profile profile1 = profileRepository.findByAccount(account1);
        Profile profile2 = profileRepository.findByAccount(account2);

        Follow follow = new Follow(profile1,profile2,Timestamp.valueOf(LocalDateTime.now()));
        followRepository.save(follow);
        follow = new Follow(profile2,profile1,Timestamp.valueOf(LocalDateTime.now()));
        followRepository.save(follow);

        List<Follow> followList = followRepository.findAllByFollowedProfile(profile1);
        List<Profile> profiles = new ArrayList<>();
        followList.forEach(f -> {
            Profile p = profileRepository.findByProfileId(f.getFollowerProfile().getProfileId());
            profiles.add(p);
        });
        System.out.println("FIND ALL FOLLOWERS OF : " + profile1 + " : ");
        profiles.forEach(p-> System.out.println(p));

        followList = followRepository.findAllByFollowerProfile(profile1);
        profiles.clear();
        followList.forEach(f -> {
            Profile p = profileRepository.findByProfileId(f.getFollowerProfile().getProfileId());
            profiles.add(p);
        });
        System.out.println("FIND ALL FOLLOWED OF : " + profile1 + " : ");
        profiles.forEach(p-> System.out.println(p));
    }
}
