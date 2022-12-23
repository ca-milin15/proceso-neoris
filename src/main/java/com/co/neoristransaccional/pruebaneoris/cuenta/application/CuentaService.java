package com.co.neoristransaccional.pruebaneoris.cuenta.application;

import java.math.BigInteger;

import com.co.neoristransaccional.pruebaneoris.cuenta.domain.Cuenta;

public interface CuentaService {

	CuentaResponse crear (CuentaRequest cuentaRequest);
	CuentaResponse actualizar (BigInteger id, CuentaRequest cuentaRequest);
	Boolean eliminar (BigInteger id);
	Cuenta buscarPorId (BigInteger id);
	Cuenta guardarCuenta (Cuenta cuenta);
}
