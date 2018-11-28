package com.txt.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.txt.model.Curtida;
import com.txt.model.Postagem;
import com.txt.model.Usuario;



public class CurtidaDAO {
	
	private ConexaoMySQL conexao;
	
	public CurtidaDAO() {
		super();
		this.conexao = new ConexaoMySQL("localhost", "root", "", "txt");
	}

	public Curtida cadastrar(Curtida curtida) {
		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO curtida VALUES(null, ?, ?);";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			statement.setLong(1, curtida.getUsuario().getIdUsuario());
			statement.setLong(2, curtida.getPostagem().getIdPostagem());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()){
				curtida.setIdCurtida(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	
		return curtida;
		
	}
	
	
	
	public void excluir(long id) {
		String sqlExcluir = "DELETE FROM curtida WHERE id_curtida=?;";
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
	
	
	
	
	
			public List<Curtida> buscarTodasPorIdUsuario(long idUsuario) {
				this.conexao.abrirConexao();
				String sqlSelect = "SELECT * FROM Curtida c "
						+ "INNER JOIN Usuario u ON c.id_usuario = u.id_usuario "
						+ "INNER JOIN Postagem p ON c.id_postagem = p.id_postagem WHERE c.id_usuario=?;";
				PreparedStatement statement;
				Curtida curtida = null;
				List<Curtida> listaCurtidas = new ArrayList<Curtida>();
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlSelect);
					statement.setLong(1, idUsuario);
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()) {
						curtida = new Curtida();
						curtida.setIdCurtida(rs.getLong("id_curtida"));
						
						Postagem postagem = new Postagem();
						postagem.setIdPostagem(rs.getLong("id_postagem"));
						postagem.setNomePostagem(rs.getString("nome_postagem"));
						postagem.setCategoria(rs.getString("categoria"));
						postagem.setDataPostagem(rs.getString("data_postagem"));
						postagem.setTextoPostagem(rs.getString("texto_postagem"));

						curtida.setPostagem(postagem);
						
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
						curtida.setUsuario(usuario);
						
						listaCurtidas.add(curtida);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
				return listaCurtidas;
			} 
			public int buscarQntdCurtidasPorIdPostagem(long idPostagem) {
				this.conexao.abrirConexao();
				String sqlSelect = "SELECT COUNT(id_curtida) FROM curtida WHERE id_postagem=?;";
				PreparedStatement statement;
				int countCurtidas = 0;
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlSelect);
					statement.setLong(1, idPostagem);
					ResultSet rs = statement.executeQuery();
					if(rs.next()) {
						countCurtidas = rs.getInt("COUNT(id_curtida)");
					} 
				} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						this.conexao.fecharConexao();
					}
					return countCurtidas;
			}
			 
			
				

			
}

