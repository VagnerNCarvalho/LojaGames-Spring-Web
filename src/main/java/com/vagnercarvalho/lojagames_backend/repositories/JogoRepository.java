package com.vagnercarvalho.lojagames_backend.repositories;

import com.vagnercarvalho.lojagames_backend.entities.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogoRepository extends JpaRepository<Jogo,Long> {
}
