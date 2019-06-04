package food.gram.business;

import food.gram.persistence.entity.*;
import food.gram.persistence.repository.AccountRepository;
import food.gram.persistence.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;


@Service
public class AccountService {

    @Inject
    AccountRepository accountRepository;

    @Inject
    ProfileRepository profileRepository;

    public Profile processSignUp(Account account){
        account = accountRepository.save(account);
        Profile profile = new Profile(account,account.getAccountName(),"","","PUBLIC");
        profile = profileRepository.save(profile);
        return profile;
    }

    public Profile processLogIn(Account account){
        Account found = null;
        found = accountRepository.findByAccountNameAndAccountPassword(account.getAccountName(), account.getAccountPassword());
        if(found == null){
            found = accountRepository.findByAccountEmailAndAccountPassword(account.getAccountEmail(), account.getAccountPassword());
            if(found == null) return null;
        }

        return profileRepository.findByAccount(found);
    }
}
