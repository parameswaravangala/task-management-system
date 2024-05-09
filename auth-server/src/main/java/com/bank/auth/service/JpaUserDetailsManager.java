package com.bank.auth.service;

import com.bank.auth.entity.Permission;
import com.bank.auth.entity.Role;
import com.bank.auth.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;

@Service
public class JpaUserDetailsManager implements UserDetailsManager {
    
    private final UserDetailRepository userRepository;

    @Autowired
    public JpaUserDetailsManager(UserDetailRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(UserDetails user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateUser(UserDetails user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteUser(String username) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean userExists(String username) {
        return this.userRepository.existsByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<com.bank.auth.entity.User> user = userRepository.findByUsername(username);
        if(user.isPresent()) {
            com.bank.auth.entity.User u = user.get();
            return User.builder()
                    .username(u.getUsername())
                    .password(u.getPassword())
                    .roles(u.getRoles().stream().map(Role::getName).toArray(String[]::new))
                    .authorities(u.getRoles().stream().flatMap(role -> role.getPermissions().stream().map(Permission::getName).toList().stream()).toArray(String[]::new))
                    .build();
        }
        throw new UsernameNotFoundException(MessageFormat.format("username {0} not found", username));
    }

}