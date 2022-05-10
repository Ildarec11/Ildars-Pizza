package services;

import enums.Role;
import forms.SignInUserForm;
import forms.SignUpUserForm;
import models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import repositories.*;

import javax.servlet.http.Cookie;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private DiscountsRepository discountsRepository;
    @Autowired
    private PurchaseHistoryRepository purchaseHistoryRepository;

    @Override
    public User register(SignUpUserForm signUpUserForm) {

        User user = User.builder()
                .fullName(signUpUserForm.getFullName())
                .email(signUpUserForm.getEmail())
                .password(passwordEncoder.encode(signUpUserForm.getPassword()))
                .role(Role.USER)
                .build();
        return userRepository.save(user);
    }

    @Override
    public Cookie signIn(SignInUserForm signInUserForm) {

        User user = userRepository.findByEmail(signInUserForm.getEmail()).get();

        if (user != null) {
            if (passwordEncoder.matches(signInUserForm.getPassword(), user.getPassword())) {
                System.out.println("Вход выполнен");
                String cookieValue = UUID.randomUUID().toString();
                Cookie cookie = new Cookie("auth", cookieValue);
                cookie.setMaxAge(10 * 60 * 60 * 60);
                Auth auth = Auth.builder()
                        .user(user)
                        .cookieValue(cookieValue)
                        .build();
                authRepository.save(auth);
                return cookie;
            } else {
                System.out.println("Вход не выполнен");
            }
        }
        return null;
    }

    @Override
    public boolean isAdminByCookieValue(String cookieValue) {
        User userByCookie = getUserByCookie(cookieValue);
        return userByCookie.getRole().equals(Role.ADMIN);
    }

    @Override
    public Auth getAuthByCookie(String cookieValue) {
        return authRepository.findByCookieValue(cookieValue).get();
    }

    @Override
    public User getUserByCookie(String cookieValue) {
        return authRepository.findByCookieValue(cookieValue).get().getUser();
    }

    @Override
    public void buyDish(User user, Dish dish) {
        Optional<Discount> discountModel = discountsRepository.getMaxDiscountByDishId(dish.getId());
        BigDecimal dishCost = dish.getCost();
        int percentageInt = 1;
        if (discountModel.isPresent()) {
            BigDecimal percentage = BigDecimal.valueOf((long) discountModel.get().getPercentage());
            dishCost = dishCost.multiply(percentage);
            percentageInt = discountModel.get().getPercentage();
        }
        userRepository.spendMoney(user.getId(), dishCost);

        PurchaseHistoryModel purchaseHistoryModel = PurchaseHistoryModel.builder()
                .dish(dish)
                .cost(dishCost)
                .user(user)
                .purchaseDate(Date.from(Instant.now()))
                .discountsPercents(percentageInt)
                .build();
        purchaseHistoryRepository.save(purchaseHistoryModel);
    }

    @Override
    public boolean emailDoesntExist(String email) {
        return !userRepository.existsByEmail(email);
    }
}
