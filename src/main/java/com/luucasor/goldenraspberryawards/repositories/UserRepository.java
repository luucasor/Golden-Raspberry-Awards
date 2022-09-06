package com.luucasor.goldenraspberryawards.repositories;

import com.luucasor.goldenraspberryawards.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmailAndPassword(String email, String password);
    User findByEmail(String email);
    Long countByEmail(String email);
    User findById(Integer userId);
}
