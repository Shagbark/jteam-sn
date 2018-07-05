package ru.jteam.social.network.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.jteam.social.network.common.Constants;
import ru.jteam.social.network.dto.AuthorizationUser;
import ru.jteam.social.network.dto.UserSession;
import ru.jteam.social.network.service.AuthorizationService;
import ru.jteam.social.network.service.UserSessionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author protsko on 29.04.18
 */
@Controller
public class AuthorizationController {

    private final Logger logger = LoggerFactory.getLogger(AuthorizationController.class);

    private final AuthorizationService authService;
    private final UserSessionService sessionService;

    @Autowired
    public AuthorizationController(AuthorizationService authorizationService, UserSessionService sessionService) {
        this.authService = authorizationService;
        this.sessionService = sessionService;
    }

    @GetMapping(value = "/login")
    public String loginPage(Model model) {
        model.addAttribute("authUser", new AuthorizationUser());
        return "login";
    }

    @PostMapping(value = "/login")
    public String loginUser(@ModelAttribute("authUser") final AuthorizationUser authUser,
                            HttpServletResponse response,
                            Model model) {
        boolean isAuthorize = authService.authorize(authUser.getLogin(), authUser.getPassword());
        if (!isAuthorize) {
            model.addAttribute("denied", true);
            return "login";
        }

        UserSession session = sessionService.createSession(authUser.getLogin());
        response.addCookie(new Cookie(Constants.SESSION_ID_COOKIE_NAME, session.getSessionId()));
        return "redirect:/profile";
    }

}
