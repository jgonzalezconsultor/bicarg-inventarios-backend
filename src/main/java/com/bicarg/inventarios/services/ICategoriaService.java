package com.bicarg.inventarios.services;

import org.springframework.http.ResponseEntity;

import com.bicarg.inventarios.model.CatCategoria;
import com.bicarg.inventarios.response.CategoriaResponseRest;

public interface ICategoriaService {
	
	public ResponseEntity<CategoriaResponseRest> search();
	
	public ResponseEntity<CategoriaResponseRest> searchById(Long id);
	
	public ResponseEntity<CategoriaResponseRest> save(CatCategoria categoria);

}
