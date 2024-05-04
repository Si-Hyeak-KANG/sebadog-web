package com.web.sebadog.modules.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainView() {
        return "home";
    }

    @GetMapping("/view/login")
    public String loginView() {
        return "login";
    }
}
