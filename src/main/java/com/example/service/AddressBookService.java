package com.example.service;

import com.example.dto.AddressBookDTO;
import com.example.model.AddressBook;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class AddressBookService {

    private final List<AddressBook> addressBookList = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public AddressBook addContact(AddressBookDTO dto) {
        AddressBook contact = new AddressBook(idCounter.getAndIncrement(), dto.getName(), dto.getEmail());
        addressBookList.add(contact);
        return contact;
    }

    public List<AddressBook> getAllContacts() {
        return addressBookList;
    }

    public Optional<AddressBook> getContactById(Long id) {
        return addressBookList.stream().filter(contact -> contact.getId().equals(id)).findFirst();
    }

    public Optional<AddressBook> updateContact(Long id, AddressBookDTO dto) {
        Optional<AddressBook> contactOptional = getContactById(id);
        contactOptional.ifPresent(contact -> {
            contact.setName(dto.getName());
            contact.setEmail(dto.getEmail());
        });
        return contactOptional;
    }

    public boolean deleteContact(Long id) {
        return addressBookList.removeIf(contact -> contact.getId().equals(id));
    }
}
