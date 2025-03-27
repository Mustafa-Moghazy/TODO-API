package com.example.todo.config;

import com.example.todo.entity.Role;
import com.example.todo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


@Component
public class DatabaseSeeder implements CommandLineRunner {
  @Autowired
  private RoleRepository roleRepo;

  @Override
  public void run(String... args) throws Exception {
    List<String> roles = Arrays.asList("ADMIN", "USER");

    for (String roleName : roles){
      if( roleRepo.findByName(roleName).isEmpty() ) {
        Role role = new Role();
        role.setName(roleName);
        roleRepo.save(role);
      }
    }
  }
}
