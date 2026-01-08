package com.alumnexus.lms.Repository;

import com.alumnexus.lms.Entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {

    // select * from tbl_profile where email = ?
    Optional<ProfileEntity> findByEmail(String email);
}
