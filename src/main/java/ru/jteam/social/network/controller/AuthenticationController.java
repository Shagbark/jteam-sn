package ru.jteam.social.network.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.jteam.social.network.dto.UserRegistration;
import ru.jteam.social.network.service.RegistrationService;

import javax.validation.Valid;

/**
 * @author protsko on 22.03.18
 */
@Controller
public class AuthenticationController {

    private final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private final RegistrationService registrationService;

    @Autowired
    public AuthenticationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "/registration")
    public String registrationPage(Model model) {
        model.addAttribute("userRegistration", new UserRegistration());
        return "registration";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute("userRegistration") @Valid UserRegistration userRegistration,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        registrationService.register(userRegistration);
        return "redirect:/login";
    }

}
