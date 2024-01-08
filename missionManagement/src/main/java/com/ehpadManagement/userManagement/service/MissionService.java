package com.ehpadManagement.userManagement.service;
import com.ehpadManagement.userManagement.MissionManagementApplication;
import com.ehpadManagement.userManagement.model.Mission;
import com.ehpadManagement.userManagement.model.User;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.hibernate.Session;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class MissionService {

    @Autowired
    private RestTemplate restTemplate;
    public void addMission(Mission mission){
        Session session= MissionManagementApplication.sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            session.save(mission);

            transaction.commit();
            System.out.println("Mission "+ mission +" ajouté dans la bdd");
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

    public void deleteMission(long missionId) {
        Session session = MissionManagementApplication.sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Load the user by ID before deleting
            Mission mission = session.load(Mission.class, missionId);
            session.delete(mission);

            transaction.commit();
            System.out.println("Mission avec l'ID " + missionId + " supprimé de la bdd");
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

    public void editMission(Mission updatedMission) {
        Session session = MissionManagementApplication.sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Merge the updated user into the session
            session.merge(updatedMission);

            transaction.commit();
            System.out.println("Mission " + updatedMission + " modifié dans la bdd");
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

    public Mission getMissionById(long missionId) {
        Session session = MissionManagementApplication.sessionFactory.openSession();
        Transaction transaction = null;
        Mission mission = null;

        try {
            transaction = session.beginTransaction();

            // Retrieve the user by ID
            mission = session.get(Mission.class, missionId);

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

        return mission;
    }

    public List<Mission> getAllMissions() {
        Session session = MissionManagementApplication.sessionFactory.openSession();
        Transaction transaction = null;
        List<Mission> missionList = null;

        try {
            transaction = session.beginTransaction();

            // Retrieve all users
            missionList = session.createQuery("FROM Mission", Mission.class).list();

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

        return missionList;
    }

    public List<Mission> getAllMissionsByStatut(Mission.StatutMission statut) {
        Session session = MissionManagementApplication.sessionFactory.openSession();
        Transaction transaction = null;
        List<Mission> missionList = null;

        try {
            transaction = session.beginTransaction();

            // Retrieve users by Statut
            missionList = session.createQuery("FROM Mission WHERE statut = :statut", Mission.class)
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

        return missionList;
    }


    public Mission setMissionType(Mission mission) {
        long userId= mission.getUserId();
        try {
            User user = restTemplate.getForObject("http://userManagementService/users/get/"+userId, User.class);
            if (user.getStatut() == User.Statut.BENEVOLE) {
                mission.setType(Mission.TypeMission.SPONTANEE);
                mission.setBenevoleId(userId);
                editMission(mission);
                System.out.println("La mission " + mission + " est spontanée et a été créé par " + user);
            } else if(user.getStatut() == User.Statut.BENEFICIAIRE) {
                mission.setType(Mission.TypeMission.DEMANDE);
                mission.setBeneficiareId(userId);
                editMission(mission);
                System.out.println("La mission " + mission + " est une demande d'aide et a été créé par " + user);

            }

        } catch (Exception e){
            System.out.println("L'utilisateur " + userId + " n'a pas été trouvé/ le microservice userManagment ne marche pas ");

        }

        return mission;
    }
    public Mission verifyMission(Mission mission, long userId) {
        try {
            User user = restTemplate.getForObject("http://userManagementService/users/get/"+userId, User.class);
            if (user.getStatut() == User.Statut.ADMIN) {
                mission.setStatut(Mission.StatutMission.VALIDEE);
                editMission(mission);
                System.out.println("La mission " + mission + " a été validée");
            } else {
                System.out.println("L'utilisateur " + userId + " n'est pas administrateur");

            }
        } catch (Exception e){
            System.out.println("L'utilisateur " + userId + " n'a pas été trouvé/ le microservice userManagment ne marche pas ");

        }

        return mission;
    }

    public Mission refuteMission(Mission mission, long userId, String motif) {
        try {
            User user = restTemplate.getForObject("http://userManagementService/users/get/"+userId, User.class);
            if (user.getStatut() == User.Statut.ADMIN) {
                mission.setStatut(Mission.StatutMission.NONVALIDEE);
                mission.setMotif(motif);
                editMission(mission);
                System.out.println("La mission " + mission + " a été validée");
            } else {
                System.out.println("L'utilisateur " + userId + " n'est pas administrateur");

            }
        } catch (Exception e){
            System.out.println("L'utilisateur " + userId + " n'a pas été trouvé/ le microservice userManagment ne marche pas ");

        }

        return mission;
    }




}
