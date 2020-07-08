package aman.project.springbootstarter.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private Integer id;
    private String username;
    private Integer age;
    private String mobileNo;
    private String address;
    private IdentityType identityType;
}
