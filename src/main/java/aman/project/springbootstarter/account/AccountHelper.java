package aman.project.springbootstarter.account;
import aman.project.springbootstarter.account.model.Account;
import aman.project.springbootstarter.account.model.AccountRequest;
import aman.project.springbootstarter.account.model.AccountResponse;
import org.springframework.stereotype.Component;

@Component
public class AccountHelper {
    public AccountRequest convertAccountRequest(Account account)
    {
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setId(account.getId());
        accountRequest.setAccountType(account.getAccountType());
        accountRequest.setBalance(account.getBalance());
        accountRequest.setUserId(account.getUser().getId());
        return accountRequest;
    }
    public AccountResponse convertAccountResponse(Account account)
    {
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setId(account.getId());
        accountResponse.setAccountType(account.getAccountType());
        accountResponse.setBalance(account.getBalance());
        accountResponse.setUserId(account.getUser().getId());
        return accountResponse;
    }
}
