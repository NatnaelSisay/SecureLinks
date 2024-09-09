package org.example.securenotes.utilities;

import org.example.securenotes.model.NoteUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

public class UserUtils {

    public static NoteUser getAuthenticatedUserDetail(Authentication authentication){
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
