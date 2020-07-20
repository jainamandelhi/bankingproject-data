package aman.project.springbootstarter.account;

import aman.project.springbootstarter.account.model.Account;
import aman.project.springbootstarter.account.model.AccountRequest;
import aman.project.springbootstarter.account.model.AccountResponse;
import aman.project.springbootstarter.account.model.AccountStatement;
import aman.project.springbootstarter.transaction.TransactionRepository;
import aman.project.springbootstarter.transaction.model.Transaction;
import aman.project.springbootstarter.transaction.model.TransactionType;
import aman.project.springbootstarter.user.UserRepository;
import aman.project.springbootstarter.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;


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

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    public List<AccountResponse> getAllAccounts() {
        logger.info("Retrieving list of all accounts");
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
        AccountResponse accountResponse = new AccountResponse();
        try {
            Account account = accountRepository.findById(id).get();
            accountResponse = accountHelper.convertAccountResponse(account);
        }
        catch(NoSuchElementException e){
            logger.info("No such account exists");
        }
        return accountResponse;
    }

    public void addAccount(AccountRequest accountRequest) {
        Account account = new Account();
        account.setUser(userRepository.findById(accountRequest.getUserId()).get());
        account.setBalance(accountRequest.getBalance());
        account.setAccountType(accountRequest.getAccountType());
        accountRepository.save(account);
    }

    public void deleteAccount(Integer id)
    {
        try {
            accountRepository.deleteById(id);
        }
        catch(NoSuchElementException e){
            logger.info("No such account exists");
        }

    }

    public List<AccountStatement> findStatementByAccountId(Integer id, Integer months)
    {
        logger.info("Generating statement");
        List<AccountStatement>accountStatements = new ArrayList<>();
        List<Transaction> transactions = new ArrayList<>();
        try {
            transactions = transactionRepository.findBySenderAccountOrReceiverAccount(id, id);
        }
        catch(NoSuchElementException e) {
            logger.info("No transaction exists");
        }
       for(Transaction transaction: transactions)
       {
           Date transactionDate = transaction.getDate();
           Calendar transactionCalender = Calendar.getInstance();
           transactionCalender.setTime(transactionDate);
           //Integer transactionMonth = transactionCalender.get(MONTH);
           //Integer transactionYear = transactionCalender.get(Calendar.YEAR);
           Date currentDate= new Date();
           Calendar currentCalender = Calendar.getInstance();
           currentCalender.setTime(currentDate);
          // Integer currentMonth = currentCalender.get(MONTH);
           //Integer currentYear = currentCalender.get(Calendar.YEAR);
           Integer days = (int) Duration.between(transactionCalender.toInstant(), currentCalender.toInstant()).toDays();
           if(days > months*30)
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
       logger.info("Statement generated");
       return accountStatements;
    }

}
