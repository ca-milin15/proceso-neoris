package com.co.neoristransaccional.pruebaneoris.cliente.domain;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteServiceRepository extends JpaRepository<Cliente, BigInteger> {

}
