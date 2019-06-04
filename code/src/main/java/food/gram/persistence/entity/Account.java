package food.gram.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private int accountId;

    @Column(name = "account_name",length = 30,nullable = false)
    private String accountName;

    @Column(name = "account_email",length = 30,nullable = false)
    private String accountEmail;

    @Column(name = "account_password",length = 15,nullable = false)
    private String accountPassword;

    public Account() {
        super();
    }

    public Account(String accountName, String accountEmail, String accountPassword) {
        this.accountName = accountName;
        this.accountEmail = accountEmail;
        this.accountPassword = accountPassword;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", accountName='" + accountName + '\'' +
                ", accountEmail='" + accountEmail + '\'' +
                ", accountPassword='" + accountPassword + '\'' +
                '}';
    }
}
