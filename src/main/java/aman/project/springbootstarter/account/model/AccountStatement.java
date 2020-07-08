package aman.project.springbootstarter.account.model;

import aman.project.springbootstarter.transaction.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountStatement {
    //private Integer id;
    private Integer transactionId;
    private TransactionType transactionType;
    private Double amount;
    private Integer otherAccount;
}
