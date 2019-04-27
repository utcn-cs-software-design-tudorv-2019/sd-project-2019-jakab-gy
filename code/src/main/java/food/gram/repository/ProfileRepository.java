package food.gram.repository;

import food.gram.entity.Account;
import food.gram.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile,Integer> {

    public Profile findByProfileId(int profileId);
    public Profile findByAccount(Account account);
    public List<Profile> findAllByProfileName(String profileName);
}
