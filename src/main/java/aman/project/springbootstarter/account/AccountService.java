package aman.project.springbootstarter.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts;
    }

    public Optional<Account> getAccount(Integer id) {
        return accountRepository.findById(id);
    }

    public void addAccount(AccountRequest accountRequest) {
        Account account = new Account();
        account.setBalance(accountRequest.getBalance());
        account.setOwner(accountRequest.getOwner());
        account.setAccountType(accountRequest.getAccountType());
        accountRepository.save(account);
    }

    public void updateAccount(Integer id, Account account) {
        accountRepository.save(account);

    }

    public void deleteAccount(Integer id) {
        accountRepository.deleteById(id);
    }

    /*public Double getBalance(Integer id) {
        return accountRepository.findById(id).get().getBalance();
    }*/
}
