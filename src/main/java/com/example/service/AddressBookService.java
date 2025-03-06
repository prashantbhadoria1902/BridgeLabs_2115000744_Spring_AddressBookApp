package com.example.service;

import com.example.dto.AddressBookDTO;
import com.example.model.AddressBook;
import com.example.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressBookService {

    @Autowired
    private AddressBookRepository repository;

    public AddressBook addContact(AddressBookDTO dto) {
        AddressBook contact = new AddressBook(null, dto.getName(), dto.getEmail(), dto.getPhone());
        return repository.save(contact);
    }

    public List<AddressBookDTO> getAllContacts() {
        return repository.findAll().stream()
                .map(contact -> new AddressBookDTO(contact.getName(), contact.getEmail(), contact.getPhone()))
                .collect(Collectors.toList());
    }
}
