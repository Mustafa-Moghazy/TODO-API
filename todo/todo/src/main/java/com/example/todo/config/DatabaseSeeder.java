package com.example.todo.config;

import com.example.todo.entity.Role;
import com.example.todo.entity.User;
import com.example.todo.repository.RoleRepository;
import com.example.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
public class DatabaseSeeder implements CommandLineRunner {
  @Autowired
  private RoleRepository roleRepo;
  @Autowired
  private UserRepository userRepo;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public void run(String... args) throws Exception {
    List<String> roles = Arrays.asList("ROLE_ADMIN", "ROLE_USER");

    for (String roleName : roles){
      if( roleRepo.findByName(roleName).isEmpty() ) {
        Role role = new Role();
        role.setName(roleName);
        roleRepo.save(role);
      }
    }

    // create default admin //
    Set<Role> adminRole = new HashSet<>();
    adminRole.add(roleRepo.findByName("ROLE_ADMIN").orElseThrow());

    if( userRepo.findByUserName("admin").isEmpty() ){
      User user = new User();
      user.setUserName("admin");
      user.setEmail("admin@admin.com");
      user.setPassword(passwordEncoder.encode("admin123"));
      user.setRoles(adminRole);

      userRepo.save(user);
    }

  }
}
