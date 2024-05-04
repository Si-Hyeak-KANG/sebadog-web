package com.web.sebadog.modules.main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class MainController {

    @GetMapping("/")
    public String mainView() {
        log.info("[view] (홈)세바독 소개 페이지 접속");
        return "home";
    }

    @GetMapping("/view/login")
    public String loginView() {
        log.info("[view] 로그인 페이지 접속");
        return "login";
    }
}
