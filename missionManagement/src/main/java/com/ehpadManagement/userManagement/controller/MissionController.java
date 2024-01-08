package com.ehpadManagement.userManagement.controller;

import com.ehpadManagement.userManagement.model.Mission;
import com.ehpadManagement.userManagement.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mission")
public class MissionController {

    private final MissionService missionService;

    @Autowired
    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    @PostMapping("/add")
    public String addMission(@RequestBody Mission mission) {
        missionService.addMission(mission);
        try{
            missionService.setMissionType(mission);
        } catch (Exception e){
            System.out.println("Le microservice userManagment ne marche pas ");
        }
        return "Benevole ajouté dans la bdd";
    }

    @DeleteMapping("/delete/{missionId}")
    public String deleteMission(@PathVariable int missionId) {
        missionService.deleteMission(missionId);
        return "Utilisateur avec l'ID " + missionId + " supprimé de la bdd";
    }

    @PutMapping("/edit")
    public String editMission(@RequestBody Mission updatedMission) {
        missionService.editMission(updatedMission);
        return "Utilisateur modifié dans la bdd";
    }

    @GetMapping("/get/{missionId}")
    public Mission getMissionById(@PathVariable int missionId) {
        Mission mission = missionService.getMissionById(missionId);
        if (mission != null) {
            System.out.println("Mission trouvé : " + mission);
        } else {
            System.out.println("Mission avec l'ID " + missionId + " non trouvé");
        }
        return mission;
    }

    @GetMapping("/getAll")
    public List<Mission> getAllMissions() {
        List<Mission> missionList = missionService.getAllMissions();
        if (missionList != null && !missionList.isEmpty()) {
            System.out.println("Liste des missions : " + missionList);
        } else {
            System.out.println("Aucune mission trouvé dans la bdd");
        }
        return missionList;
    }

    @GetMapping("/getAllAttente")
    public List<Mission> getAllMissionsEnAttente() {
        List<Mission> benevoleList = missionService.getAllMissionsByStatut(Mission.StatutMission.ENATTENTE);
        if (benevoleList != null && !benevoleList.isEmpty()) {
            System.out.println("Liste des utilisateurs BENEVOLE : " + benevoleList);
        } else {
            System.out.println("Aucun utilisateur BENEVOLE trouvé dans la bdd");
        }
        return benevoleList;
    }

    @GetMapping("/getAllValid")
    public List<Mission> getAllMissionsValidee() {
        List<Mission> adminList = missionService.getAllMissionsByStatut(Mission.StatutMission.VALIDEE);
        if (adminList != null && !adminList.isEmpty()) {
            System.out.println("Liste des utilisateurs ADMIN : " + adminList);
        } else {
            System.out.println("Aucun utilisateur ADMIN trouvé dans la bdd");
        }
        return adminList;
    }

    @GetMapping("/getAllNotValid")
    public List<Mission> getAllMissionsNonValidee() {
        List<Mission> adminList = missionService.getAllMissionsByStatut(Mission.StatutMission.NONVALIDEE);
        if (adminList != null && !adminList.isEmpty()) {
            System.out.println("Liste des utilisateurs ADMIN : " + adminList);
        } else {
            System.out.println("Aucun utilisateur ADMIN trouvé dans la bdd");
        }
        return adminList;
    }

    @GetMapping("/getAllRealise")
    public List<Mission> getAllMissionsRealisee() {
        List<Mission> adminList = missionService.getAllMissionsByStatut(Mission.StatutMission.REALISEE);
        if (adminList != null && !adminList.isEmpty()) {
            System.out.println("Liste des utilisateurs ADMIN : " + adminList);
        } else {
            System.out.println("Aucun utilisateur ADMIN trouvé dans la bdd");
        }
        return adminList;
    }

    @PostMapping("/validate")
    public String verifyMission(@RequestParam long missionId, @RequestParam long userId){
        try{
            Mission mission = missionService.getMissionById(missionId);
            missionService.verifyMission(mission,userId);
            return "Mission" + mission+ "validé";

        } catch (Exception e){
            System.out.println("Mission non trouvé");
        }
        return "Mission non trouvé";
    }

    @PostMapping("/refute")
    public String refuteMission(@RequestParam long missionId, @RequestParam long userId, @RequestParam String motif){
        try{
            Mission mission = missionService.getMissionById(missionId);
            missionService.refuteMission(mission,userId,motif);
            return "Mission" + mission+ "refusé";

        } catch (Exception e){
            System.out.println("Mission non trouvé");
        }
        return "Mission non trouvé";    }

}