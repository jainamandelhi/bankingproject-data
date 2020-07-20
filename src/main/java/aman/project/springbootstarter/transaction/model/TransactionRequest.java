package aman.project.springbootstarter.transaction.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {
    private Date date;
    private Integer senderAccount;
    private Integer receiverAccount;
    private double amount;
    private TransactionType transactionType;
}
