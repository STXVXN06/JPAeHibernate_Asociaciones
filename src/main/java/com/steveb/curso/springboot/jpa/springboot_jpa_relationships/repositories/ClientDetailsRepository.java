package com.steveb.curso.springboot.jpa.springboot_jpa_relationships.repositories;

import org.springframework.data.repository.CrudRepository;

import com.steveb.curso.springboot.jpa.springboot_jpa_relationships.entities.ClientDetails;

public interface ClientDetailsRepository extends CrudRepository<ClientDetails, Long> {

}
