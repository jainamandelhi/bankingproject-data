package aman.project.springbootstarter.transaction;

import aman.project.springbootstarter.account.Account;
import aman.project.springbootstarter.account.AccountRepository;
import aman.project.springbootstarter.account.AccountService;
import aman.project.springbootstarter.user.UserRepository;
import aman.project.springbootstarter.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    /*private List<Transaction> transactions = new ArrayList<>(Arrays.asList(
            new Transaction("ID1", "100", "101", 123),
            new Transaction("ID2", "101", "100", 385)
    ));*/

    public List<Transaction> getAllTransactions() {
        List<Transaction> users = (List<Transaction>) transactionRepository.findAll();
        return users;
        //return transactions;
    }
    public Optional<Transaction> getTransaction(Integer id) {
        return transactionRepository.findById(id);
    }

    @Transactional
    public TransactionResponse addTransaction(TransactionRequest transactionRequest) {
        Integer senderId = transactionRequest.getSenderId();
        Integer receiverId = transactionRequest.getReceiverId();
        double amount = transactionRequest.getAmount();
        String failureReason = null;
        Account senderAccount = accounts.getAccount(senderId).get();
        Account receiverAccount = accounts.getAccount(receiverId).get();
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
        TransactionResponse transactionResponse = TransactionResponse.builder().account2(receiverId).account1(senderId).id(transaction.getId()).transactionType(transactionType).failureReason(failureReason).amount(amount).transactionType(TransactionType.DEBIT).build();
        return transactionResponse;
    }


    public void deleteTransaction(Integer id)
    {
        transactionRepository.deleteById(id);
    }

    public List<Transaction> getTransactionsByAccountId(Integer id) {
        return transactionRepository.getTransactionsByAccountId(id);
    }

    /*public List<Transaction> findTransactionsByAccountId(Integer id)
    {
        return transactionRepository.findTransactionsByAccountId(id);
    }*/
}
