package ru.jteam.social.network.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.jteam.social.network.dto.AuthorizationUser;
import ru.jteam.social.network.service.AuthorizationService;

/**
 * @author protsko on 29.04.18
 */
@Controller
public class AuthorizationController {

    private final Logger logger = LoggerFactory.getLogger(AuthorizationController.class);

    private final AuthorizationService authService;

    @Autowired
    public AuthorizationController(AuthorizationService authorizationService) {
        this.authService = authorizationService;
    }

    @GetMapping(value = "/login")
    public String loginPage(Model model) {
        model.addAttribute("authUser", new AuthorizationUser());
        return "login";
    }

    @PostMapping(value = "/login")
    public String loginUser(@ModelAttribute("authUser") final AuthorizationUser authUser,
                            Model model) {
        logger.info("login: " + authUser.getLoginOrEmail() + ", password: " + authUser.getPassword());
        boolean isAuthorize = authService.authorize(authUser.getLoginOrEmail(), authUser.getPassword());
        if (!isAuthorize) {
            model.addAttribute("denied", true);
            return "login";
        }
        return "redirect:/profile";
    }

}
