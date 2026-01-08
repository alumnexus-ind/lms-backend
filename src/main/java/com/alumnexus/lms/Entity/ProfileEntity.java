package com.alumnexus.lms.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name= "tbl_profiles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Database will be assigning it automatically
    private Long id;
    // First Name
    @Column(nullable = false)
    private String firstName;
    // Last Name
    @Column(nullable = false)
    private String lastName;
    // Email
    @Column(nullable = false, unique = true)
    private String email;
    // Password -> will be encoded later
    private String password;
    // The role that this person has(should not be empty)
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private Role role;
    // Created at -> cannot be updated by any person
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
//    // Mapping the course Assignment
//    @OneToMany(
//            mappedBy = "user",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true,
//            fetch = FetchType.LAZY
//    )
////     Is this the correct way?
//    private List<CourseAssignment> courseAssignments;
//    private AccountStatus accountStatus;
}
