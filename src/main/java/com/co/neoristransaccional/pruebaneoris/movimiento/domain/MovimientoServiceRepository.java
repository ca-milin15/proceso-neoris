package com.co.neoristransaccional.pruebaneoris.movimiento.domain;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoServiceRepository extends JpaRepository<Movimiento, BigInteger>{

}
