package org.luca.repository;

import io.vertx.ext.auth.User;
import org.luca.models.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends CrudRepository<Users, Long> {

   // List<Users> findByColor(String color);
}
