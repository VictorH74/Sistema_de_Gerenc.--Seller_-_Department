package com.myproject.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.demo.entities.Product;

// Repository responsavel por fazer operações com a entidade "User"
// Vai ser capaz de instanciar um obj Repository que vai ter varias operações para trabalhar com usuário
public interface ProductRepository extends JpaRepository<Product, Long> {
}
