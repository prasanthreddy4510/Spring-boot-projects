package com.ashokit.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.exceptions.PhoneBookExceptionHandler;
import com.ashokit.models.Contact;
import com.ashokit.repository.ContactDtlsRepository;
import com.ashokit.repository.ContactEntity;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactDtlsRepository contactRepo;
	
	@Override
	public boolean save(Contact contact) {
		boolean isSaved=false;
		try {
		
		ContactEntity entity=new ContactEntity();
		BeanUtils.copyProperties(contact, entity);
		ContactEntity contactEntity = contactRepo.save(entity);
		if(contactEntity.getContactId()!=null)
			return isSaved;
		}catch(Exception e) {
			throw new PhoneBookExceptionHandler("failed to save the contact");
		}
		return isSaved;
	}

	@Override
	public List<Contact> getAllContacts() {
		try {
          List<ContactEntity> entities = contactRepo.findAll();
          return entities.stream().map(entity->{
        	  Contact contact=new Contact();
        	  BeanUtils.copyProperties(entity, contact);
        	  return contact;
          }).collect(Collectors.toList());
		}catch(Exception e) {
			throw new PhoneBookExceptionHandler("failed to get contacts");
		}
		
	}

	@Override
	public Contact getContactById(Integer contactId) {
		try {
		Optional<ContactEntity> entity = contactRepo.findById(contactId);
		if(entity.isPresent()) {
			ContactEntity contactEntity = entity.get();
			Contact c=new Contact();
			BeanUtils.copyProperties(contactEntity, c);
			return c;
		}
		}catch(Exception e) {
			throw new PhoneBookExceptionHandler("failed to get specific contact");
		}
		
		return null;
	}

	
	@Override
	public boolean deleteContact(Integer contactId) {
		try {
		if(contactId!=null) {
		contactRepo.deleteById(contactId);
		return true;
		}
		}catch(Exception e) {
			throw new PhoneBookExceptionHandler("failed to delete the contact");
		}
		return false;
	}

}
