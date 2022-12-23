package com.co.neoristransaccional.pruebaneoris.movimiento.domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.neoristransaccional.pruebaneoris.movimiento.application.ReporteMovimiento;

import jakarta.persistence.EntityManager;

@Service
public class MovimientoServiceRepoImpl implements MovimientoServiceCustomRepo{

	@Autowired
	EntityManager entityManager;
	
	@Override
	public ReporteMovimiento reporte() {
		var configuration = new Configuration().configure("hibernate.cfg.xml");
		var builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
		var factory = configuration.buildSessionFactory(builder.build());
		
		var query = "SELECT \r\n"
				+ "movimiento.id, movimiento.fecha, movimiento.tipo_movimiento, movimiento.valor, \r\n"
				+ "persona.nombre, persona.identificacion\r\n"
				+ "FROM `movimiento` \r\n"
				+ "INNER JOIN `cliente` ON `cliente`.id = movimiento.cliente_id\r\n"
				+ "INNER JOIN `persona` ON `persona`.id = cliente.id_persona\r\n"
				+ "WHERE \r\n"
				+ "movimiento.fecha BETWEEN '2022-12-22 00:00:00' AND '2022-12-22 23:59:59' AND \r\n"
				+ "movimiento.cliente_id = 8;";
		
		return null;
	}

}
