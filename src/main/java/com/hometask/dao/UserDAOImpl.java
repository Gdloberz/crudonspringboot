package com.hometask.dao;

import com.hometask.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    private EntityManager entityManager;

    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u",User.class).getResultList();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(User user) {
        entityManager.remove(user);
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    public void updateUser(User updateUserold) {
        entityManager.merge(updateUserold);
    }

    @Override
    public boolean ExistUser(User user) {

        TypedQuery<User> query = entityManager.createQuery("select u From User u where u.username=:username",User.class);
        query.setParameter("username", user.getUsername());
        List<User> list = query.getResultList();
        return list.size() == 0;
    }

    @Override
    public User getUserByName(String name) {
        TypedQuery<User> query = entityManager.createQuery("select u From User u where u.username=:username",User.class);
        query.setParameter("username", name);
        return query.getSingleResult();
    }
}
