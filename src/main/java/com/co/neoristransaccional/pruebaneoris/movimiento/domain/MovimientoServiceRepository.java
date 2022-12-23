package com.co.neoristransaccional.pruebaneoris.movimiento.domain;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoServiceRepository extends JpaRepository<Movimiento, BigInteger>{

	List<Movimiento> findByClienteIdAndFechaBetween(BigInteger idCliente, LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
