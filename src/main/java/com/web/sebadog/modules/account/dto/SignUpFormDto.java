package com.web.sebadog.modules.account.dto;

import com.web.sebadog.modules.account.Account;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpFormDto {

    @Email(message = "이메일 형식에 맞게 입력해주세요.")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;


    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 6, max = 20, message = "6자 이상, 20자 이하로 입력해주세요.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$", message = "영문과 숫자를 모두 포함하여 작성해주세요.")
    private String password;

    @NotBlank(message = "비밀번호를 재입력해주세요.")
    private String rePassword;


    @NotBlank(message = "닉네임을 입력해주세요.")
    @Size(min = 2, message = "2자 이상 입력해주세요.")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z]+$", message = "한글과 영문만 허용합니다.")
    private String nickname;

    public Account toEntity() {
        return Account.of(this.email, this.password, this.nickname);
    }
}
