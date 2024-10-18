package com.certidevs;

import com.certidevs.model.Customer;
import com.certidevs.repository.CustomerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {

		var context = SpringApplication.run(Main.class, args);

		var customerRepository = context.getBean(CustomerRepository.class);
		if (customerRepository.count() == 0) {
			var customer1 = Customer.builder()
					.nombre("Juan").apellido("García").email("juan@game.com").edad(43).build();
			var customer2 = Customer.builder()
					.nombre("María").apellido("Rodríguez").email("maria@game.com").edad(25).build();

			customerRepository.saveAll(List.of(customer1, customer2));
		}
	}

}
