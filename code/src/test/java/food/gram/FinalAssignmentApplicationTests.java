package food.gram;

import food.gram.entity.Account;
import food.gram.entity.Profile;
import food.gram.repository.AccountRepository;
import food.gram.repository.ProfileRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FinalAssignmentApplicationTests {
    @Inject
    AccountRepository accountRepository;

    @Inject
    ProfileRepository profileRepository;
    @Test
    public void contextLoads() {
    }

    @Test
    public void testrepos(){
        Account account = new Account("jakabgyi","jakabgyi","1997jakab");
        //accountRepository.save(account);
        account.setAccountId(1);
        account = accountRepository.findByAccountId(account.getAccountId());
        System.out.println(account);
        //Profile profile = new Profile(account,"jakabgy",null,null,"PUBLIC");
        //profileRepository.save(profile);
    }

}
