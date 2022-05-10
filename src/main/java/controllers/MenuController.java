package controllers;

import forms.DishForm;
import models.Dish;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import services.DishService;
import services.UserService;

import java.util.List;

@Controller
public class MenuController {

    @Autowired
    private DishService dishService;
    @Autowired
    private UserService userService;

    @GetMapping("/menu")
    public ModelAndView getMenuPage() {
        List<Dish> dishes = dishService.getAllDishes();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("menu");
        modelAndView.addObject("menu", dishes);
        return modelAndView;
    }

    @PostMapping("/add-dish")
    public void addDish(DishForm dishForm) {
        dishService.addDish(dishForm);
    }

    @PostMapping("/buy")
    public void buy(Authentication authentication, Dish dish) {
        User user = (User) authentication.getPrincipal();
        userService.buyDish(user, dish);
    }

}
