package com.steveb.curso.springboot.jpa.springboot_jpa_relationships.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Long total;

    @ManyToOne
    @JoinColumn(name = "id_cliente_temp")
    private Client cliente;

    public Invoice(String description, Long total) {
        this.description = description;
        this.total = total;
    }

    public Invoice() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
    
        public Client getCliente() {
            return cliente;
        }
    
        public void setCliente(Client cliente) {
            this.cliente = cliente;
        }
    

    @Override
    public String toString() {
        return "{id=" + id + ", description=" + description + ", total=" + total + ", cliente=" + cliente + "}";
    }

}
