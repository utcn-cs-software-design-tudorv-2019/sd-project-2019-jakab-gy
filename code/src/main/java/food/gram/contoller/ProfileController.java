package food.gram.contoller;

import food.gram.business.PostService;
import food.gram.business.ProfileService;
import food.gram.persistence.entity.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Inject
    ProfileService profileService;

    @Inject
    PostService postService;

    @RequestMapping(value = "/recentPost", method = RequestMethod.GET)
    public ModelAndView viewRecentPosts(){
        ModelAndView mav = new ModelAndView("recentPost");
        return mav;
    }

    @RequestMapping(value = "/searchPost", method = RequestMethod.GET)
    public ModelAndView viewSearchPost(){
        ModelAndView mav = new ModelAndView("searchPost");
        return mav;
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

        ModelAndView mav = new ModelAndView("redirect:/recentPost");
        return mav;
    }

    @RequestMapping(value = "/personalProfile", method = RequestMethod.GET)
    public ModelAndView viewProfile(){
        ModelAndView mav = new ModelAndView("viewProfile");
        mav.addObject("profile", new Profile());
        mav.addObject("account");
        mav.addObject("listPost");
        return mav;
    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public ModelAndView viewMessages(){
        ModelAndView mav = new ModelAndView("viewMessage");
        return mav;
    }
}
