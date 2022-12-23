package com.co.neoristransaccional.pruebaneoris.movimiento.infrastructure;

import java.math.BigInteger;
import java.sql.Timestamp;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.co.neoristransaccional.pruebaneoris.movimiento.application.MovimientoRequest;
import com.co.neoristransaccional.pruebaneoris.movimiento.application.MovimientoResponse;
import com.co.neoristransaccional.pruebaneoris.movimiento.application.MovimientoService;
import com.co.neoristransaccional.pruebaneoris.movimiento.application.ReporteMovimiento;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("movimientos")
public class MovimientoController {

	MovimientoService movimientoService;
	
	public MovimientoController(MovimientoService movimientoService) {
		super();
		this.movimientoService = movimientoService;
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.OK)
	public MovimientoResponse crear(@RequestBody MovimientoRequest movimientoRequest) {
		return movimientoService.crear(movimientoRequest);
	}

	@GetMapping("reportes")
	@ResponseStatus(value = HttpStatus.OK)
	public ReporteMovimiento buscarPorId(@RequestParam("fechaInicio") String fechaInicio,
			@RequestParam("fechaFin") String fechaFin,
			@RequestParam("idCliente") BigInteger idCliente) {
		return movimientoService.reportes(Timestamp.valueOf(fechaInicio).toLocalDateTime(), Timestamp.valueOf(fechaFin).toLocalDateTime(), idCliente);
	}
}
