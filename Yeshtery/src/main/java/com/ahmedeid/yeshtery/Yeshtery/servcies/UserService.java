package com.ahmedeid.yeshtery.Yeshtery.servcies;

import com.ahmedeid.yeshtery.Yeshtery.entities.User;
import com.ahmedeid.yeshtery.Yeshtery.repository.UserRepository;
import com.ahmedeid.yeshtery.Yeshtery.validate.SystemValidation;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<List<User>> getAllUsers() {
        return Optional.ofNullable(this.userRepository.findAll());
    }

    public Optional<User> saveUser(User user) {

        // validate user at first ...
        SystemValidation.validateUser(user);

        // save user ...
        return Optional.of(this.userRepository.save(user));
    }
}
