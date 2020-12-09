package com.aarshinkov.masters.rest;

import com.aarshinkov.masters.entities.UserEntity;
import com.aarshinkov.masters.errors.CustomException;
import com.aarshinkov.masters.repositories.UsersRepository;
import com.aarshinkov.masters.security.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private WebSecurityConfig webSecurityConfig;

    @PostMapping("/login")
    public UserDetails login(@RequestParam("email") String email,
                             @RequestParam("password") String password, HttpSession session) throws CustomException {

        UserEntity user = usersRepository.findByEmail(email);

        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                // Authentication successful
                session.setAttribute("user", user);

                try {
                    UserDetails userDetails = webSecurityConfig.userDetailsServiceBean().loadUserByUsername(user.getEmail());

                    if (userDetails != null) {
                        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
                                userDetails.getPassword(), userDetails.getAuthorities());

                        SecurityContextHolder.getContext().setAuthentication(auth);

                        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

                        HttpSession http = attrs.getRequest().getSession(true);
                        http.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

                        return userDetails;
                    }

                    return null;
                } catch (UsernameNotFoundException e) {
                    e.printStackTrace();
                    throw new CustomException(404, "Not found", "User with email " + email + " not found", HttpStatus.NOT_FOUND);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            throw new CustomException(4001, "Bad credentials", "Credentials not match", HttpStatus.BAD_REQUEST);
        }
        throw new CustomException(404, "Not found", "User with email " + email + " not found", HttpStatus.NOT_FOUND);
    }
}
