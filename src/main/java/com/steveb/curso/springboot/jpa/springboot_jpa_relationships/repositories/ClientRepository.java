package com.steveb.curso.springboot.jpa.springboot_jpa_relationships.repositories;

import org.springframework.data.repository.CrudRepository;

import com.steveb.curso.springboot.jpa.springboot_jpa_relationships.entities.Client;

public interface ClientRepository extends CrudRepository<Client,Long> {

}
