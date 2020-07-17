package aman.project.springbootstarter.transaction;
import aman.project.springbootstarter.account.model.Account;
import aman.project.springbootstarter.account.AccountRepository;
import aman.project.springbootstarter.account.AccountService;
import aman.project.springbootstarter.transaction.model.*;
import aman.project.springbootstarter.user.UserRepository;
import aman.project.springbootstarter.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static javax.persistence.LockModeType.PESSIMISTIC_READ;
import static javax.persistence.LockModeType.PESSIMISTIC_WRITE;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService users;
    
    @Autowired
    private AccountService accounts;
    
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionHelper transactionHelper;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Lock(PESSIMISTIC_READ)
    public List<TransactionResponseView> getAllTransactions() {
        logger.info("Retrieving list of all transactions");
        List<Transaction> transactionList = transactionRepository.findAll();
        List<TransactionResponseView>transactionResponseViewList = new ArrayList<>();
        for(Transaction transaction: transactionList)
        {
            TransactionResponseView transactionResponseView = new TransactionResponseView();
            transactionResponseView = transactionHelper.convertTransactionResponse(transaction);
            transactionResponseViewList.add(transactionResponseView);
        }
        return transactionResponseViewList;
    }
    @Lock(PESSIMISTIC_READ)
    public TransactionResponseView getTransaction(Integer id) {
        TransactionResponseView transactionResponseView = new TransactionResponseView();
        logger.info("Finding transaction with given id");
        try {
            Transaction transaction = transactionRepository.findById(id).get();
            transactionResponseView = transactionHelper.convertTransactionResponse(transaction);
        }
        catch(NoSuchElementException e) {
            logger.info("No such transaction exists");
        }
        return transactionResponseView;
    }

    @Transactional
    @Lock(PESSIMISTIC_WRITE)
    public TransactionResponse addTransaction(TransactionRequest transactionRequest) {
        logger.info("Transaction in process");
        Integer senderAccountId = transactionRequest.getSenderAccount();
        Integer receiverAccountId = transactionRequest.getReceiverAccount();
        double amount = transactionRequest.getAmount();
        String failureReason = null;
        Account senderAccount = accountRepository.findById(senderAccountId).get();
        Account receiverAccount = accountRepository.findById(receiverAccountId).get();
        TransactionType transactionType;
        transactionType = TransactionType.DEBIT;
        if(senderAccount.getBalance() < amount) {
            failureReason = "Insufficient Balance";
            logger.info("Transaction failed");
        }
        else
        {
            senderAccount.setBalance(senderAccount.getBalance() - amount);
            receiverAccount.setBalance(receiverAccount.getBalance() + amount);
            accountRepository.saveAll(Arrays.asList(senderAccount, receiverAccount));
            logger.info("Transaction successful");
        }
        Transaction transaction = new Transaction();
        transaction.setSenderAccount(senderAccount);
        transaction.setReceiverAccount(receiverAccount);
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.DEBIT);
        transactionRepository.save(transaction);
        TransactionResponse transactionResponse = TransactionResponse.builder().account2(receiverAccountId).account1(senderAccountId).id(transaction.getId()).transactionType(transactionType).failureReason(failureReason).amount(amount).transactionType(TransactionType.DEBIT).build();
        return transactionResponse;
    }
    @Lock(PESSIMISTIC_WRITE)
    public void deleteTransaction(Integer id)
    {
        try {
            transactionRepository.deleteById(id);
        }
        catch(NoSuchElementException e){
            logger.info("No such transaction exists");
        }
    }
}