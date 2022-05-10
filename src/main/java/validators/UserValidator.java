package validators;

import dtos.SignUpUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import services.UserService;

import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return SignUpUserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        SignUpUserDto signUpUserDto = (SignUpUserDto) obj;
        ValidationUtils.rejectIfEmpty(errors, "username", "username", "Не указано Имя");
        ValidationUtils.rejectIfEmpty(errors, "email", "email", "Не указан Логин");
        ValidationUtils.rejectIfEmpty(errors, "password", "password", "Не указан Пароль");
        ValidationUtils.rejectIfEmpty(errors, "consent", "consent", "Не указано Согласие");
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE);
        if (!(pattern.matcher(signUpUserDto.getEmail()).matches())) {
            errors.reject("email", "Неправильный email");
        }

        if (!userService.emailDoesntExist(signUpUserDto.getEmail())) {
            errors.reject("email", "Пользователь с таким логином существует");
        }

        if (!signUpUserDto.getPassword().equals(signUpUserDto.getRepeatPassword())) {
            errors.reject("confirmPassword", "Пароли не совпадают");
        }
    }
}
