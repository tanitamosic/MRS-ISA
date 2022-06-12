package com.Projekat.listeners;

import com.Projekat.events.OnEmployeeRegistrationEvent;
import com.Projekat.model.Account;
import com.Projekat.model.EmployeeRegistrationRequest;
import com.Projekat.service.AccountService;
import com.Projekat.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class EmployeeRegistrationListener implements ApplicationListener<OnEmployeeRegistrationEvent> {

    @Autowired
    private AccountService accountService;
    @Autowired
    private EmployeeService employeeService;

    @Override
    public void onApplicationEvent(OnEmployeeRegistrationEvent event) {
        this.requestEmployeeRegistration(event);
    }

    private void requestEmployeeRegistration(OnEmployeeRegistrationEvent event) {
        Account acc = event.getAccount();
        String request = event.getRegistrationRequest();
        String username = event.getUsername();
        EmployeeRegistrationRequest newRequest = new EmployeeRegistrationRequest(acc, request, username);

        employeeService.save(newRequest);
    }
}
