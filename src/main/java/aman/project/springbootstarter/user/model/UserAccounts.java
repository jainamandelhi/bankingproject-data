package aman.project.springbootstarter.user.model;

import aman.project.springbootstarter.account.model.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAccounts {
    Integer accountId;
    AccountType accountType;
}
