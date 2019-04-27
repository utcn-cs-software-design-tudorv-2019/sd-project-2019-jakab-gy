package food.gram.repository;

import food.gram.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Integer> {

    public Account findByAccountId(int accountId);
    public Account findByAccountNameAndAccountPassword(String accountName,String accountPassword);
    public Account findByAccountEmailAndAccountPassword(String accountEmail,String accountPassword);
    public Account findByAccountName(String accountName);
    public Account findByAccountEmail(String accountEmail);
}
