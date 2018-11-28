package com.txt.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.txt.model.Diario;
import com.txt.repository.DiarioDAO;


@Controller    
@RequestMapping(path="/diario/") 

public class DiarioController {
	
	private DiarioDAO dDAO;
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Diario> cadastrar(@RequestBody Diario diario) {
		dDAO = new DiarioDAO();
		diario = (Diario) dDAO.cadastrar(diario);
		return new ResponseEntity<Diario>(diario, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody Diario diario){
		dDAO = new DiarioDAO();
		dDAO.editar(diario);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable long id){
		dDAO = new DiarioDAO();
		dDAO.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}
	
	@RequestMapping(value = "listar/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Diario>> buscarTodosPorIdUsuario(@PathVariable long id) {
		dDAO = new DiarioDAO();
		List<Diario> listaDiarios = dDAO.buscarTodosPorIdUsuario(id);
		if(listaDiarios!=null) {
			return new ResponseEntity<List<Diario>>(listaDiarios, HttpStatus.OK);
		} else {		
		return new ResponseEntity<List<Diario>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Diario> buscarPorId(@PathVariable long id) {
		dDAO = new DiarioDAO();
		Diario diario = dDAO.buscarPorId(id);
		if(diario!=null) {
			return new ResponseEntity<Diario>(diario, HttpStatus.OK);
		} else {		
		return new ResponseEntity<Diario>(HttpStatus.NOT_FOUND);
	}
	}
}
