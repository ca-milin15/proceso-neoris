package com.co.neoristransaccional.pruebaneoris.cuenta.domain;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaServiceRepository extends JpaRepository<Cuenta, BigInteger> {

}
