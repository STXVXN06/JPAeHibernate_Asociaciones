package com.steveb.curso.springboot.jpa.springboot_jpa_relationships;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.steveb.curso.springboot.jpa.springboot_jpa_relationships.entities.Address;
import com.steveb.curso.springboot.jpa.springboot_jpa_relationships.entities.Client;
import com.steveb.curso.springboot.jpa.springboot_jpa_relationships.entities.ClientDetails;
import com.steveb.curso.springboot.jpa.springboot_jpa_relationships.entities.Invoice;
import com.steveb.curso.springboot.jpa.springboot_jpa_relationships.repositories.ClientDetailsRepository;
import com.steveb.curso.springboot.jpa.springboot_jpa_relationships.repositories.ClientRepository;
import com.steveb.curso.springboot.jpa.springboot_jpa_relationships.repositories.InvoiceRepository;

@SpringBootApplication
public class SpringbootJpaRelationshipsApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private ClientDetailsRepository clientDetailsRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// manyToOne();
		// manyToOneFindById();
		// // oneToMany();
		// oneToManyFindById();
		// exampleDelete();
		// removeAddress();
		// oneToManyInvoiceBidireccional();
		// removeInvoiceBidireccionalById();
		// oneToOne();
		// oneToOneFindById();
		// oneToOneBidireccional();
		oneToOneBidireccionalFindById();
	}

	@Transactional
	public void oneToOneBidireccionalFindById() {
		Client client2 = new Client("Sara", "Lopez");
		clientRepository.save(client2);
		Optional<Client> clientOptional = clientRepository.findOne(2L);
		clientOptional.ifPresent(client -> {

			ClientDetails clientDetails = new ClientDetails(false, 100);

			client.setClientDetails(clientDetails);

			clientRepository.save(client);

			System.out.println(client);
		});

	}

	@Transactional
	public void oneToOneBidireccional() {

		Client client = new Client("Sara", "Lopez");

		ClientDetails clientDetails = new ClientDetails(true, 200);

		client.setClientDetails(clientDetails);

		clientRepository.save(client);

		System.out.println(client);

	}

	@Transactional
	public void oneToOneFindById() {
		ClientDetails clientDetails = new ClientDetails(true, 23000);
		clientDetailsRepository.save(clientDetails);

		Optional<Client> clientOptional = clientRepository.findOne(1L);
		clientOptional.ifPresent(client -> {

			client.setClientDetails(clientDetails);
			clientRepository.save(client);
		});

	}

	@Transactional
	public void oneToOne() {
		ClientDetails clientDetails = new ClientDetails(true, 5000);
		clientDetailsRepository.save(clientDetails);

		Client client = new Client("Jero", "Sierra");
		client.setClientDetails(clientDetails);
		clientRepository.save(client);

	}

	@Transactional
	public void removeInvoiceBidireccionalById() {

		Optional<Client> optionalClientBd = clientRepository.findOne(5L);
		optionalClientBd.ifPresent(client -> {
			Optional<Invoice> invoiceOptional = invoiceRepository.findById(14L);
			invoiceOptional.ifPresent(invoice -> {
				client.removeInvoice(invoice);
				clientRepository.save(client);
			});
		});

	}

	@Transactional
	public void oneToManyInvoiceBidireccional() {

		Optional<Client> optionalClient = clientRepository.findOne(1L);

		optionalClient.ifPresent(client -> {

			Invoice invoice1 = new Invoice("sillas", 1200L);
			Invoice invoice2 = new Invoice("carrosss", 22000L);

			client.addInvoice(invoice2)
					.addInvoice(invoice1);

			clientRepository.save(client);
			System.out.println(client);
		});

	}

	@Transactional
	public void removeAddress() {
		Optional<Client> optionalClient = clientRepository.findById(1L);
		if (optionalClient.isPresent()) {
			Client client = optionalClient.get();
			client.getAddresses().remove(0);
			clientRepository.save(client);
			System.out.println(client);
		}

	}

	@Transactional
	public void oneToManyFindById() {
		Optional<Client> optionalClient = clientRepository.findById(1L);
		if (optionalClient.isPresent()) {
			Client client = optionalClient.get();
			Address address1 = new Address("Velez", 1243);
			Address address2 = new Address("Fundadodres", 4321);
			Set<Address> addresses = new HashSet<>();
			addresses.add(address1);
			addresses.add(address2);
			client.setAddresses(addresses);

			clientRepository.save(client);
			System.out.println(client);

		}
	}

	@Transactional
	public void exampleDelete() {
		Optional<Client> optionalClient = clientRepository.findById(4L);
		if (optionalClient.isPresent()) {
			Client client = optionalClient.get();
			clientRepository.delete(client);
		}
	}

	@Transactional
	public void oneToMany() {

		Client client = new Client("Frank", "Moras");
		Address address1 = new Address("Velez", 1243);
		Address address2 = new Address("Fundadodres", 4321);
		client.getAddresses().add(address1);
		client.getAddresses().add(address2);
		clientRepository.save(client);
		System.out.println(client);

	}

	@Transactional
	public void manyToOne() {

		Client client = new Client("Steven", "Parra");
		clientRepository.save(client);

		Invoice invoice = new Invoice("Compra de comida", 15000L);
		invoice.setCliente(client);
		Invoice invoiceDb = invoiceRepository.save(invoice);
		System.out.println(invoiceDb);
	}

	@Transactional
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
