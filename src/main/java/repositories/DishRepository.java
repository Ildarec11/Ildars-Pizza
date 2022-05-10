package repositories;

import forms.DishForm;
import models.Dish;
import models.PurchaseHistoryModel;
import models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

    Optional<Dish> findById(Long dishId);
}
