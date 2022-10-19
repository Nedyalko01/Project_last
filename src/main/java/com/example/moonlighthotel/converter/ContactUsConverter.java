package com.example.moonlighthotel.converter;

import com.example.moonlighthotel.dto.ContactUsRequest;
import com.example.moonlighthotel.model.ContactUs;

public class ContactUsConverter {
    public static ContactUs convertToContactUs(ContactUsRequest request) {

        ContactUs contact = new ContactUs();

        contact.setName(request.getName());
        contact.setEmail(request.getEmail());
        contact.setPhone(request.getPhone());
        contact.setMessage(request.getMessage());

        return contact;
    }
}


