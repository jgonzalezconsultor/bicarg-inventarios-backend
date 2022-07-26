package com.bicarg.inventarios.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoriaResponseRest> searchById(Long id) {
		
		CategoriaResponseRest response = new CategoriaResponseRest();
		List<CatCategoria> list = new ArrayList<>();
		try {
			Optional<CatCategoria> categoria = categoriaDao.findById(id);
			if(categoria.isPresent()) {
				list.add(categoria.get());
				response.getCategoriaResponse().setCategoria(list);
				response.setMetadata("Respuesta ok", "00", "Categoría encontrada");
			}
			else {
				response.setMetadata("Respuesta nok", "-1", "Categoría no encontrada");
				return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error al consultar por Id");
			e.getStackTrace();
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	
	}

	@Override
	@Transactional
	public ResponseEntity<CategoriaResponseRest> save(CatCategoria categoria) {
		CategoriaResponseRest response = new CategoriaResponseRest();
		List<CatCategoria> list = new ArrayList<>();
		try {		
			CatCategoria categoriaSaved = categoriaDao.save(categoria);
			if(categoriaSaved != null) {
				list.add(categoriaSaved);
				response.getCategoriaResponse().setCategoria(list);
				response.setMetadata("Respuesta ok", "00", "Categoría guardada");
			}
			else {
				response.setMetadata("Respuesta nok", "-1", "Categoría no almacenada");
				return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error al almacenar la categoría");
			e.getStackTrace();
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<CategoriaResponseRest> update(CatCategoria categoria, Long id) {
		CategoriaResponseRest response = new CategoriaResponseRest();
		List<CatCategoria> list = new ArrayList<>();
		try {		
			Optional<CatCategoria> categoriaSearch = categoriaDao.findById(id);
			if(categoriaSearch.isPresent()) {
				//Se procede a actualizar el registro
				categoriaSearch.get().setNombre(categoria.getNombre());
				categoriaSearch.get().setDescripcion(categoria.getDescripcion());
				CatCategoria categoriaToUpdate = categoriaDao.save(categoriaSearch.get());
				
				if(categoriaToUpdate != null) {
					list.add(categoriaToUpdate);
					response.getCategoriaResponse().setCategoria(list);
					response.setMetadata("Respuesta OK", "00", "categoria actualizada");
				}else {
					response.setMetadata("Respuesta nok", "-1", "Categoría no actualizada");
					return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			}
			else {
				response.setMetadata("Respuesta nok", "-1", "Categoría no encontrada");
				return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			
		} catch (Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error al actualizar la categoría");
			e.getStackTrace();
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}
	
	

	@Override
	@Transactional
	public ResponseEntity<CategoriaResponseRest> deleteById(Long id) {
		CategoriaResponseRest response = new CategoriaResponseRest();
		try {		
			categoriaDao.deleteById(id);
			response.setMetadata("respuesta OK", "00", "Registro eliminado");
		} catch (Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error al eliminar");
			e.getStackTrace();
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}

}

