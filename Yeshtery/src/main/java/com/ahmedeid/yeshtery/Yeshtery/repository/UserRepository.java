package com.ahmedeid.yeshtery.Yeshtery.repository;

import com.ahmedeid.yeshtery.Yeshtery.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
