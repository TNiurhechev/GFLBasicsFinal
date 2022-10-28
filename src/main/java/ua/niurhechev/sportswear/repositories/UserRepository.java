package ua.niurhechev.sportswear.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.niurhechev.sportswear.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByNickname(String nickname);
}
