package com.txt.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.txt.model.Postagem;
import com.txt.repository.PostagemDAO;


@Controller    
@RequestMapping(path="/postagem/")
public class PostagemController {
	
	private PostagemDAO pDAO;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Postagem> cadastrar(@RequestBody Postagem postagem) {
		pDAO = new PostagemDAO();
		postagem = (Postagem) pDAO.cadastrar(postagem);
		return new ResponseEntity<Postagem>(postagem, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody Postagem postagem){
		pDAO = new PostagemDAO();
		pDAO.editar(postagem);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable long id){
		pDAO = new PostagemDAO();
		pDAO.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@RequestMapping(value = "listar/", method = RequestMethod.GET)
    public ResponseEntity<List<Postagem>> buscarTodos() {
		
		pDAO = new PostagemDAO();
		List<Postagem> listaPostagens = pDAO.buscarTodos();		
		return new ResponseEntity<List<Postagem>>(listaPostagens, HttpStatus.OK);
	}

	@RequestMapping(value = "listar/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Postagem>> buscarPorIdUsuario(@PathVariable long id) {
		pDAO = new PostagemDAO();
		List<Postagem> listaPostagens = pDAO.buscarPorIdUsuario(id);
		if(listaPostagens!=null) {
			return new ResponseEntity<List<Postagem>>(listaPostagens, HttpStatus.OK);
		} else {		
		return new ResponseEntity<List<Postagem>>(HttpStatus.NOT_FOUND);
	}
}
	@RequestMapping(value = "listarFavoritos/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Postagem>> buscarFavoritos(@PathVariable long id) {
		pDAO = new PostagemDAO();
		List<Postagem> listaPostagens = pDAO.buscarFavoritosPorIdUsuario(id);
		if(listaPostagens!=null) {
			return new ResponseEntity<List<Postagem>>(listaPostagens, HttpStatus.OK);
		} else {		
		return new ResponseEntity<List<Postagem>>(HttpStatus.NOT_FOUND);
	}
}
	
	@RequestMapping(value = "listar/{categoria}", method = RequestMethod.GET)
	public ResponseEntity<List<Postagem>> buscarPorCategoria(@PathVariable String categoria) {
		pDAO = new PostagemDAO();
		List<Postagem> listaPostagens = pDAO.buscarPorCategoria(categoria);
		if(listaPostagens!=null) {
			return new ResponseEntity<List<Postagem>>(listaPostagens, HttpStatus.OK);
		} else {
		return new ResponseEntity<List<Postagem>>(HttpStatus.NOT_FOUND);
	}
	}
	
	@RequestMapping(value = "listarSeguidos/{id}", method = RequestMethod.GET)
		public ResponseEntity<List<Postagem>> buscarPorSeguidos(@PathVariable long id) {
			pDAO = new PostagemDAO();
			List<Postagem> listaPostagens = pDAO.buscarPorSeguidos(id);
			if(listaPostagens!=null) {
				return new ResponseEntity<List<Postagem>>(listaPostagens, HttpStatus.OK);
			} else {		
			return new ResponseEntity<List<Postagem>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Postagem> buscarPorId(@PathVariable long id) {
		pDAO = new PostagemDAO();
		Postagem postagem = pDAO.buscarPorId(id);
		if(postagem!=null) {
			return new ResponseEntity<Postagem>(postagem, HttpStatus.OK);
		} else {		
		return new ResponseEntity<Postagem>(HttpStatus.NOT_FOUND);
	}
	}
	
	
	
}
		

		
		
		
		