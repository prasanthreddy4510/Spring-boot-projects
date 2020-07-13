package com.ashokit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ashokit.models.Contact;
import com.ashokit.service.ContactService;

@Controller
public class ViewController {
	
	@Autowired
	private ContactService contactService;

	@GetMapping("/editContact")
	public String editContact(@RequestParam("cid")Integer contactId, Model model) {
		Contact c = contactService.getContactById(contactId);
	    model.addAttribute("contact",c);
		return "contactInfo";
	}
	
	
	@GetMapping("/deleteContact")
	public String deleteContact(@RequestParam("cid")Integer contactId,Model model) {
		boolean isDeleted = contactService.deleteContact(contactId);
		if(isDeleted) {
		model.addAttribute("msg", "message deleted succesfully");
		}
		return "redirect:viewContacts1";
	}
}
