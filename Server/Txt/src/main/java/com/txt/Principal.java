package com.txt;

import com.txt.model.*;
import com.txt.repository.*;

public class Principal {
	public static void main(String[] args) {

		Avaliacao avaliacao = new Avaliacao();
		AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
		
		Comentario comentario = new Comentario();
		ComentarioDAO comentarioDAO = new ComentarioDAO();
		
		Curtida curtida = new Curtida();
		CurtidaDAO curtidaDAO = new CurtidaDAO(); 
		
		Diario diario = new Diario();
		DiarioDAO diarioDAO = new DiarioDAO();
		
		Postagem postagem = new Postagem();
		PostagemDAO postagemDAO = new PostagemDAO();
		
		Seguidor seguidor = new Seguidor();
		SeguidorDAO seguidorDAO = new SeguidorDAO();
			
		Usuario usuario = new Usuario();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		
		
		
}
}