package com.bicarg.inventarios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bicarg.inventarios.model.CatCategoria;
import com.bicarg.inventarios.response.CategoriaResponseRest;
import com.bicarg.inventarios.services.ICategoriaService;

@RestController
@RequestMapping("/api/v1")
public class CategoriaRestController {
	
	@Autowired
	private ICategoriaService service;
	
	
	/**
	 * get all the categories
	 * @return
	 */
	@GetMapping("/categorias")
	public ResponseEntity<CategoriaResponseRest> searchCategorias(){
		ResponseEntity<CategoriaResponseRest> response = service.search();
		return response;
	}
	
	/**
	 * get categories by id
	 * @param id
	 * @return
	 */
	@GetMapping("/categorias/{id}")
	public ResponseEntity<CategoriaResponseRest> searchCategoriasById(@PathVariable Long id){
		
		ResponseEntity<CategoriaResponseRest> response = service.searchById(id);
		return response;
	}
	
	/**
	 * Save categories
	 * @param categoria
	 * @return
	 */
	@PostMapping("/categorias")
	public ResponseEntity<CategoriaResponseRest> save(@RequestBody CatCategoria categoria){
		
		ResponseEntity<CategoriaResponseRest> response = service.save(categoria);
		return response;
	}
	
	/**
	 * Update categorias
	 * @param categoria
	 * @param id
	 * @return
	 */
	@PutMapping("/categorias/{id}")
	public ResponseEntity<CategoriaResponseRest> update(@RequestBody CatCategoria categoria, @PathVariable Long id){
		
		
		ResponseEntity<CategoriaResponseRest> response = service.update(categoria, id);
		return response;
	}
	
	
	/**
	 * Delete categoria
	 * @param id
	 * @return
	 */
	@DeleteMapping("/categorias/{id}")
	public ResponseEntity<CategoriaResponseRest> delete(@PathVariable Long id){		
		ResponseEntity<CategoriaResponseRest> response = service.deleteById(id);
		return response;
	}

}
