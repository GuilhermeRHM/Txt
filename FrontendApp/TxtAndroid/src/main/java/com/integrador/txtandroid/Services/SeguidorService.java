package com.integrador.txtandroid.Services;

import com.integrador.txtandroid.Model.Seguidor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SeguidorService {

    @POST("seguidor/")
    Call<Seguidor> cadastrar(@Body Seguidor seguidor);

    @PUT("seguidor/")
    Call<Void> editar(@Body Seguidor seguidor);

    @DELETE("seguidor/{id}")
    Call<Void> excluir(@Path("id") long id);

    @GET("seguidor/listarSeguidores/{id}")
    Call<List<Seguidor>> buscarTodosSeguidoresPorIdUsuario(@Path("id") long id);

    @GET("seguidor/listarSeguidos/{id}")
    Call<List<Seguidor>> buscarTodosSeguidosPorIdUsuario(@Path("id") long id);

    @GET("seguidor/qntdSeguidores/{id}")
    Call<Integer> buscarQntdSeguidoresPorIdUsuario(@Path("id") long id);

    @GET("seguidor/qntdSeguidos/{id}")
    Call<Integer> buscarQntdSeguidosPorIdUsuario(@Path("id") long id);

}
