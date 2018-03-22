package ru.jteam.social.network.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author protsko on 22.03.18
 */
@Controller
public class LoginController {

    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }

}
