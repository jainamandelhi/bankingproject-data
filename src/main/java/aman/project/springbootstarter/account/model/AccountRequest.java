package aman.project.springbootstarter.account.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {
    private Integer id;
    private Double balance;
    private Integer userId;
    private AccountType accountType;
}
