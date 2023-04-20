package ru.kata.spring.boot_security.demo.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public User getUser(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("Sorry, there is no user with this id.");
        }
        return optionalUser.get();
    }

    @Override
    public User getUser(String name) {
        User user = userRepository.findByUsername(name);
        if (user == null) {
            throw new UsernameNotFoundException("Sorry, there is no user with this name.");
        }
        return user;
    }

    @Override
    @Transactional
    public void update(Long id, User updateUser) {
        User userToUpd = getUser(id);
        userToUpd.setUsername(updateUser.getUsername());
        userToUpd.setLastName(updateUser.getLastName());
        userToUpd.setEmail(updateUser.getEmail());
        userToUpd.setAge(updateUser.getAge());
<<<<<<< HEAD
        if (updateUser.getPassword().isEmpty()) {
            userToUpd.setPassword(userToUpd.getPassword());
        } else {
=======
        if (updateUser.getPassword().isEmpty()){
            userToUpd.setPassword(userToUpd.getPassword());
        }else {
>>>>>>> origin/master
            userToUpd.setPassword(bCryptPasswordEncoder.encode(updateUser.getPassword()));
        }
        userToUpd.setRoles(updateUser.getRoles());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        Hibernate.initialize(user.getRoles());

        return user;
    }
<<<<<<< HEAD

    @Override
    public HashSet<Role> saveRole(String[] selectedRoles) {
        HashSet<Role> editRoles = new HashSet<>();
        for (String roleName : selectedRoles) {
            if (roleName.equals("ADMIN")) {
                editRoles.add(new Role(2L, "ROLE_ADMIN"));
            } else {
                editRoles.add(new Role(1L, "ROLE_USER"));
            }
        }
        return editRoles;
=======
    @Override
    public User getUserWithFields(String name, String surname, Integer age, String email, String password, String role) {
        User userInfoUpdate = new User();
        userInfoUpdate.setUsername(name);
        userInfoUpdate.setLastName(surname);
        userInfoUpdate.setAge(age);
        userInfoUpdate.setEmail(email);
        userInfoUpdate.setPassword(password);
        Set<Role> roles = new HashSet<>();
        Role nRole;
        if (role.equals("ROLE_ADMIN")) {
            nRole = new Role(2L, "ROLE_ADMIN");
        } else {
            nRole = new Role(1L, "ROLE_USER");
        }
        roles.add(nRole);
        userInfoUpdate.setRoles(roles);
        return userInfoUpdate;
>>>>>>> origin/master
    }
}
