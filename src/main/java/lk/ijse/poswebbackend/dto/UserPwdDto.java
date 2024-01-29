package lk.ijse.poswebbackend.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserPwdDto {
    private Long id;
    private String oldPassword;
    private String newPassword;
}
