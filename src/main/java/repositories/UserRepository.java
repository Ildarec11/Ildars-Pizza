package repositories;

import models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Repository("userRepository")
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Modifying
    @Query("update User u set u.money = ?2 where u.id = ?1")
    void spendMoney(Long id, BigDecimal money);

    boolean existsByEmail(String email);
}
