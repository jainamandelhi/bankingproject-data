package aman.project.springbootstarter.transaction.model;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {

    @ApiModelProperty(notes = "Id of the transaction")
    private Integer id;

    @ApiModelProperty(notes = "Reason of the failure, in case transaction failed")
    String failureReason;

    @ApiModelProperty(notes = "Amount involved in the transaction")
    private Double amount;

    @ApiModelProperty(notes = "Type of the transaction: Debit or Credit")
    private TransactionType transactionType;

    @ApiModelProperty(notes = "Account id of the transaction maker")
    private Integer account1;

    @ApiModelProperty(notes = "Account id of the other account involved")
    private Integer account2;
}
