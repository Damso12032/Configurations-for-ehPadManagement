package com.ehpadManagement.userManagement.service;
import com.ehpadManagement.userManagement.UserManagementApplication;
import com.ehpadManagement.userManagement.model.User;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.hibernate.Session;

import java.util.List;
@Service
public class UserService {
    public void addUser(User user){
        Session session= UserManagementApplication.sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            session.save(user);

            transaction.commit();
            System.out.println("Utilisateur "+user+" ajouté dans la bdd");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    public void deleteUser(int userId) {
        Session session = UserManagementApplication.sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Load the user by ID before deleting
            User user = session.load(User.class, userId);
            session.delete(user);

            transaction.commit();
            System.out.println("Utilisateur avec l'ID " + userId + " supprimé de la bdd");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void editUser(User updatedUser) {
        Session session = UserManagementApplication.sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Merge the updated user into the session
            session.merge(updatedUser);

            transaction.commit();
            System.out.println("Utilisateur " + updatedUser + " modifié dans la bdd");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public User getUserById(int userId) {
        Session session = UserManagementApplication.sessionFactory.openSession();
        Transaction transaction = null;
        User user = null;

        try {
            transaction = session.beginTransaction();

            // Retrieve the user by ID
            user = session.get(User.class, userId);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return user;
    }

    public List<User> getAllUsers() {
        Session session = UserManagementApplication.sessionFactory.openSession();
        Transaction transaction = null;
        List<User> userList = null;

        try {
            transaction = session.beginTransaction();

            // Retrieve all users
            userList = session.createQuery("FROM User", User.class).list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return userList;
    }

    public List<User> getAllUsersByStatut(User.Statut statut) {
        Session session = UserManagementApplication.sessionFactory.openSession();
        Transaction transaction = null;
        List<User> userList = null;

        try {
            transaction = session.beginTransaction();

            // Retrieve users by Statut
            userList = session.createQuery("FROM User WHERE statut = :statut", User.class)
                    .setParameter("statut", statut)
                    .list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return userList;
    }



}
