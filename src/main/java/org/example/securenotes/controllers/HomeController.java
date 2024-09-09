package org.example.securenotes.controllers;


import lombok.RequiredArgsConstructor;
import org.example.securenotes.model.NoteUser;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class HomeController {
    private final ConversionService conversionService;

    @GetMapping
    public String home(
            Authentication authentication,
            Model model
    ) {
        // get authenticated user information and send it to the user interface
        if(authentication == null) return "index";

        NoteUser noteUser = (NoteUser) getAuthenticatedUserDetail(authentication);
        System.out.println(noteUser);
        model.addAttribute("user", noteUser);
        return "index";
    }

    private NoteUser getAuthenticatedUserDetail(Authentication authentication){
        Object principal = authentication.getPrincipal();

        String fullName = "";
        String email = "";
        String image = "";

        //        Google
        if(principal instanceof DefaultOidcUser) {
            DefaultOidcUser user = (DefaultOidcUser) authentication.getPrincipal();
            fullName = user.getFullName();
            email = user.getEmail();
            image = user.getPicture();
            System.out.println(image);

        } else if(principal instanceof DefaultOAuth2User) {
            //        Github
            DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();

            fullName = user.getAttribute("name");
            email = user.getAttribute("email");
            image = user.getAttribute("avatar_url");

        }

        return new NoteUser(fullName, email, image);
    }
}
