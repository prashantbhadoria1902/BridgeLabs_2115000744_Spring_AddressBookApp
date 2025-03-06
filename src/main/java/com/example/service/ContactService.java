package com.example.service;

import com.example.model.Contact;
import com.example.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    // Get all contacts
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    // Get contact by ID
    public Optional<Contact> getContactById(Long id) {
        return contactRepository.findById(id);
    }

    // Add a new contact
    public Contact addContact(Contact contact) {
        return contactRepository.save(contact);
    }

    // Delete contact by ID
    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}
