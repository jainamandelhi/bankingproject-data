package aman.project.springbootstarter.transaction.model;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseView {

    @ApiModelProperty(notes = "Id of the transaction")
    private Integer id;

    @ApiModelProperty(notes = "Date of the transaction")
    private Date date;

    @ApiModelProperty(notes = "Account id from which the amount is debited")
    private Integer senderAccount;

    @ApiModelProperty(notes = "Account id in which thew amount is credited")
    private Integer receiverAccount;

    @ApiModelProperty(notes = "Amount involved in the transaction")
    private double amount;

    @ApiModelProperty(notes = "Type of the transaction: Debit or Credit")
    private TransactionType transactionType;
}
