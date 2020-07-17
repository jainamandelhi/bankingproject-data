package aman.project.springbootstarter.user.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    @ApiModelProperty(notes = "Id of the User")
    private Integer id;

    @ApiModelProperty(notes = "Username")
    private String username;

    @ApiModelProperty(notes = "Age of the user")
    private Integer age;

    @ApiModelProperty(notes = "Mobile no of the user")
    private String mobileNo;

    @ApiModelProperty(notes = "Address of the user")
    private String address;

    @ApiModelProperty(notes = "Identity type of the User")
    private IdentityType identityType;
}
