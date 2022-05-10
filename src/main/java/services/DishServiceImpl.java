package services;

import forms.DishForm;
import models.Dish;
import models.PurchaseHistoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.DishRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishRepository dishRepository;

    @Override
    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    @Override
    public Dish findById(Long dishId) {
        return dishRepository.findById(dishId).get();
    }

    @Override
    public void addDish(DishForm dishForm) {
        Dish dish = Dish.builder()
                .name(dishForm.getName())
                .description(dishForm.getDescription())
                .cost(BigDecimal.valueOf(dishForm.getCost()))
                .build();
        dishRepository.save(dish);
    }
}
