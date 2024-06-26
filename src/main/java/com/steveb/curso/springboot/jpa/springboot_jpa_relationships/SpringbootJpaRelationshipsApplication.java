package com.steveb.curso.springboot.jpa.springboot_jpa_relationships;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.steveb.curso.springboot.jpa.springboot_jpa_relationships.entities.Client;
import com.steveb.curso.springboot.jpa.springboot_jpa_relationships.entities.Invoice;
import com.steveb.curso.springboot.jpa.springboot_jpa_relationships.repositories.ClientRepository;
import com.steveb.curso.springboot.jpa.springboot_jpa_relationships.repositories.InvoiceRepository;

@SpringBootApplication
public class SpringbootJpaRelationshipsApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		manyToOneFindById();
	}

	public void manyToOne() {

		Client client = new Client("Steven", "Parra");
		clientRepository.save(client);

		Invoice invoice = new Invoice("Compra de comida", 15000L);
		invoice.setCliente(client);
		Invoice invoiceDb = invoiceRepository.save(invoice);
		System.out.println(invoiceDb);
	}

	public void manyToOneFindById() {

		Optional<Client> optionalClient = clientRepository.findById(1L);
		if (optionalClient.isPresent()) {
			Client client = optionalClient.orElseThrow();
			Invoice invoice = new Invoice("Compra de arepas", 20000L);
			invoice.setCliente(client);
			Invoice invoiceDb = invoiceRepository.save(invoice);
			System.out.println(invoiceDb);
		}

	}

}
