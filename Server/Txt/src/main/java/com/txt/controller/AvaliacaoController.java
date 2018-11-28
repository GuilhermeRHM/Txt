package com.txt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.txt.model.Avaliacao;
import com.txt.repository.AvaliacaoDAO;

@Controller    
@RequestMapping(path="/avaliacao/")
public class AvaliacaoController {

	private AvaliacaoDAO aDAO;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Avaliacao> cadastrar(@RequestBody Avaliacao avaliacao) {
		aDAO = new AvaliacaoDAO();
		avaliacao = (Avaliacao) aDAO.cadastrar(avaliacao);
		return new ResponseEntity<Avaliacao>(avaliacao, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody Avaliacao avaliacao){
		aDAO = new AvaliacaoDAO();
		aDAO.editar(avaliacao);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable long id){
		aDAO = new AvaliacaoDAO();
		aDAO.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}
	
	@RequestMapping(value = "mediaAvaliacao/{id}", method = RequestMethod.GET)
    public ResponseEntity<Double> calcularMediaPostagem(@PathVariable long id) {
		
		aDAO = new AvaliacaoDAO();
		double media = aDAO.calcularMediaPostagem(id);
		if(media != 0) {
			return new ResponseEntity<Double>(media, HttpStatus.OK);
			} else {		
		return new ResponseEntity<Double>(HttpStatus.NOT_FOUND);
	}
	}
	

}
