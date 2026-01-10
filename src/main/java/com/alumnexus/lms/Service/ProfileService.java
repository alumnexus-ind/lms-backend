package com.alumnexus.lms.Service;

import com.alumnexus.lms.DTO.ProfileDTO;
import com.alumnexus.lms.Entity.ProfileEntity;
import com.alumnexus.lms.Entity.Role;
import com.alumnexus.lms.Repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileDTO registerTeacher(ProfileDTO profileDTO) {
        ProfileEntity newProfile = toEntity(profileDTO);
        newProfile.setRole(Role.TEACHER);
        newProfile = profileRepository.save(newProfile);
        return toDto(newProfile);
    }


    public ProfileDTO registerStudent(ProfileDTO profileDTO) {
        ProfileEntity newProfile = toEntity(profileDTO);
        newProfile.setRole(Role.USER);
        newProfile = profileRepository.save(newProfile);
        return toDto(newProfile);
    }

    // helper methods -->
    private ProfileEntity toEntity(ProfileDTO profileDTO) {
        return ProfileEntity.builder()
                .id(profileDTO.getId())
                .firstName(profileDTO.getFirstName())
                .lastName(profileDTO.getLastName())
                .email(profileDTO.getEmail())
                .password(profileDTO.getPassword())
                .role(profileDTO.getRole())
                .createdAt(profileDTO.getCreatedAt())
                .updatedAt(profileDTO.getUpdatedAt())
                .build();
    }

    private ProfileDTO toDto(ProfileEntity newProfile) {
        return ProfileDTO.builder()
                .id(newProfile.getId())
                .firstName(newProfile.getFirstName())
                .lastName(newProfile.getLastName())
                .email(newProfile.getEmail())
                .password(newProfile.getPassword())
                .role(newProfile.getRole())
                .createdAt(newProfile.getCreatedAt())
                .updatedAt(newProfile.getUpdatedAt())
                .build();
    }
}
