package ru.jteam.social.network.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.jteam.social.network.dto.RegistrationDto;

import javax.validation.Valid;

/**
 * @author protsko on 22.03.18
 */
@Controller
public class AuthenticationController {

    private final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "/registration")
    public String registrationPage(Model model) {
        model.addAttribute("registrationDto", new RegistrationDto());
        return "registration";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute("registrationDto") @Valid RegistrationDto registrationDto,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        return "registration";
    }

}
