package aman.project.springbootstarter.account.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {

    @ApiModelProperty(notes = "Auto generated account id")
    private Integer id;

    @ApiModelProperty(notes = "Account Balance")
    private Double balance;

    @ApiModelProperty(notes = "Id of the account holder")
    private Integer userId;

    @ApiModelProperty(notes = "Type of the account: Current or Savings")
    private AccountType accountType;
}
