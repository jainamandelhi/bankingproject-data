package aman.project.springbootstarter.account;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping("/accounts")
    public List<Account> getAllAccounts()
    {
        return accountService.getAllAccounts();
    }
    @RequestMapping("/accounts/{id}")
    public Optional<Account> getAccount(@PathVariable Integer id)
    {
        return accountService.getAccount(id);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/accounts")
    public void addAccount(@RequestBody AccountRequest account) {
        accountService.addAccount(account);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/accounts/{id}")
    public void deleteAccount(@PathVariable Integer id)
    {
        accountService.deleteAccount(id);
    }
}
