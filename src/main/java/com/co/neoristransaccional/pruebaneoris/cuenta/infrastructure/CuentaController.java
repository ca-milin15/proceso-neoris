package com.co.neoristransaccional.pruebaneoris.cuenta.infrastructure;

import java.math.BigInteger;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.co.neoristransaccional.pruebaneoris.cuenta.application.CuentaRequest;
import com.co.neoristransaccional.pruebaneoris.cuenta.application.CuentaResponse;
import com.co.neoristransaccional.pruebaneoris.cuenta.application.CuentaService;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("cuentas")
public class CuentaController {
	
	CuentaService cuentaService;
	
	public CuentaController(CuentaService cuentaService) {
		super();
		this.cuentaService = cuentaService;
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.OK)
	public CuentaResponse crear(@RequestBody CuentaRequest cuentaRequest) {
		return cuentaService.crear(cuentaRequest);
	}

	@PutMapping("{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public CuentaResponse actualizar(@PathVariable BigInteger id, @RequestBody CuentaRequest cuentaRequest) {
		return cuentaService.actualizar(id, cuentaRequest);
	}

	@DeleteMapping("{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public Boolean eliminar(@PathVariable BigInteger id) {
		return cuentaService.eliminar(id);
	}

}
