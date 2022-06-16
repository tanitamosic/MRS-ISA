package com.Projekat.controller;


import com.Projekat.dto.ComplaintAnswerDTO;
import com.Projekat.mail.MyMailSender;
import com.Projekat.model.reservations.submitions.Complaint;
import com.Projekat.service.AccountService;
import com.Projekat.service.ComplaintService;
import com.Projekat.service.ReservationService;
import com.Projekat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;
    @Autowired
    private MyMailSender mailSender;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private UserService userService;

    @GetMapping(value="/admin/get-complaints")
    public ResponseEntity<List<Complaint>> getDeletionRequests() {
        try {
            List<Complaint> complaints = complaintService.getOpenComplaints();
            return new ResponseEntity<>(complaints, HttpStatus.OK);
        } catch (DataAccessException selectException) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value="/admin/answer-complaint")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> answerComplaint(@RequestBody ComplaintAnswerDTO answers) {
        Integer client_id = answers.getTargetClient();
        Integer employee_id = answers.getTargetEmployee();
        String client_username = userService.findUsernameById(client_id);
        String employee_username = userService.findUsernameById(employee_id);
        String answer = "Odgovor klijentu glasi: " + answers.getAnswerToClient() +"\r\nOdgovor zaposlenom glasi" +
                answers.getAnswerToEmployee();
        complaintService.answerComplaint(answers.getComplaintId(), answer);
        mailSender.sendComplaintAnswer(client_username, answers.getAnswerToClient());
        mailSender.sendComplaintAnswer(employee_username, answers.getAnswerToEmployee());

        return new ResponseEntity<>("Odgovori uspešno poslati", HttpStatus.OK);
    }

}
