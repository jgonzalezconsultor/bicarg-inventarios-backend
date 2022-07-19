package com.bicarg.inventarios.response;

import java.util.List;

import com.bicarg.inventarios.model.CatCategoria;

import lombok.Data;

@Data
public class CategoriaResponse {
	
	private List<CatCategoria> categoria;

}
