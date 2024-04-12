package com.vagnercarvalho.lojagames_backend.repositories;

import com.vagnercarvalho.lojagames_backend.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
}
