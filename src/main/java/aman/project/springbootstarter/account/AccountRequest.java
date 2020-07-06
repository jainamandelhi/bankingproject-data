package aman.project.springbootstarter.account;
import aman.project.springbootstarter.user.User;
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
    private Integer ownerId;
    private AccountType accountType;
    private User owner;
}
