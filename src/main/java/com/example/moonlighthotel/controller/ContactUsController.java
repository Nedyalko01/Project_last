package com.example.moonlighthotel.controller;

import com.example.moonlighthotel.converter.ContactUsConverter;
import com.example.moonlighthotel.dto.ContactUsRequest;
import com.example.moonlighthotel.model.ContactUs;
import com.example.moonlighthotel.service.ContactUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/contacts", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContactUsController {

    private final ContactUsService contactUsService;

    @Autowired
    public ContactUsController(ContactUsService contactUsService) {
        this.contactUsService = contactUsService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> sendNewMessage(@RequestBody ContactUsRequest request) {

        ContactUs contact = ContactUsConverter.convertToContactUs(request);
        contactUsService.save(contact);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

