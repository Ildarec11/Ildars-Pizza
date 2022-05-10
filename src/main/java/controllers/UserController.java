package controllers;

import dtos.SignInUserDto;
import dtos.SignUpUserDto;
import enums.Role;
import forms.SignInUserForm;
import forms.SignUpUserForm;
import models.BoughtDish;
import models.PurchaseHistoryModel;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import services.DishService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private DishService dishService;

    @GetMapping("/signUp")
    public ModelAndView getSignUpPage() {
        return new ModelAndView("signUp");
    }

    @PostMapping("/signUp")
    public ModelAndView signUp(SignUpUserDto signUpUserDto, HttpServletResponse response) {
        SignUpUserForm signUpUserForm = SignUpUserForm.builder()
                .fullName(signUpUserDto.getFullname())
                .email(signUpUserDto.getEmail())
                .password(signUpUserDto.getPassword())
                .build();
        User user = userService.register(signUpUserForm);
        if (user != null) {
            SignInUserForm signInUserForm = SignInUserForm.builder()
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .build();

            Cookie cookie = userService.signIn(signInUserForm);
            cookie.setMaxAge(10 * 60 * 60);

            response.addCookie(cookie);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("redirect:/games");
            return modelAndView;
        }
        return new ModelAndView("redirect:/signUp");
    }

    @GetMapping("/signIn")
    public ModelAndView getSignInPage() {
        return new ModelAndView("signIn");
    }

    @PostMapping("/signIn")
    public ModelAndView signIn(SignInUserDto signInUserDto, HttpServletResponse resp) throws ServletException, IOException {

        SignInUserForm signInUserForm = SignInUserForm.builder()
                .email(signInUserDto.getEmail())
                .password(signInUserDto.getPassword())
                .build();
        Cookie cookie = userService.signIn(signInUserForm);
        if (cookie == null) {
            resp.addCookie(cookie);
            return new ModelAndView("redirect:/signIn");
        }
        return new ModelAndView("redirect:/menu");
    }

    @GetMapping("/profile")
    public ModelAndView getProfilePage(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) authentication.getPrincipal();
        modelAndView.addObject("user", user);

        boolean isAdmin = (user.getRole() == Role.ADMIN);
        List<PurchaseHistoryModel> history = user.getPurchaseHistory();
        List<BoughtDish> boughtDishes = new ArrayList<>();
        for (PurchaseHistoryModel purchase : history) {
            BoughtDish boughtDish = new BoughtDish();
            boughtDish.setDish(dishService.findById(purchase.getId()));
            boughtDish.setPurchaseHistoryModel(purchase);
            boughtDishes.add(boughtDish);
        }
        modelAndView.addObject("bought", boughtDishes);
        if (isAdmin) {
            modelAndView.setViewName("profileAdmin");
        } else {
            modelAndView.setViewName("profile");
        }
        return modelAndView;
    }
}
