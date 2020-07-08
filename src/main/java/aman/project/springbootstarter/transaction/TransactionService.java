package aman.project.springbootstarter.transaction;

import aman.project.springbootstarter.account.model.Account;
import aman.project.springbootstarter.account.AccountRepository;
import aman.project.springbootstarter.account.AccountService;
import aman.project.springbootstarter.transaction.model.*;
import aman.project.springbootstarter.user.UserRepository;
import aman.project.springbootstarter.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public List<TransactionResponseView> getAllTransactions() {
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
    public TransactionResponseView getTransaction(Integer id) {
        Transaction transaction = transactionRepository.findById(id).get();
        TransactionResponseView transactionResponseView = new TransactionResponseView();
        transactionResponseView = transactionHelper.convertTransactionResponse(transaction);
        return transactionResponseView;
    }

    @Transactional
    public TransactionResponse addTransaction(TransactionRequest transactionRequest) {
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
        }
        else
        {
            senderAccount.setBalance(senderAccount.getBalance() - amount);
            receiverAccount.setBalance(receiverAccount.getBalance() + amount);
            accountRepository.saveAll(Arrays.asList(senderAccount, receiverAccount));
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


    public void deleteTransaction(Integer id)
    {
        transactionRepository.deleteById(id);
    }
}
