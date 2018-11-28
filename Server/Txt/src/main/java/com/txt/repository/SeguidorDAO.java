package com.txt.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.txt.model.Seguidor;
import com.txt.model.Usuario;

public class SeguidorDAO implements DAO<Object> {

	private ConexaoMySQL conexao;
	
	public SeguidorDAO() {
		super();
		this.conexao = new ConexaoMySQL("localhost", "root", "", "txt");
	}
	

	@Override
	public Object cadastrar(Object objeto) {
		Seguidor seguidor = (Seguidor) objeto;
		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO Seguidores VALUES(?, ?, null, 0);";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			statement.setLong(1, seguidor.getSeguidor().getIdUsuario());
			statement.setLong(2, seguidor.getSeguido().getIdUsuario());
			statement.setBoolean(3, seguidor.isStatus());
			
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()){
				seguidor.setId(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	
		return seguidor;
	}

	
	@Override
	public void editar(Object objeto) {
		Seguidor seguidor = (Seguidor) objeto;
		String sqlEditar = "UPDATE Seguidores SET status=1 WHERE id=?;";
		this.conexao.abrirConexao();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlEditar);
			statement.setBoolean(1, seguidor.isStatus());
			statement.setLong(2, seguidor.getId());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}


	@Override
	public void excluir(long id) {
		String sqlExcluir = "DELETE FROM Seguidores WHERE id=?;";
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

	public List<Seguidor> buscarTodosSeguidoresPorIdUsuario(long idUsuario) {
		this.conexao.abrirConexao();
		String sqlSelect = "SELECT * FROM Seguidores s "
				+ "INNER JOIN Usuario u ON s.id_usuario_seguidor = u.id_usuario "
				+ "WHERE s.id_usuario_seguido=? AND s.status_seguidores=0;";
		PreparedStatement statement;
		Seguidor seguidor = null;
		List<Seguidor> listaSeguidores = new ArrayList<Seguidor>();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			statement.setLong(1, idUsuario);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				
				seguidor = new Seguidor();
				seguidor.setId(rs.getLong("id"));
				seguidor.setStatus(rs.getBoolean("status_seguidores"));
				
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
				seguidor.setSeguidor(usuario);
				
				listaSeguidores.add(seguidor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return listaSeguidores;
	}
	

	public List<Seguidor> buscarTodosSeguidosPorIdUsuario(long idUsuario) {
		this.conexao.abrirConexao();
		String sqlSelect = "SELECT * FROM Seguidores s "
				+ "INNER JOIN Usuario u ON s.id_usuario_seguido = u.id_usuario "
				+ "WHERE s.id_usuario_seguidor=? AND s.status_seguidores=0;";
		PreparedStatement statement;
		Seguidor seguido = null;
		List<Seguidor> listaSeguidos = new ArrayList<Seguidor>();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			statement.setLong(1, idUsuario);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				seguido = new Seguidor();
				seguido.setId(rs.getLong("id"));
				seguido.setStatus(rs.getBoolean("status_seguidores"));
				
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
				seguido.setSeguido(usuario);
				
				listaSeguidos.add(seguido);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return listaSeguidos;
	}

	public int buscarQntdSeguidoresPorIdUsuario(long idUsuario) {
		this.conexao.abrirConexao();
		String sqlSelect = "SELECT COUNT(id_usuario_seguidor) FROM Seguidores s WHERE s.id_usuario_seguido=? AND status_seguidores=0;";
		PreparedStatement statement;
		int countSeguidores = 0;
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			statement.setLong(1, idUsuario);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				countSeguidores = rs.getInt("COUNT(id_usuario_seguidor)");
			} 
		} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
			return countSeguidores;
	}

	public int buscarQntdSeguidosPorIdUsuario(long idUsuario) {
		this.conexao.abrirConexao();
		String sqlSelect = "SELECT COUNT(id_usuario_seguido) FROM Seguidores s WHERE s.id_usuario_seguidor=? AND status_seguidores=0;";
		PreparedStatement statement;
		int countSeguidos = 0;
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			statement.setLong(1, idUsuario);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				countSeguidos = rs.getInt("COUNT(id_usuario_seguido)");
			} 
		} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
			return countSeguidos;
	}

}
