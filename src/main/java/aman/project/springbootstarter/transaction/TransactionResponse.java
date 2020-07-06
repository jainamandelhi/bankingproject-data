package aman.project.springbootstarter.transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
    private Integer id;
    String failureReason;
    private Double amount;
    private TransactionType transactionType;
    private Integer account1;
    private Integer account2;
}
