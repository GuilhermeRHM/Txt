package com.txt.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.txt.model.Curtida;
import com.txt.repository.CurtidaDAO;

@Controller    
@RequestMapping(path="/curtida/")
public class CurtidaController {

	private CurtidaDAO cDAO;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Curtida> cadastrar(@RequestBody Curtida curtida) {
		cDAO = new CurtidaDAO();
		curtida = cDAO.cadastrar(curtida);
		return new ResponseEntity<Curtida>(curtida, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable long id){
		cDAO = new CurtidaDAO();
		cDAO.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}
	

	@RequestMapping(value = "listar/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Curtida>> buscarTodasPorIdUsuario(@PathVariable long id) {
		cDAO = new CurtidaDAO();
		List<Curtida> listaCurtidas = cDAO.buscarTodasPorIdUsuario(id);
		if(listaCurtidas!=null) {
			return new ResponseEntity<List<Curtida>>(listaCurtidas, HttpStatus.OK);
		} else {		
		return new ResponseEntity<List<Curtida>>(HttpStatus.NOT_FOUND);
	}
	}
		
	@RequestMapping(value = "qntdCurtidas/{id}", method = RequestMethod.GET)
    public ResponseEntity<Integer> buscarQntdCurtidasPorIdPostagem(@PathVariable long id) {
		
		cDAO = new CurtidaDAO();
		int countCurtidas = cDAO.buscarQntdCurtidasPorIdPostagem(id);
		if(countCurtidas != 0) {
			return new ResponseEntity<Integer>(countCurtidas, HttpStatus.OK);
			} else {		
		return new ResponseEntity<Integer>(HttpStatus.NOT_FOUND);
	}
	}
	
}
