package food.gram.contoller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import food.gram.business.FollowService;
import food.gram.business.MessageService;
import food.gram.business.PostService;
import food.gram.business.ProfileService;
import food.gram.persistence.entity.*;
import food.gram.persistence.repository.AccountRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Inject
    ProfileService profileService;

    @Inject
    PostService postService;

    @Inject
    MessageService messageService;

    @Inject
    FollowService followService;

    int profileId = 3;

    @RequestMapping(value = "/recentPost", method = RequestMethod.GET)
    public ModelAndView viewRecentPosts(){
        ModelAndView mav = new ModelAndView("recentPost");
        Profile profile = profileService.viewProfile(profileId);

        List<Post> postList = postService.viewAllRecentFollowedPosts(profile);
        mav.addObject("postList",postList);
        return mav;
    }

    @RequestMapping(value = "/recentPost", method = RequestMethod.POST)
    public ModelAndView processRecentPostAction(@RequestParam(value = "action")String action){

        return new ModelAndView();
    }

    @RequestMapping(value = "/searchPost", method = RequestMethod.GET)
    public ModelAndView viewSearchPost(){
        ModelAndView mav = new ModelAndView("searchPost");
        Profile profile = profileService.viewProfile(profileId);
        List<Post> postList = postService.viewAllRecentPosts(profile);
        mav.addObject("postList",postList);
        return mav;
    }

    @RequestMapping(value = "/searchPost", method = RequestMethod.POST)
    public ModelAndView processSearchPostAction(@RequestParam(value = "action")String action,
                                                @RequestParam(value = "searchInfo")String searchInfo){
        if(action.equals("Search")){
            List<Profile> profiles = profileService.viewAllThatMatchName(searchInfo);
            if(profiles == null || profiles.isEmpty()) return new ModelAndView("redirect:/profile/searchPost");

            List<Post> postList = postService.viewAllPosts(profiles.get(0));
            ModelAndView mav = new ModelAndView("searchPost");
            mav.addObject("postList",postList);
            return mav;
        }
        return new ModelAndView("redirect:/profile/searchPost");
    }

    @RequestMapping(value = "/newPost", method = RequestMethod.GET)
    public ModelAndView viewCreatePost(){
        ModelAndView mav = new ModelAndView("createPost");
        mav.addObject("post",new Post());
        mav.addObject("description", new Description());
        return mav;
    }

    @RequestMapping(value = "/newPost", method = RequestMethod.POST)
    public ModelAndView processCreatePost(@ModelAttribute(value = "post")Post post,
                                          @ModelAttribute(value = "description")Description description){
        Profile profile = profileService.viewProfile(profileId);

        postService.createPost(post,profile,description);

        ModelAndView mav = new ModelAndView("redirect:/profile/recentPost");
        return mav;
    }

    @RequestMapping(value = "/personalProfile", method = RequestMethod.GET)
    public ModelAndView viewProfile(){
        ModelAndView mav = new ModelAndView("viewProfile");
        Profile profile = profileService.viewProfile(profileId);
        Account account = profile.getAccount();
        List<Post> postList = postService.viewAllPosts(profile);

        long followerNumber = followService.viewNumberOfFollowers(profile);
        long followedNumber = followService.viewNumberOfFollowed(profile);
        long postNumber = 0;
        if(postList != null && !postList.isEmpty()) postNumber = postList.size();

        mav.addObject("profile", profile);
        mav.addObject("followerNumber", followerNumber);
        mav.addObject("followedNumber", followedNumber);
        mav.addObject("postNumber", postNumber);
        mav.addObject("account",account);
        mav.addObject("postList",postList);
        return mav;
    }

    @RequestMapping(value = "/personalProfile", method = RequestMethod.POST)
    public ModelAndView processProfileAction(@RequestParam(value = "action")String action,
                                             @ModelAttribute(value = "profile")Profile profile,
                                             @ModelAttribute(value = "account")Account account){
        return new ModelAndView();
    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public ModelAndView viewMessages(){
        Profile profile = profileService.viewProfile(profileId);
        System.out.println(profile);
        List<Profile> conversation = messageService.listAllConversations(profile);
        List<Message> messages = new ArrayList<>();

        ModelAndView mav = new ModelAndView("viewMessage");
        mav.addObject("conversation", conversation);
        mav.addObject("receiverProfile", new Profile());
        mav.addObject("messages", messages);
        mav.addObject("newMessage", new Message());
        return mav;
    }

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public ModelAndView processMessagesAction(@RequestParam(value = "action")String action,
                                              @RequestParam(value = "receiverName")String receiverName,
                                              @ModelAttribute(value = "newMessage")Message message,
                                              @ModelAttribute(value = "receiverProfile")Profile receiverProfile){

        ModelAndView mav = new ModelAndView("viewMessage");
        Profile senderProfile = profileService.viewProfile(profileId);

        if(action.equals("Search")) {
            receiverProfile = profileService.searchForMatchName(senderProfile, receiverName);
            if(receiverProfile == null) {
                mav.addObject("receiverProfile", new Profile());
                mav.addObject("messages", new ArrayList<>());
            }
            else {

                mav.addObject("receiverProfile", receiverProfile);
                List<Message> messages = messageService.listAllMessages(senderProfile,receiverProfile);
                mav.addObject("messages", messages);
            }
        }
        else if(action.equals("Send")) {
            System.out.println("SENDER: "+ senderProfile);
            System.out.println("RECEIVER: " + receiverProfile);
            messageService.createMessage(senderProfile,receiverProfile,message);
            mav.addObject("receiverProfile", receiverProfile);
            List<Message> messages = messageService.listAllMessages(senderProfile,receiverProfile);
            mav.addObject("messages", messages);
        }

        List<Profile> conversation = messageService.listAllConversations(senderProfile);
        mav.addObject("conversation", conversation);
        mav.addObject("newMessage", new Message());

        return mav;
    }
}
