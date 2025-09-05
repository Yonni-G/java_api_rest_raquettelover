package com.yonni.raquettelover.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "participations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Participation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(max = 50)
    private String firstName;

    @Email(message = "L'email doit être valide")
    private String email;

    @Size(min = 10, max = 10, message = "Le numéro de téléphone doit contenir exactement 10 chiffres")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    // on peut avoir un joueur inscrit ou un simple invité ajouté par l'organisateur
    // nullable = true pour permettre les invités sans compte utilisateur
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
