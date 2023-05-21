package controllers;

import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class JoinForm {
    @NotBlank
    @Size(min = 6,max = 16)
    private String userId;
    @NotBlank
    @Size(min = 8)
    private String userPw;
    @NotBlank
    private String userPwRe;
    @NotBlank
    private String userNm;
    @AssertTrue
    private boolean agree;
    private Address address;
    private List<String> hobby;

}
