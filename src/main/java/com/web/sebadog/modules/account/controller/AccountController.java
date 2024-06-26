package com.web.sebadog.modules.account.controller;

import com.web.sebadog.modules.account.dto.CertificationNumberDto;
import com.web.sebadog.modules.account.dto.SignUpFormDto;
import com.web.sebadog.modules.account.service.AccountService;
import com.web.sebadog.modules.account.validator.SignUpFormValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Controller
public class AccountController {

    private final SignUpFormValidator signUpFormValidator;
    private final AccountService accountService;

    @InitBinder("signUpFormDto")
    public void enrollFormInitBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(signUpFormValidator);
    }


    @GetMapping("/view/sign-up")
    public String signUpView(Model model) {
        log.info("[view] 회원가입 페이지 접속");
        model.addAttribute(new SignUpFormDto());
        return "account/sign-up";
    }

    @PostMapping("/account/sign-up")
    public String signUp(@Valid SignUpFormDto signUpFormDto, Errors errors, Model model) {
        if (errors.hasErrors()) {
            getErrorsLog(errors);
            return "account/sign-up";
        }
        accountService.processNewAccount(signUpFormDto);
        model.addAttribute(new CertificationNumberDto());
        return "account/check_c_number";
    }

    @ResponseBody
    @PostMapping("/account/check/certification-number")
    public ResponseEntity checkCertificationNumber(@RequestBody CertificationNumberDto certificationNumberDto) {
        boolean isMatch = accountService.checkCertificationNumber(certificationNumberDto);
        if(isMatch) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

    @ResponseBody
    @PostMapping("/account/re/send/certification-number")
    public ResponseEntity checkCertificationNumber(@RequestBody HashMap<String,String> request) {
        accountService.reSendCertificationNumberToEmail(request.get("email"));
        return ResponseEntity.ok().build();
    }

    private static void getErrorsLog(Errors errors) {
        errors.getAllErrors()
                .forEach(e ->
                        log.error("REASON={}", Objects.requireNonNull(e.getDefaultMessage())));
    }
}
