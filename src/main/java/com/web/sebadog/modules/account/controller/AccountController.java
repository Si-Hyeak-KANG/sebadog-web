package com.web.sebadog.modules.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    @GetMapping("/view/sign-up")
    public String signUpView() {
        return "account/sign-up";
    }
}
