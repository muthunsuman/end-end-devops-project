package com.jtspringproject.JtSpringProject.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jtspringproject.JtSpringProject.models.User;

@Repository
public class userDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<User> getAllUser() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Transactional
    public User saveUser(User user) {
        entityManager.merge(user);
        return user;
    }

    @Transactional
    public boolean userExists(String username) {
        List<User> users = entityManager.createQuery("from User where username = :username", User.class)
                                        .setParameter("username", username)
                                        .getResultList();
        return !users.isEmpty();
    }

    @Transactional
    public User getUserByUsername(String username) {
        try {
            return entityManager.createQuery("from User where username = :username", User.class)
                                .setParameter("username", username)
                                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }
}
