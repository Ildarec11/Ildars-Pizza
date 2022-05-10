package repositories;

import models.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountsRepository extends JpaRepository<Discount, Long> {

    @Query("SELECT max(d.percentage) FROM Discount d WHERE d.dish.id=?1 AND d.endDate <= current_date")
    Optional<Discount> getMaxDiscountByDishId(Long dishId);
}
