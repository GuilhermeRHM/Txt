package com.txt.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.txt.model.Comentario;
import com.txt.repository.ComentarioDAO;

@Controller    
@RequestMapping(path="/comentario/")
public class ComentarioController {
	
	private ComentarioDAO cDAO;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Comentario> cadastrar(@RequestBody Comentario comentario) {
		cDAO = new ComentarioDAO();
		comentario = (Comentario) cDAO.cadastrar(comentario);
		return new ResponseEntity<Comentario>(comentario, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody Comentario comentario){
		cDAO = new ComentarioDAO();
		cDAO.editar(comentario);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable long id){
		cDAO = new ComentarioDAO();
		cDAO.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}
	
	@RequestMapping(value = "listar/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Comentario>> buscarPorIdPostagem(@PathVariable long id) {
		cDAO = new ComentarioDAO();
		List<Comentario> listaComentarios = cDAO.buscarTodosPorIdPostagem(id);
		if(listaComentarios!=null) {
			return new ResponseEntity<List<Comentario>>(listaComentarios, HttpStatus.OK);
		} else {		
		return new ResponseEntity<List<Comentario>>(HttpStatus.NOT_FOUND);
	}
	}
	
	@RequestMapping(value = "qntdComentarios/{id}", method = RequestMethod.GET)
    public ResponseEntity<Integer> buscarQntdComentariosPorIdPostagem(@PathVariable long id) {
		
		cDAO = new ComentarioDAO();
		int countComentarios = cDAO.buscarQntdComentariosPorIdPostagem(id);
		if(countComentarios!= 0) {
			return new ResponseEntity<Integer>(countComentarios, HttpStatus.OK);
			} else {		
		return new ResponseEntity<Integer>(HttpStatus.NOT_FOUND);
	}
	}
	

}
