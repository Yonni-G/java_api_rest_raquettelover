package com.yonni.raquettelover.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users_places")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPlace {

    @EmbeddedId
    private PlaceUserId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId") // Correspond à PlaceUserId.userId
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("placeId") // Correspond à PlaceUserId.placeId
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;

    public UserPlace(User user, Place place) {
        this.user = user;
        this.place = place;
        this.id = new PlaceUserId(user.getId(), place.getId());
    }

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
