package food.gram.contoller;

import food.gram.business.AccountService;
import food.gram.persistence.entity.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;

@Controller
public class AccountController {
    @Inject
    AccountService accountService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView viewLogin(){
        Account account = new Account();

        ModelAndView mav = new ModelAndView("startPage");
        mav.addObject("account", account);
        return mav;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView handleAction(@RequestParam(value = "action") String action,
                                     @ModelAttribute(value = "account") Account account){
        System.out.println(account);
        Profile profile = null;

        if(action.equals("SignUp"))
            profile = accountService.processSignUp(account);
        else if(action.equals("Login"))
            profile = accountService.processLogIn(account);

        if(profile == null)
            return new ModelAndView("redirect:/");

        ModelAndView mav = new ModelAndView("profileMain");
        mav.addObject("profile",profile);
        return mav;
    }
}
