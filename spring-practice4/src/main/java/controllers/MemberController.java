package controllers;

import models.member.MemberJoinService;
import models.member.MemberLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private JoinValidator joinValidator;
    @Autowired
    private LoginValidator loginValidator;
    @Autowired
    private Optional<MemberJoinService> opt;
    @Autowired
    private MemberLoginService loginService;
    @GetMapping("/join")
    public String join(@ModelAttribute JoinForm joinForm) {

        return "member/join";
    }

    @PostMapping("/join")
    public String joinPs(@Valid JoinForm joinForm, Errors errors) {

        if (errors.hasErrors()) {

            return "member/join";
        }
        // 회원가입 처리
        MemberJoinService joinService = opt.get();
        joinService.join(joinForm);
        return "redirect:/member/login";
    }
    @GetMapping("/login")
    public String login(@ModelAttribute LoginForm loginForm,
                        @CookieValue(required = false) String saveId) {
        System.out.println(saveId);

        if (saveId != null) {
            loginForm.setUserId(saveId);
            loginForm.setSaveId(true);
        }

        return "member/login";
    }

    @PostMapping("/login")
    public String loginPs(@Valid LoginForm loginForm, Errors errors) {
        loginValidator.validate(loginForm, errors);

        if (errors.hasErrors()) {
            return "member/login";
        }

        loginService.login(loginForm);


        return "redirect:/";
    }
}
