package org.luca.services;

import io.vertx.ext.auth.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.luca.models.Users;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
@Default
public class UserService {




    @PersistenceContext
    EntityManager em;

    @Transactional
public void saveUser(Users user){
    em.persist(user);
}

@Transactional
    public List<Users> getAllUsers(){
        return em.createQuery("select u from Users u", Users.class).getResultList();

}

}
