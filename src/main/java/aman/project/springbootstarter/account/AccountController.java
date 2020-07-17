package aman.project.springbootstarter.account;
import java.util.List;

import aman.project.springbootstarter.account.model.AccountRequest;
import aman.project.springbootstarter.account.model.AccountResponse;
import aman.project.springbootstarter.account.model.AccountStatement;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET, value = "/accounts")
    @ApiOperation(value = "Get accounts",
            notes = "Hit the api to get the list of all the accounts")
    public List<AccountResponse> getAllAccounts()
    {
        return accountService.getAllAccounts();
    }


    @RequestMapping(method = RequestMethod.GET, value = "/accounts/{id}")
    @ApiOperation(value = "Get account by id",
            notes = "Provide transaction id to get information about the transaction")
    public AccountResponse getAccount(@PathVariable Integer id)
    {
        return accountService.getAccount(id);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/accounts")
    @ApiOperation(value = "Add account",
                  notes = "Add details of the account")
    public void addAccount(@RequestBody AccountRequest accountRequest) {
        accountService.addAccount(accountRequest);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/accounts/{id}")
    @ApiOperation(value = "Delete account",
            notes = "Provide account id to delete the account")
    public void deleteAccount(@PathVariable Integer id)
    {
        accountService.deleteAccount(id);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/accounts/{id}/statement")
    @ApiOperation(value = "Find account statement",
            notes = "Provide account id to get the statement")
    public List<AccountStatement> findStatementByAccountId(@PathVariable Integer id, @RequestParam(required = false) Integer months) {
        return accountService.findStatementByAccountId(id, months);
    }
}
