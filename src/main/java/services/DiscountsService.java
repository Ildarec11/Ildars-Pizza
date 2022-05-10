package services;

import models.Discount;

import java.util.List;
import java.util.Optional;

public interface DiscountsService {

    void save(Discount discount);

    Optional<Discount> getMaxDiscountByDishId(Long dishId);

    public List<Discount> findAll();
}
