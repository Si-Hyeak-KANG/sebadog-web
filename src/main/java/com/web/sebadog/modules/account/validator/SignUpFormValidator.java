package com.web.sebadog.modules.account.validator;

import com.web.sebadog.modules.account.dto.SignUpFormDto;
import com.web.sebadog.modules.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class SignUpFormValidator implements Validator {


    private final AccountRepository accountRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(SignUpFormDto.class);
    }

    /**
     * 1) 이메일 중복체크
     * 2) 비밀번호와 비밀번호 재입력 일치여부
     * 3) 닉네임 중복체크
     * @param target the object that is to be validated
     * @param errors contextual state about the validation process
     */
    @Override
    public void validate(Object target, Errors errors) {

        SignUpFormDto signUpForm = (SignUpFormDto) target;

        if (accountRepository.existsByEmail(signUpForm.getEmail())) {
            errors.rejectValue(
                    "email",
                    "invalid.email",
                    new Object[]{signUpForm.getEmail()},
                    "이미 사용중인 이메일입니다.");
        }

        if(!signUpForm.getPassword().equals(signUpForm.getRePassword())) {
            errors.rejectValue(
                    "rePassword",
                    "invalid.rePassword",
                    new Object[]{signUpForm.getRePassword()},
                    "재입력한 비밀번호가 일치하지 않습니다.");
        }

        if (accountRepository.existsByNickname(signUpForm.getNickname())) {
            errors.rejectValue(
                    "nickname",
                    "invalid.nickname",
                    new Object[]{signUpForm.getNickname()},
                    "이미 사용중인 닉네임입니다.");
        }

    }
}
