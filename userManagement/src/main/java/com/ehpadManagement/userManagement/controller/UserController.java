package com.ehpadManagement.userManagement.controller;

import com.ehpadManagement.userManagement.model.User;
import com.ehpadManagement.userManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addBenevole")
    public String addBenevole(@RequestBody User user) {
        user.setStatut(User.Statut.BENEVOLE);
        userService.addUser(user);
        return "Benevole ajouté dans la bdd";
    }
    @PostMapping("/addAdmin")
    public String addAdmin(@RequestBody User user) {
        user.setStatut(User.Statut.ADMIN);
        userService.addUser(user);
        return "Admin ajouté dans la bdd";
    }
    @PostMapping("/addBeneficiaire")
    public String addBeneficiaire(@RequestBody User user) {
        user.setStatut(User.Statut.BENEFICIAIRE);
        userService.addUser(user);
        return "Beneficiaire ajouté dans la bdd";
    }

    @DeleteMapping("/delete/{userId}")
    public String deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
        return "Utilisateur avec l'ID " + userId + " supprimé de la bdd";
    }

    @PutMapping("/edit")
    public String editUser(@RequestBody User updatedUser) {
        userService.editUser(updatedUser);
        return "Utilisateur modifié dans la bdd";
    }

    @GetMapping("/get/{userId}")
    public User getUserById(@PathVariable int userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            System.out.println("Utilisateur trouvé : " + user);
        } else {
            System.out.println("Utilisateur avec l'ID " + userId + " non trouvé");
        }
        return user;
    }

    @GetMapping("/getAll")
    public List<User> getAllUsers() {
        List<User> userList = userService.getAllUsers();
        if (userList != null && !userList.isEmpty()) {
            System.out.println("Liste des utilisateurs : " + userList);
        } else {
            System.out.println("Aucun utilisateur trouvé dans la bdd");
        }
        return userList;
    }

    @GetMapping("/getAllBenevole")
    public List<User> getAllBenevoleUsers() {
        List<User> benevoleList = userService.getAllUsersByStatut(User.Statut.BENEVOLE);
        if (benevoleList != null && !benevoleList.isEmpty()) {
            System.out.println("Liste des utilisateurs BENEVOLE : " + benevoleList);
        } else {
            System.out.println("Aucun utilisateur BENEVOLE trouvé dans la bdd");
        }
        return benevoleList;
    }

    @GetMapping("/getAllAdmin")
    public List<User> getAllAdminUsers() {
        List<User> adminList = userService.getAllUsersByStatut(User.Statut.ADMIN);
        if (adminList != null && !adminList.isEmpty()) {
            System.out.println("Liste des utilisateurs ADMIN : " + adminList);
        } else {
            System.out.println("Aucun utilisateur ADMIN trouvé dans la bdd");
        }
        return adminList;
    }

    @GetMapping("/getAllBeneficiaire")
    public List<User> getAllBeneficiaireUsers() {
        List<User> beneficiaireList = userService.getAllUsersByStatut(User.Statut.BENEFICIAIRE);
        if (beneficiaireList != null && !beneficiaireList.isEmpty()) {
            System.out.println("Liste des utilisateurs BENEFICIAIRE : " + beneficiaireList);
        } else {
            System.out.println("Aucun utilisateur BENEFICIAIRE trouvé dans la bdd");
        }
        return beneficiaireList;
    }



}