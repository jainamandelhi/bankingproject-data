package aman.project.springbootstarter.account;
import java.util.List;

import aman.project.springbootstarter.account.model.AccountRequest;
import aman.project.springbootstarter.account.model.AccountResponse;
import aman.project.springbootstarter.account.model.AccountStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping("/accounts")
    public List<AccountResponse> getAllAccounts()
    {
        return accountService.getAllAccounts();
    }
    @RequestMapping("/accounts/{id}")
    public AccountResponse getAccount(@PathVariable Integer id)
    {
        return accountService.getAccount(id);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/accounts")
    public void addAccount(@RequestBody AccountRequest accountRequest) {
        accountService.addAccount(accountRequest);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/accounts/{id}")
    public void deleteAccount(@PathVariable Integer id)
    {
        accountService.deleteAccount(id);
    }

    @RequestMapping("/accounts/{id}/statement")
    public List<AccountStatement> findStatementByAccountId(@PathVariable Integer id, @RequestParam(required = false) Integer months) {
        return accountService.findStatementByAccountId(id, months);
    }
}
