package com.bicarg.inventarios.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bicarg.inventarios.dao.ICatCategoriaDao;
import com.bicarg.inventarios.model.CatCategoria;
import com.bicarg.inventarios.response.CategoriaResponseRest;

@Service
public class CategoriaServicesImpl implements ICategoriaService{

	@Autowired
	private ICatCategoriaDao categoriaDao;
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoriaResponseRest> search() {
		CategoriaResponseRest response = new CategoriaResponseRest();
		try {
			List<CatCategoria> categoria = (List<CatCategoria>) categoriaDao.findAll();
			
			response.getCategoriaResponse().setCategoria(categoria);
			response.setMetadata("Respuesta ok", "00", "exitosa");
			
		} catch (Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error al consultar");
			e.getStackTrace();
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}

}
