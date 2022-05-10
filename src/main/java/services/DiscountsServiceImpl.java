package services;

import models.Discount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.DiscountsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountsServiceImpl implements DiscountsService {

    @Autowired
    private DiscountsRepository discountsRepository;

    @Override
    public void save(Discount discount) {
          discountsRepository.save(discount);
    }

    @Override
    public Optional<Discount> getMaxDiscountByDishId(Long dishId) {
        return discountsRepository.getMaxDiscountByDishId(dishId);
    }

    @Override
    public List<Discount> findAll() {
        return discountsRepository.findAll();
    }
}
