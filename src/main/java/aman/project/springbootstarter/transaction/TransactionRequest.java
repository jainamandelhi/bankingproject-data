package aman.project.springbootstarter.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {
    private Integer id;
    private Integer senderId;
    private Integer receiverId;
    private double amount;
    private TransactionType transactionType;
}
