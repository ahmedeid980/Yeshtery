package com.ahmedeid.yeshtery.Yeshtery.dao;

import com.ahmedeid.yeshtery.Yeshtery.entities.Product;
import com.ahmedeid.yeshtery.Yeshtery.entities.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class YeshteryDao {

    @Autowired
    private EntityManager entityManager;

    public Optional<List<Product>> getProductToUserToGeneralPage() {
        List<Product> products = this.entityManager
                .createQuery("from Product p where p.isDeleted=0 " +
                        "and p.rejected=0 and p.accepted=1").getResultList();
        return Optional.of(products);
    }

    public Optional<List<Product>> getProductToAdministratorPage() {
        List<Product> products = this.entityManager
                .createQuery("from Product p where p.isDeleted=0 " +
                        "and p.rejected=0 and p.accepted=0").getResultList();
        return Optional.of(products);
    }

    @Transactional
    public Optional<Product> getProductToShow(int productId) {
        Session session = entityManager.unwrap(Session.class);
        Product product = (Product) session
                .createQuery("from Product p where p.isDeleted=0 " +
                        " and p.rejected=0 and p.accepted=1 and p.id= :productId")
                .setParameter("productId", productId)
                .uniqueResult();
        return Optional.ofNullable(product);
    }

    @Transactional
    public Optional<Product> getProductToUpdate(int productId) {
        Session session = entityManager.unwrap(Session.class);
        Product product = (Product) session
                .createQuery("from Product p where p.isDeleted=0 " +
                        "and p.rejected=0 and p.accepted=0 and p.id= :productId")
                .setParameter("productId", productId)
                .uniqueResult();
        return Optional.ofNullable(product);
    }

    @Transactional
    public boolean productUpdateToAccepted(int productId) {
        this.entityManager
                .createQuery("update Product p set p.accepted=1 where p.id= :productId")
                .setParameter("productId", productId)
                .executeUpdate();
        return true;
    }

    @Transactional
    public boolean productUpdateToRejected(int productId) {
        this.entityManager
                .createQuery("update Product p set p.rejected=1 where p.id= :productId")
                .setParameter("productId", productId)
                .executeUpdate();
        return true;
    }

    public Optional<User> login(String email, String password) {
        Session session = entityManager.unwrap(Session.class);
        User user = (User) session
                .createQuery("from User u where u.email= :email " +
                        "and u.password= :password")
                .setParameter("email", email)
                .setParameter("password", password)
                .uniqueResult();
        return Optional.ofNullable(user);
    }
}
