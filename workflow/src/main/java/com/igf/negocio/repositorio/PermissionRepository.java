package com.igf.negocio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igf.modelo.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long>{

}
