package com.aarshinkov.masters.controllers;

import com.aarshinkov.masters.entities.UserEntity;
import com.aarshinkov.masters.repositories.UsersRepository;
import com.aarshinkov.masters.security.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private WebSecurityConfig webSecurityConfig;
    
    @GetMapping("/login")
    public String prepareLogin(Model model) {
        model.addAttribute("globalMenu", "login");

        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password, HttpSession session) {

        UserEntity user = usersRepository.findByEmail(email);

        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                // Authentication successful
                session.setAttribute("user", user);

                try {
                    UserDetails userDetails = webSecurityConfig.userDetailsServiceBean()
                            .loadUserByUsername(user.getEmail());

                    if (userDetails != null) {
                        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
                                userDetails.getPassword(), userDetails.getAuthorities());

                        SecurityContextHolder.getContext().setAuthentication(auth);

                        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

                        HttpSession http = attrs.getRequest().getSession(true);
                        http.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
                    }

                    return "redirect:/";
                } catch (UsernameNotFoundException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return "redirect:/login";
    }
}
