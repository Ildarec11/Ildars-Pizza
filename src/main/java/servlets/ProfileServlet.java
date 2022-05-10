//package servlets;
//
//import models.BoughtDish;
//import models.PurchaseHistoryModel;
//import repositories.*;
//import services.DishService;
//import services.DishServiceImpl;
//import services.UserService;
//import services.UserServiceImpl;
//import models.Auth;
//
//import javax.servlet.ServletException;
//import javax.servlet.UnavailableException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//@WebServlet("/profile")
//public class ProfileServlet extends HttpServlet {
//
//    private UserService userService;
//    private DishService dishService;
//    private final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
//    private final String DB_USERNAME = "postgres";
//    private final String DB_PASSWORD = "postgres";
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        Auth auth = (Auth) req.getAttribute("auth");
//        req.setAttribute("user", auth.getUser());
//        boolean isAdmin = userService.isAdminByCookieValue(auth.getCoolieValue());
//        List<PurchaseHistoryModel> history = dishService.getPurchaseHistoryByUserId(auth.getUser().getId());
//        List<BoughtDish> boughtDishes = new ArrayList<>();
//        for (PurchaseHistoryModel purchase: history) {
//            BoughtDish boughtDish = new BoughtDish();
//            boughtDish.setDish(dishService.findById(purchase.getDishId()));
//            boughtDish.setPurchaseHistoryModel(purchase);
//            boughtDishes.add(boughtDish);
//        }
//        req.setAttribute("bought", boughtDishes);
//        if (isAdmin) {
//            req.getRequestDispatcher("WEB-INF/jsp/ProfileAdmin.jsp").forward(req, resp);
//        } else {
//            req.getRequestDispatcher("WEB-INF/jsp/Profile.jsp").forward(req, resp);
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
//    }
//
//    @Override
//    public void init() throws ServletException {
//        super.init();
//        try {
//            Class.forName("org.postgresql.Driver");
//            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
//
//            UserRepository userRepository = new UserRepositoryImpl(connection);
//            AuthRepository authRepository = new AuthRepositoryImpl(connection);
//            DishRepository dishRepository = new DishRepositoryImpl(connection);
//            DiscountsRepository discountsRepository = new DiscountsRepositoryImpl(connection);
//            userService = new UserServiceImpl(userRepository, authRepository, dishRepository, discountsRepository);
//            dishService = new DishServiceImpl(dishRepository);
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new UnavailableException("Unavailable");
//        }
//    }
//}
