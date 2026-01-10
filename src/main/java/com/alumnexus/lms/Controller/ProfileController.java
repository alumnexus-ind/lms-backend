package com.alumnexus.lms.Controller;

import com.alumnexus.lms.DTO.ProfileDTO;
import com.alumnexus.lms.Service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @PostMapping("/register/teacher")
    public ResponseEntity<ProfileDTO> registerTeacherProfile(@RequestBody ProfileDTO profileDTO) {
        ProfileDTO registeredProfile = profileService.registerTeacher(profileDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredProfile);
    }

    @PostMapping("/register/student")
    public ResponseEntity<ProfileDTO> registerStudentProfile(@RequestBody ProfileDTO profileDTO) {
        ProfileDTO registeredProfile = profileService.registerStudent(profileDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredProfile);
    }
}
