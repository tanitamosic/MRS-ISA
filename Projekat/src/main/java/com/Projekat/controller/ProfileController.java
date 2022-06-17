package com.Projekat.controller;


import com.Projekat.dto.SimpleUserDTO;
import com.Projekat.model.Account;
import com.Projekat.model.AccountDeletionRequest;
import com.Projekat.model.users.User;
import com.Projekat.service.AccountService;
import com.Projekat.dto.ProfileDetailsDTO;
import com.Projekat.service.AddressService;
import com.Projekat.service.DeletionService;
import com.Projekat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/profile")
public class ProfileController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private DeletionService deletionService;

    @PostMapping(consumes = "application/json", value = "update-profile")
    public ResponseEntity<String> updateProfile(@RequestBody ProfileDetailsDTO profile) {
        System.out.println(profile.getId());
        final Integer acc_id = userService.findUserAccountId(profile.getId());
        // UPDATE USERNAME
        if (profile.DidTryPasswordUpdate()) {
            accountService.updatePassword(acc_id, profile.getNewPassword());
        }
        // UPDATE PASSWORD
        if (profile.DidTryUsernameUpdate()) {
            accountService.updateUsername(acc_id, profile.getNewUsername());
        }
        // UPDATE USER DATA
        userService.updateUserProfile(profile.getId(), profile.getName(), profile.getSurname(), profile.getPhone());

        // UPDATE USER ADDRESS

        addressService.upsertAddress(profile.getState(), profile.getCity(), profile.getStreet());
        Integer address_id = addressService.getAddressId(profile.getState(), profile.getCity(), profile.getStreet());
        userService.updateUserAddress(profile.getId(), address_id);


        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PostMapping(value="make-delete-profile-request")
    public ResponseEntity<String> postDeletionRequest(@RequestBody SimpleUserDTO simple_dto) {
        //final Integer acc_id = userService.findUserAccountId(simple_dto.getId());
        User u = userService.findUserById(simple_dto.getId());
        Account acc = accountService.findById(u.getId());
        AccountDeletionRequest adr = new AccountDeletionRequest(acc, u);
        deletionService.makeDelRequest(adr);

        return new ResponseEntity<>("Uspešno ste poslali zahtev za brisanje naloga.", HttpStatus.OK);
    }
}
