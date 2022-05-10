package dtos;

import lombok.Data;

@Data
public class SignUpUserDto {

    private String fullname;
    private String email;
    private String password;
    private String repeatPassword;
}
