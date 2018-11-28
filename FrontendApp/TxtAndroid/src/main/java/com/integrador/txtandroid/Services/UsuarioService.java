package com.integrador.txtandroid.Services;

import com.integrador.txtandroid.Model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UsuarioService {

    @POST("usuario/")
    Call<Usuario> cadastrar(@Body Usuario usuario);

    @PUT("usuario/")
    Call<Void> editar(@Body Usuario usuario);

    @DELETE("usuario/{id}")
    Call<Void> excluir(@Path("id") long id);

    @GET("usuario/listar/")
    Call<List<Usuario>> buscarTodos();

    @GET("usuario/{id}")
    Call<Usuario> buscarPorId(@Path("id") long id);

    @GET("usuario/{login}/{senha}")
    Call<Usuario> buscarPorLoginESenha(@Path("login")String login, @Path("senha") String senha);

    @GET("usuario/{login}")
    Call<Usuario> buscarPorLogin(@Path("login")String login);

}
