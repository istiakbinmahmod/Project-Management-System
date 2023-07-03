package cns.project.service;

import cns.project.entity.User;
import cns.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> fetchUserList() {
        return userRepository.findAll();
    }

    @Override
    public User fetchUserById(Long userId)  {
        return userRepository.findById(userId).get();
    }

    @Override
    public User fetchUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User fetchUserByFirstName(String firstName) {
        return userRepository.findByFirstNameIgnoreCase(firstName);
    }

    @Override
    public User fetchUserByLastName(String lastName) {
        return userRepository.findByLastNameIgnoreCase(lastName);
    }

    @Override
    public User fetchUserByFirstNameAndLastName(String firstName, String lastName) {
        return userRepository.findByFirstNameAndLastNameIgnoreCase(firstName, lastName);
    }

    @Override
    public User fetchUserByFirstNameIgnoreCase(String firstName) {
        return null;
    }
}
