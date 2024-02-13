package com.bridgelabz.bookstoreapp.service;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstoreapp.dto.ApplicationDTO;
import com.bridgelabz.bookstoreapp.dto.CustomerDTO;
import com.bridgelabz.bookstoreapp.exception.BookStoreException;
import com.bridgelabz.bookstoreapp.model.ApplicationModel;
import com.bridgelabz.bookstoreapp.model.BookModel;
import com.bridgelabz.bookstoreapp.model.CustomerModel;
import com.bridgelabz.bookstoreapp.model.UserModel;
import com.bridgelabz.bookstoreapp.repository.CustomerRepository;
import com.bridgelabz.bookstoreapp.util.ImageUtil;

@Service
public class CustomerService implements ICustomerService{

	@Autowired
	CustomerRepository customerRepository;
	
	// Create Customer
	@Override
	public int createCustomer(CustomerDTO customerDTO) {
		CustomerModel customerModel = new CustomerModel(customerDTO);
		customerRepository.save(customerModel);
		return customerModel.getCustomerId();
	}
	
	// Get all User Details list
	@Override
	public List<CustomerModel> getAllCustomersData() {
		List<CustomerModel> customerModel = customerRepository.findAll();
		if (customerModel.isEmpty()) {
			throw new BookStoreException("No Customer Registered yet!!!!");
		} else
			return customerModel;
	}

	// Get the user data by id
	@Override
	public CustomerModel getCustomerDataById(int id) {
		CustomerModel customerModel = customerRepository.findById(id).orElse(null);
	    if (customerModel != null) {
			return customerModel;
		} else
			throw new BookStoreException("CustomerID: " + id + ", does not exist");
	}
	
	// Get customer Data by Name
	@Override
	public List<CustomerModel> searchbyName(String name) {
		List<CustomerModel> customerModel = customerRepository.findAll();
		if (customerModel != null) {
			return customerRepository.findByName(name);
		} else
			throw new BookStoreException("Name: " + name + " is not available");
	}
}
