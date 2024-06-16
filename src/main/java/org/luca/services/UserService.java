package org.luca.services;


import io.vertx.ext.auth.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.luca.models.House;
import org.luca.models.Users;


import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Default
public class UserService {


    @Inject
    EntityManager em;

    @Transactional
    public void saveUser(Users user) {
        em.persist(user);
    }

    @Transactional
    public List<Users> getAllUsers() {
        return em.createQuery("select u from Users u", Users.class).getResultList();

    }




    @Transactional
    public void deleteUserById(Long id) {
        User user = em.find(User.class, id);
        if (user != null) {
            em.remove(user);
        }
    }


    @Transactional
    public void saveHouse(House house) {
        em.persist(house);
    }


    @Transactional
    public Users assegnaCasa(Long userID, List<Long> houseIds) {
//        recupero l utente
        Users user = em.find(Users.class, userID);
        if (user == null) {
            throw new NullPointerException("User not found" + userID);
        }
//        recupero le case
        // Recupera tutte le entità House corrispondenti agli ID forniti
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<House> cq = cb.createQuery(House.class);
        Root<House> houseRoot = cq.from(House.class);
        cq.where(houseRoot.get("id").in(houseIds));
        List<House> houses = em.createQuery(cq).getResultList();
        for(House house : houses) {
            user.addHouse(house);
        }
        return em.merge(user);

    }


    @Transactional
    public List<House> findHousesByUserId(Long userId) {
        Users user = em.find(Users.class, userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }

        // Recupera tutte le case associate a questo utente
        List<House> houses = user.getHouses();


        //   houses.size(); // Esempio di inizializzazione lazy

        return houses;
    }


    @Transactional
    public Optional<Users> findUserByHouseId(Long houseId) {
        // Esegui una query nativa per recuperare l'ID dell'utente associato alla casa specificata
        String sql = "SELECT u.* FROM users u JOIN users_house uh ON u.id = uh.users_id WHERE uh.houses_id = :houseId";

        // Esegui la query nativa e casta il risultato a Users
        Users user = (Users) em.createNativeQuery(sql, Users.class)
                .setParameter("houseId", houseId)
                .getSingleResult();

        // Restituisci il risultato come Optional
        return Optional.ofNullable(user);
    }


    @Transactional
    public Users findUserByHouseAddress(String houseAddress) {
        String jpql = "SELECT u FROM Users u " +
                "JOIN u.houses h " +
                "WHERE h.address = :houseAddress";

        Query query = em.createQuery(jpql, Users.class);
        query.setParameter("houseAddress", houseAddress);

        return (Users) query.getSingleResult();
    }

// metodo alternativo
    @Transactional
    public Users findUserByHouseAddresss(String houseAddress) {
        String sql = "SELECT u.* FROM users u " +
                "JOIN user_house uh ON u.id = uh.user_id " +
                "JOIN house h ON uh.house_id = h.id " +
                "WHERE h.address = :houseAddress";

        // Esegui la query SQL nativa
        Users user = (Users) em.createNativeQuery(sql, Users.class)
                .setParameter("houseAddress", houseAddress)
                .getSingleResult();
        return user;
    }

}