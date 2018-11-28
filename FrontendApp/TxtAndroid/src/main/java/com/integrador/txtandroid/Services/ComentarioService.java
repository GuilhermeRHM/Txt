package com.integrador.txtandroid.Services;

import com.integrador.txtandroid.Model.Comentario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ComentarioService {

    @POST("comentario/")
    Call<Comentario> cadastrar(@Body Comentario comentario);

    @PUT("comentario/")
    Call<Void> editar(@Body Comentario comentario);

    @DELETE("comentario/{id}")
    Call<Void> excluir(@Path("id") long id);

    @GET("comentario/listar/{id}")
    Call<List<Comentario>> buscarPorIdPostagem(@Path("id") long id);

    @GET("comentario/qntdComentarios/{id}")
    Call<Integer> buscarQntdComentariosPorIdPostagem(@Path("id") long id);
}
