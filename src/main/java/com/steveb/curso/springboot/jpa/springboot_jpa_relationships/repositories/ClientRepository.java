package com.steveb.curso.springboot.jpa.springboot_jpa_relationships.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.steveb.curso.springboot.jpa.springboot_jpa_relationships.entities.Client;

public interface ClientRepository extends CrudRepository<Client,Long> {

    @Query("select c from Client c left join fetch c.invoices where c.id = ?1")
    Optional<Client> findOneWithInvoices(Long id);
   
    @Query("select c from Client c left join fetch c.invoices left join fetch c.addresses where c.id = ?1")
    Optional<Client> findOne(Long id);

}
