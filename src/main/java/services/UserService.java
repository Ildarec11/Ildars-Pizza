package services;

import forms.SignInUserForm;
import forms.SignUpUserForm;
import models.Auth;
import models.Dish;
import models.User;

import javax.servlet.http.Cookie;

public interface UserService {
    User register(SignUpUserForm signUpUserForm);

    Cookie signIn(SignInUserForm signInUserForm);

    boolean isAdminByCookieValue(String cookieValue);

    Auth getAuthByCookie(String cookieValue);

    User getUserByCookie(String cookieValue);

    void buyDish(User user, Dish dish);

    boolean emailDoesntExist(String email);
}
