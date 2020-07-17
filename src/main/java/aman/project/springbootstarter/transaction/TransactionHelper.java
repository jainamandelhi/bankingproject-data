package aman.project.springbootstarter.transaction;

import aman.project.springbootstarter.transaction.model.Transaction;
import aman.project.springbootstarter.transaction.model.TransactionRequest;
import aman.project.springbootstarter.transaction.model.TransactionResponse;
import aman.project.springbootstarter.transaction.model.TransactionResponseView;
import org.springframework.stereotype.Component;

@Component
public class TransactionHelper {
    public TransactionRequest convertTransactionRequest(Transaction transaction)
    {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setAmount(transaction.getAmount());
        transactionRequest.setDate(transaction.getDate());
        //transactionRequest.setId(transaction.getId());
        transactionRequest.setReceiverAccount(transaction.getReceiverAccount().getId());
        transactionRequest.setSenderAccount(transaction.getSenderAccount().getId());
        transactionRequest.setTransactionType(transaction.getTransactionType());
        return transactionRequest;
    }
    public TransactionResponseView convertTransactionResponse(Transaction transaction)
    {
        TransactionResponseView transactionResponseView = new TransactionResponseView();
        transactionResponseView.setAmount(transaction.getAmount());
        transactionResponseView.setDate(transaction.getDate());
        transactionResponseView.setId(transaction.getId());
        transactionResponseView.setReceiverAccount(transaction.getReceiverAccount().getId());
        transactionResponseView.setSenderAccount(transaction.getSenderAccount().getId());
        transactionResponseView.setTransactionType(transaction.getTransactionType());
        return transactionResponseView;
    }
}
