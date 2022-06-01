package com.Projekat.controller;


import com.Projekat.model.Account;
import com.Projekat.service.AccountService;
import com.Projekat.dto.ProfileDetailsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/profile")
public class ProfileController {

    @PostMapping(consumes = "application/json", value = "update-profile")
    public ResponseEntity<String> updateProfile(ProfileDetailsDTO profile) {
        String currentPassword = ""; // TODO: Extract current password from Account

        // if 1 of the password input fields has input, user tried to change the password
        if (!profile.NewPassword1.isEmpty() || !profile.NewPassword2.isEmpty() || !profile.OldPassword.isEmpty()) {
            if (!profile.NewPassword1.equals(profile.NewPassword2) || !profile.OldPassword.equals(currentPassword)) {
                return new ResponseEntity<>("Invalid attempt at changing passwords", HttpStatus.BAD_REQUEST);
            } else {
                System.out.println("Update password");
                // TODO: Update password to NewPassword in pgadmin
            }
        }
        // TODO: Extract user ID from token, Update profile with new Details from parameter 'profile'
        return null;
    }
}
