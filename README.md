La branche master contient les microservices et la branche main les configs.
Le UserManagementService est par défaut lancé sur le port 8081,
Le MissionManagementService est par défaut lancé sur le port 8082 et
Le ReviewMangementService est par défaut sur le port 8083
Afin d'assurer le fonctionnement des connexions entre les microservices merci de lancer eureka sur le port 8771.

Voici la liste des enpoint créé: 

UserManagementService
  - localhost/8081/users/addBenevole
  - localhost/8081/users/addAdmin
  - localhost/8081/users/addBeneficiaire
  - localhost/8081/users/delete/{userId}
  - localhost/8081/users/edit
  - localhost/8081/users/get/{userId}
  - localhost/8081/users/getAll
  - localhost/8081/users/getAllBenevole
  - localhost/8081/users/getAllAdmin
  - localhost/8081/users/getAllBeneficiaire


MissionManagementService
  - localhost/8082/mission/add
  - localhost/8082/mission/delete/{missionId}
  - localhost/8082/mission/edit
  - localhost/8082/mission/get/{missionId}
  - localhost/8082/mission/getAll
  - localhost/8082/mission/getAllAttente
  - localhost/8082/mission/getAllValid
  - localhost/8082/mission/getAllNotValid
  - localhost/8082/mission/getAllRealise
  - localhost/8082/mission/validate
  - localhost/8082/mission/refute

ReviewManagementService
  - localhost/8083/review/add
  - localhost/8083/review/delete/{reviewId}
  - localhost/8083/review/edit
  - localhost/8083/review/get/{reviewId}
  - localhost/8083/review/getAll













