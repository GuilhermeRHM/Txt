package com.txt.repository;


public interface DAO <Objeto extends Object>{
	
	Objeto cadastrar(Objeto objeto);
	void editar(Objeto objeto);
	void excluir(long id);
	

}
