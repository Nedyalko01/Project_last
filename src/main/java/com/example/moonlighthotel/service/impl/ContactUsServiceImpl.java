package com.example.moonlighthotel.service.impl;

import com.example.moonlighthotel.model.ContactUs;
import com.example.moonlighthotel.repositories.ContactUsRepository;
import com.example.moonlighthotel.service.ContactUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactUsServiceImpl implements ContactUsService {

    private final ContactUsRepository contactUsRepository;

    @Autowired
    public ContactUsServiceImpl(ContactUsRepository contactUsRepository) {
        this.contactUsRepository = contactUsRepository;
    }

    @Override
    public void save(ContactUs contactUs) {

    }
}
