package aman.project.springbootstarter.transaction;

import aman.project.springbootstarter.user.User;
import aman.project.springbootstarter.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService users;

    private double sender_balance = -1;
    private double receiver_balance = -1;


    private List<Transaction> transactions = new ArrayList<>(Arrays.asList(
            new Transaction("ID1", "100", "101", 123),
            new Transaction("ID2", "101", "100", 385)
    ));

    public List<Transaction> getAllTransactions() {
        List<Transaction> users = (List<Transaction>) transactionRepository.findAll();
        return users;
        //return transactions;
    }

    public Optional<Transaction> getTransaction(String id) {
        return transactionRepository.findById(id);
    }

    public void addTransaction(Transaction transaction) {
        String sender = transaction.getSender();
        String receiver = transaction.getReceiver();
        double amount = transaction.getAmount();
        List<User> user = users.getAllUsers();
        for (int i = 0; i < user.size(); i++) {
            String UserT = user.get(i).getId();
            if (sender.equals(UserT)) {
                sender_balance = user.get(i).getBalance();
            }
            if (receiver.equals(UserT)) {
                receiver_balance = user.get(i).getBalance();
            }
        }
        if (sender_balance != -1 && receiver_balance != -1 && amount <= sender_balance) {
            for (int i = 0; i < user.size(); i++) {
                String UserT = user.get(i).getId();
                if (sender == UserT) {
                    user.get(i).setBalance(sender_balance - amount);
                }
                if (receiver == UserT) {
                    user.get(i).setBalance(receiver_balance + amount);
                }
            }
            transactionRepository.save(transaction);
            // do transaction
        } else {

            // abort
        }
    }


    public void deleteTransaction(String id) {
        transactionRepository.deleteById(id);
    }

}
