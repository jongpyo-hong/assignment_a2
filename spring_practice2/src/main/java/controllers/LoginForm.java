package controllers;

import lombok.Data;

@Data
public class LoginForm {
    private String userId;
    private String userPw;
    private boolean saveId;
}
