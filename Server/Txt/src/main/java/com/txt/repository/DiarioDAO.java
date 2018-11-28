package com.txt.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.txt.model.Diario;
import com.txt.model.Usuario;



public class DiarioDAO implements DAO<Object>  {
	
	private ConexaoMySQL conexao;
	
	public DiarioDAO() {
		super();
		this.conexao = new ConexaoMySQL("localhost", "root", "", "txt");
	}

	@Override
	public Object cadastrar(Object objeto) {
		Diario diario = (Diario) objeto;
		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO Diario VALUES(null, ?, curdate(), ?);";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, diario.getTextoDiario());
			statement.setLong(2, diario.getUsuario().getIdUsuario());
	
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()){
				diario.setIdDiario(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	
		return diario;
		
	}
	
	@Override
	public void editar(Object objeto) {
		Diario diario = (Diario) objeto;
		String sqlEditar = "UPDATE Diario SET texto_diario=? WHERE id_diario=?;";
		this.conexao.abrirConexao();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlEditar);
			statement.setString(1, diario.getTextoDiario());
			statement.setLong(2, diario.getIdDiario());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}
	
	@Override
	public void excluir(long id) {
		String sqlExcluir = "DELETE FROM Diario WHERE id_diario=?;";
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
	
	public List<Diario> buscarTodosPorIdUsuario(long idUsuario) {
				this.conexao.abrirConexao();
				String sqlSelect = "SELECT * FROM Diario d INNER JOIN Usuario u ON d.id_usuario = u.id_usuario WHERE id_usuario=? ORDER BY data_diario, id_diario DESC;";
				PreparedStatement statement;
				Diario diario = null;
				List<Diario> listaDiarios = new ArrayList<Diario>();
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlSelect);
					statement.setLong(1, idUsuario);
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()) {
						
						diario = new Diario();
						diario.setIdDiario(rs.getLong("id_diario"));
						diario.setTextoDiario(rs.getString("texto_diario"));
						diario.setDataDiario(rs.getString("data_diario"));
											
						Usuario usuario = new Usuario();
						usuario.setIdUsuario(rs.getLong("id_usuario"));
						usuario.setEmail(rs.getString("email"));
						usuario.setLogin(rs.getString("login"));
						usuario.setSenha(rs.getString("senha"));
						usuario.setDataNascimento(rs.getString("data_nascimento"));
						usuario.setUrlFoto(rs.getString("url_foto"));
						usuario.setBio(rs.getString("bio"));
						usuario.setGenero(rs.getString("genero"));
						usuario.setTelefone(rs.getString("telefone"));
						usuario.setUrlSite(rs.getString("url_site"));
						usuario.setNomeUsuario(rs.getString("nome_usuario"));
						diario.setUsuario(usuario);
						
						listaDiarios.add(diario);
						
						
						
						
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
				return listaDiarios;
			}
	
	public Diario buscarPorId(long id) {
		this.conexao.abrirConexao();
		String sqlInsert = "SELECT * FROM Diario d INNER JOIN Usuario u ON d.id_usuario = u.id_usuario WHERE id_diario=?;";
		PreparedStatement statement;
		Diario diario = null;
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				diario = new Diario();
				diario.setIdDiario(rs.getLong("id_diario"));
				diario.setTextoDiario(rs.getString("texto_diario"));
				diario.setDataDiario(rs.getString("data_diario"));
				
				Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setEmail(rs.getString("email"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setDataNascimento(rs.getString("data_nascimento"));
				usuario.setUrlFoto(rs.getString("url_foto"));
				usuario.setBio(rs.getString("bio"));
				usuario.setGenero(rs.getString("genero"));
				usuario.setTelefone(rs.getString("telefone"));
				usuario.setUrlSite(rs.getString("url_site"));
				usuario.setNomeUsuario(rs.getString("nome_usuario"));
				diario.setUsuario(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return diario;
	}
		
			
}


