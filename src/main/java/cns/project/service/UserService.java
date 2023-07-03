package cns.project.service;

import cns.project.entity.User;
import org.springframework.ui.Model;

import java.util.List;

public interface UserService {

    public User saveUser(User user);

    public List<User> fetchUserList();

    public User fetchUserById(Long userId);

    public User fetchUserByEmail(String email);

    public User fetchUserByFirstName(String firstName);

    public User fetchUserByLastName(String lastName);

    public User fetchUserByFirstNameAndLastName(String firstName, String lastName);

    public User fetchUserByFirstNameIgnoreCase(String firstName);

    public User login(String email, String password);
}