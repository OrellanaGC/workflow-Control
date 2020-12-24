package com.igf.negocio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igf.modelo.User;


public interface UserRepository extends JpaRepository<User, String>{

}
