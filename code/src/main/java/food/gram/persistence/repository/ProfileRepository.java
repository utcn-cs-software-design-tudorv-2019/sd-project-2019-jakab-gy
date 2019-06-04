package food.gram.persistence.repository;

import food.gram.persistence.entity.Account;
import food.gram.persistence.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProfileRepository extends JpaRepository<Profile,Integer> {

    public Profile findByProfileId(int profileId);
    public Profile findByAccount(Account account);
    public List<Profile> findAllByProfileName(String profileName);
    public List<Profile> findAllByStatus(String status);
}
