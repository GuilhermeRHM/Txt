package com.txt.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.txt.model.Seguidor;
import com.txt.repository.SeguidorDAO;

@Controller    
@RequestMapping(path="/seguidor/")
public class SeguidorController{
	
	private SeguidorDAO sDAO; 
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Seguidor> cadastrar(@RequestBody Seguidor seguidor) {
		sDAO = new SeguidorDAO();
		seguidor = (Seguidor) sDAO.cadastrar(seguidor);
		return new ResponseEntity<Seguidor>(seguidor, HttpStatus.CREATED);

	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody Seguidor seguidor){
		sDAO = new SeguidorDAO();
		sDAO.editar(seguidor);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable long id){
		sDAO = new SeguidorDAO();
		sDAO.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@RequestMapping(value = "listarSeguidores/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Seguidor>> buscarTodosSeguidoresPorIdUsuario(@PathVariable long id) {
		
		sDAO = new SeguidorDAO();
		List<Seguidor> listaSeguidores = sDAO.buscarTodosSeguidoresPorIdUsuario(id);		
		return new ResponseEntity<List<Seguidor>>(listaSeguidores, HttpStatus.OK);
	}
	
	@RequestMapping(value = "listarSeguidos/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Seguidor>> buscarTodosSeguidosPorIdUsuario(@PathVariable long id) {
		
		sDAO = new SeguidorDAO();
		List<Seguidor> listaSeguidos = sDAO.buscarTodosSeguidosPorIdUsuario(id);		
		return new ResponseEntity<List<Seguidor>>(listaSeguidos, HttpStatus.OK);
	}
	
	@RequestMapping(value = "qntdSeguidos/{id}", method = RequestMethod.GET)
    public ResponseEntity<Integer> buscarQntdSeguidosPorIdUsuario(@PathVariable long id) {
		
		sDAO = new SeguidorDAO();
		int countSeguidos = sDAO.buscarQntdSeguidosPorIdUsuario(id);	
		if(countSeguidos!=0) {
			return new ResponseEntity<Integer>(countSeguidos, HttpStatus.OK);
			} else {		
		return new ResponseEntity<Integer>(HttpStatus.NOT_FOUND);
	}
	}
	
	@RequestMapping(value = "qntdSeguidores/{id}", method = RequestMethod.GET)
    public ResponseEntity<Integer> buscarQntdSeguidoresPorIdUsuario(@PathVariable long id) {
		
		sDAO = new SeguidorDAO();
		int countSeguidores = sDAO.buscarQntdSeguidoresPorIdUsuario(id);	
		if(countSeguidores!=0) {
			return new ResponseEntity<Integer>(countSeguidores, HttpStatus.OK);
			} else {		
		return new ResponseEntity<Integer>(HttpStatus.NOT_FOUND);
	}
		
	}
	
	
	
	
}
