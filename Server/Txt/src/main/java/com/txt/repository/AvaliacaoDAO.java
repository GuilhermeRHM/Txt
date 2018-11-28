package com.txt.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.txt.model.Avaliacao;



public class AvaliacaoDAO implements DAO <Object>{
	
	private ConexaoMySQL conexao;
	
	public AvaliacaoDAO() {
		super();
		this.conexao = new ConexaoMySQL("localhost", "root", "", "txt");
	}
	
	@Override
	public Object cadastrar (Object objeto) {
		Avaliacao avaliacao = (Avaliacao) objeto;
		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO Avaliacao VALUES(null, ?, ?, ?);";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			statement.setLong(1, avaliacao.getUsuario().getIdUsuario());
			statement.setLong(2, avaliacao.getPostagem().getIdPostagem());
			statement.setDouble(3, avaliacao.getNota());
	
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()){
				avaliacao.setIdAvaliacao(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	
		return avaliacao;
		
	}
	
	@Override
	public void editar(Object objeto) {
		Avaliacao avaliacao = (Avaliacao) objeto;
		String sqlEditar = "UPDATE Avaliacao SET nota=? WHERE id_avaliacao=?;"; 
		this.conexao.abrirConexao();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlEditar);
			statement.setDouble(1, avaliacao.getNota());
			statement.setLong(2, avaliacao.getIdAvaliacao());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	
	@Override
	public void excluir(long id) {
		String sqlExcluir = "DELETE FROM Avaliacao WHERE id_avaliacao=?;";
		this.conexao.abrirConexao();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlExcluir);
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public Double calcularMediaPostagem(long id){
				this.conexao.abrirConexao();
				String sqlSelect = "SELECT AVG(nota) FROM Avaliacao WHERE id_postagem=?;";
				PreparedStatement statement;
				double media =0;
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlSelect);
					statement.setLong(1, id);
					ResultSet rs = statement.executeQuery();
					if(rs.next()) {
						media = rs.getDouble("AVG(nota)");
						
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
				return media;
			}	
			
			
			
}

