package controllers;

import lombok.RequiredArgsConstructor;
import models.member.*;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class LoginValidator implements Validator {

    private final MemberDao memberDao;
    @Override
    public boolean supports(Class<?> clazz) {
        return LoginForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LoginForm loginForm = (LoginForm)target;

        String userId = loginForm.getUserId();
        String userPw = loginForm.getUserPw();

        if(userId == null && userId.isBlank() && userPw == null && userPw.isBlank()) {
            return;
        }

        Member member = memberDao.get(userId);
        if (member == null) {
            errors.reject("login.fail");
        }

        if (member != null) {
            boolean matched = BCrypt.checkpw(userPw, member.getUserPw());
            if(!matched) {
                errors.reject("login.fail");
            }
        }
    }
}
