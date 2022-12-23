package com.co.neoristransaccional.pruebaneoris.movimiento.application;

import java.math.BigInteger;
import java.time.LocalDateTime;

public interface MovimientoService {

	MovimientoResponse crear (MovimientoRequest movimientoRequest);
	ReporteMovimiento reportes (LocalDateTime fechaInicio, LocalDateTime fechaFin, BigInteger idCliente);
}
