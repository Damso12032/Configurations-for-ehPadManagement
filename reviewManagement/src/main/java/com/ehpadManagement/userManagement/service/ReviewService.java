package com.ehpadManagement.userManagement.service;
import com.ehpadManagement.userManagement.ReviewManagementApplication;
import com.ehpadManagement.userManagement.model.Review;
import com.ehpadManagement.userManagement.model.User;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.hibernate.Session;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class ReviewService {

    @Autowired
    private RestTemplate restTemplate;
    public void addReview(Review review){
        Session session= ReviewManagementApplication.sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            session.save(review);

            transaction.commit();
            System.out.println("Avis "+ review +" ajouté dans la bdd");
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

    public void deleteReview(int reviewId) {
        Session session = ReviewManagementApplication.sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Load the user by ID before deleting
            Review review = session.load(Review.class, reviewId);
            session.delete(review);

            transaction.commit();
            System.out.println("Avis avec l'ID " + reviewId + " supprimé de la bdd");
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

    public void editReview(Review updatedReview) {
        Session session = ReviewManagementApplication.sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Merge the updated user into the session
            session.merge(updatedReview);

            transaction.commit();
            System.out.println("Review " + updatedReview + " modifié dans la bdd");
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

    public Review getReviewById(int missionId) {
        Session session = ReviewManagementApplication.sessionFactory.openSession();
        Transaction transaction = null;
        Review review = null;

        try {
            transaction = session.beginTransaction();

            // Retrieve the user by ID
            review = session.get(Review.class, missionId);

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

        return review;
    }

    public List<Review> getAllReviews() {
        Session session = ReviewManagementApplication.sessionFactory.openSession();
        Transaction transaction = null;
        List<Review> reviewList = null;

        try {
            transaction = session.beginTransaction();

            // Retrieve all users
            reviewList = session.createQuery("FROM Review", Review.class).list();

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

        return reviewList;
    }





}
