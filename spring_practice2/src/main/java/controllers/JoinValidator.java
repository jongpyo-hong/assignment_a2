package controllers;

import models.member.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class JoinValidator implements Validator {
    @Autowired
    private MemberDao memberDao;
    @Override
    public boolean supports(Class<?> clazz) {
        return JoinForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        JoinForm joinForm = (JoinForm)target;

        String userId = joinForm.getUserId();
        String userPw = joinForm.getUserPw();
        String userPwRe = joinForm.getUserPwRe();

        // 1. 아이디의 중복 여부 -> 이미 가입된 경우 X
        if ( userId != null && !userId.isBlank() && memberDao.exists(userId)) {
            errors.rejectValue("userId","Duplicate" );
        }

        // 2. 비밀번호, 비밀번호 확인의 일치 여부
        if (userPw != null && !userPw.isBlank()
                && userPwRe != null && !userPwRe.isBlank() && !userPw.equals(userPwRe)) {
            errors.rejectValue("userPwRe", "Incorrect");
        }
    }
}
