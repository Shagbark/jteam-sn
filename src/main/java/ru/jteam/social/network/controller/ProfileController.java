package ru.jteam.social.network.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author protsko on 28.04.18
 */
@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String displayProfile() {
        return "profile";
    }

}
