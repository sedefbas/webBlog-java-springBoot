package com.sedef.blogWeb.Businnes.concretes;
import com.sedef.blogWeb.Exceptions.NotFoundException;
import com.sedef.blogWeb.Model.User;
import com.sedef.blogWeb.Repository.UserRepository;
import com.sedef.blogWeb.Request.RegisterRequest;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
   private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User addUser(RegisterRequest request) throws NotFoundException {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUserName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAuthorities(request.getAuthorities());
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);

        if (user !=null)  return userRepository.save(user);
        else  throw new NotFoundException("user is null..");

    }


    public User getOneUserById(int id) throws NotFoundException {
        return userRepository.findById((long) id)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + id));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(int id) {
        userRepository.deleteById((long) id);
    }

    public User getByUserName(String name) throws NotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(name);
        if (optionalUser.isPresent()){
            return optionalUser.get();
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return (UserDetails) user.orElseThrow(EntityNotFoundException::new);
    }


}
