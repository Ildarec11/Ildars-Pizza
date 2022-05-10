//package filters;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import models.Auth;
//import repositories.AuthRepository;
//import repositories.DishRepository;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class MoneyFilter extends HttpFilter {
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
////        HttpServletRequest httpServletRequest  = (HttpServletRequest) req;
////        HttpServletResponse httpServletResponse = (HttpServletResponse) res;
////        Cookie[] cookies = httpServletRequest.getCookies();
////        System.out.println("Money filter");
////        for (Cookie cookie : cookies) {
////            if (cookie.getName().equals("auth")) {
////                Auth auth = authRepository.findByCookieValue(cookie.getValue());
////                if (auth != null) {
////                    ObjectMapper objectMapper = new ObjectMapper();
////                    long productCost = objectMapper.readValue(req.getParameter("productCost"), Long.class);
////                    BigDecimal cost = BigDecimal.valueOf(productCost);
////                    if (auth.getUser().getMoney().compareTo(cost) <= 0) {
////                        //TODO уведомление о нехватке денег
////                        System.out.println("Not enough money");
////                    } else {
////                        chain.doFilter(req, res);
////                    }
////                }
////            }
////        }
//
//        //TODO Код отсюда в контроллер
//    }
//
//
//}
