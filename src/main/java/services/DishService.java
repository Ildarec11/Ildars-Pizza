package services;

import forms.DishForm;
import models.Dish;
import models.PurchaseHistoryModel;

import java.util.List;

public interface DishService {
    List<Dish> getAllDishes();

    Dish findById(Long dishId);

    void addDish(DishForm dishForm);
}
