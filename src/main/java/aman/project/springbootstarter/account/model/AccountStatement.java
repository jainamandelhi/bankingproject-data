package aman.project.springbootstarter.account.model;

import aman.project.springbootstarter.transaction.model.TransactionType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountStatement {

    @ApiModelProperty(notes = "Id of the transaction")
    private Integer transactionId;

    @ApiModelProperty(notes = "Type of the transaction: Debit or Credit")
    private TransactionType transactionType;

    @ApiModelProperty(notes = "Amount debited or credited")
    private Double amount;

    @ApiModelProperty(notes = "Other account involved in the transaction")
    private Integer otherAccount;
}
