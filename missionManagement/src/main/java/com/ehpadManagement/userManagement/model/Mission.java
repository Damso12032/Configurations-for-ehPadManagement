package com.ehpadManagement.userManagement.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

@Entity
public class Mission {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String titre;
    private String description;

    private long userId;

    private long benevoleId;

    private long beneficiareId;

    private long adminId;

    private String motif;


    public Mission() {

    }
    public Mission(String titre,String description,int userId){
        this.titre = titre;
        this.description = description;
        this.statut= StatutMission.ENATTENTE;
        this.userId = userId;
    }

    public enum StatutMission{
        ENATTENTE,
        VALIDEE,
        REALISEE,
        NONVALIDEE
    };

    public enum TypeMission{
        DEMANDE,
        SPONTANEE
    };
    StatutMission statut;

    TypeMission type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBenevoleId() {
        return benevoleId;
    }

    public void setBenevoleId(long benevoleId) {
        this.benevoleId = benevoleId;
    }

    public long getBeneficiareId() {
        return beneficiareId;
    }

    public void setBeneficiareId(long beneficiareId) {
        this.beneficiareId = beneficiareId;
    }

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public StatutMission getStatut() {
        return statut;
    }

    public void setStatut(StatutMission statut) {
        this.statut = statut;
    }

    public TypeMission getType() {
        return type;
    }

    public void setType(TypeMission type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Mission{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", userId=" + userId +
                ", benevoleId=" + benevoleId +
                ", beneficiareId=" + beneficiareId +
                ", adminId=" + adminId +
                ", motif='" + motif + '\'' +
                ", statut=" + statut +
                ", type=" + type +
                '}';
    }
}