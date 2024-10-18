package com.certidevs.controller;

import com.certidevs.model.Customer;
import com.certidevs.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class CustomerController {

    private CustomerRepository customerRepository;

    //http://localhost:8080/customers
    @GetMapping("customers")
    public String findAll(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "customer-list";
    }

    //http://localhost:8080/customers/1
    @GetMapping("customers/{id}")
    public String findById(@PathVariable Long id, Model model) {
        customerRepository.findById(id).
                ifPresent(customer -> model.addAttribute("customer", customer));
        return "customer-detail";
    }

    //http://localhost:8080/customers/new
    @GetMapping("customers/new")
    public String getFormToCreate(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer-form";
    }

    //http://localhost:8080/customers/edit/3
    @GetMapping("customers/update/{id}")
    public String getFormToUpdate(@PathVariable Long id, Model model) {
        customerRepository.findById(id).
                ifPresent(customer -> model.addAttribute("customer", customer));
        return "customer-form";
    }

    @PostMapping("customers")
    public String save(@ModelAttribute Customer customer) {
        if (customer.getId() == null) {
            customerRepository.save(customer);
        } else {
            customerRepository.findById(customer.getId()).ifPresent(customerDB -> {
                BeanUtils.copyProperties(customer, customerDB);
                customerRepository.save(customerDB);
            });
        }
        return "redirect:/customers";
    }

    @GetMapping("customers/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        customerRepository.deleteById(id);
        return "redirect:/customers";
    }

    @GetMapping("customers/deleteAll")
    public String deleteAll() {
        customerRepository.deleteAll();
        return "redirect:/customers";
    }
}
