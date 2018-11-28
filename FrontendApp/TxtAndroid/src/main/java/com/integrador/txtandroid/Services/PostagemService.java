package com.integrador.txtandroid.Services;

import com.integrador.txtandroid.Model.Postagem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PostagemService {

    @POST("postagem/")
    Call<Postagem> cadastrar(@Body Postagem postagem);

    @PUT("postagem/")
    Call<Void> editar(@Body Postagem postagem);

    @DELETE("postagem/{id}")
    Call<Void> excluir(@Path("id") long id);

    @GET("postagem/listar/")
    Call<List<Postagem>> buscarTodos();

    @GET("postagem/listar/{id}")
    Call<List<Postagem>> buscarPorIdUsuario(@Path("id") long id);

    @GET("postagem/{id}")
    Call<Postagem> buscarPorId(@Path("id") long id);

    @GET("postagem/listarFavoritos/{id}")
    Call<List<Postagem>> buscarFavoritos(@Path("id") long id);

    @GET("postagem/listar/{categoria}")
    Call<List<Postagem>> buscarPorCategoria(@Path("categoria")String categoria);

    @GET("postagem/listarSeguidos/{id}")
    Call<List<Postagem>> buscarPorSeguidos(@Path("id") long id);
}
