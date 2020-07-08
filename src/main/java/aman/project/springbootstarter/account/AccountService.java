package aman.project.springbootstarter.account;

import aman.project.springbootstarter.account.model.Account;
import aman.project.springbootstarter.account.model.AccountRequest;
import aman.project.springbootstarter.account.model.AccountResponse;
import aman.project.springbootstarter.account.model.AccountStatement;
import aman.project.springbootstarter.transaction.*;
import aman.project.springbootstarter.transaction.model.Transaction;
import aman.project.springbootstarter.transaction.model.TransactionType;
import aman.project.springbootstarter.user.UserRepository;
import aman.project.springbootstarter.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Calendar.MONTH;


@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AccountHelper accountHelper;

    public List<AccountResponse> getAllAccounts() {
        List<Account> accountList = accountRepository.findAll();
        List<AccountResponse>accountResponseList = new ArrayList<>();
        for(Account account: accountList)
        {
            AccountResponse accountResponse = new AccountResponse();
            accountResponse = accountHelper.convertAccountResponse(account);
            accountResponseList.add(accountResponse);
        }
        return accountResponseList;
    }

    public AccountResponse getAccount(Integer id)
    {
        Account account = accountRepository.findById(id).get();
        AccountResponse accountResponse = accountHelper.convertAccountResponse(account);
        return accountResponse;
    }

    public void addAccount(AccountRequest accountRequest) {
        Account account = new Account();
        account.setUser(userRepository.findById(accountRequest.getUserId()).get());
        account.setBalance(accountRequest.getBalance());
        account.setAccountType(accountRequest.getAccountType());
        accountRepository.save(account);
    }

    public void updateAccount(Integer id, AccountRequest accountRequest) {
        Account account = new Account();
        account.setUser(userRepository.findById(accountRequest.getUserId()).get());
        account.setBalance(accountRequest.getBalance());
        account.setAccountType(accountRequest.getAccountType());
        accountRepository.save(account);
    }

    public void deleteAccount(Integer id)
    {
        accountRepository.deleteById(id);
    }

    public List<AccountStatement> findStatementByAccountId(Integer id, Integer months)
    {
       List<Transaction>transactions = transactionRepository.findBySenderAccountOrReceiverAccount(id, id);
       List<AccountStatement>accountStatements = new ArrayList<>();
       for(Transaction transaction: transactions)
       {
           Date transactionDate = transaction.getDate();
           Calendar transactionCalender = Calendar.getInstance();
           transactionCalender.setTime(transactionDate);
           Integer transactionMonth = transactionCalender.get(MONTH);
           Date currentDate= new Date();
           Calendar currentCalender = Calendar.getInstance();
           currentCalender.setTime(currentDate);
           Integer currentMonth = currentCalender.get(MONTH);
           if(currentMonth-months > transactionMonth)
               continue;
           AccountStatement accountStatement = new AccountStatement();
           Account senderAccount = transaction.getSenderAccount();
           Account receiverAccount = transaction.getReceiverAccount();
           if(senderAccount.getId().equals(id))
           {
               accountStatement.setTransactionType(TransactionType.DEBIT);
               accountStatement.setOtherAccount(receiverAccount.getId());
           }
           else
           {
               accountStatement.setTransactionType(TransactionType.CREDIT);
               accountStatement.setOtherAccount(senderAccount.getId());
           }
           accountStatement.setTransactionId(transaction.getId());
           accountStatement.setAmount(transaction.getAmount());
           accountStatements.add(accountStatement);
       }
       return accountStatements;
    }

}
