package ua.niurhechev.sportswear.models;

import lombok.Data;

@Data
public class SignUpModel {
    private String nickname;
    private String passwd;
    private String confirm;
}
