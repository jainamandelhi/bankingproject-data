package aman.project.springbootstarter.user.model;

import aman.project.springbootstarter.account.model.AccountType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAccounts {

    @ApiModelProperty(notes = "Id of the account")
    Integer accountId;

    @ApiModelProperty(notes = "Type of the account")
    AccountType accountType;
}
