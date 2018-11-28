package com.integrador.txtandroid.Services;

import com.integrador.txtandroid.Model.Avaliacao;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AvaliacaoService {

    @POST("avaliacao/")
    Call<Avaliacao> cadastrar(@Body Avaliacao avaliacao);

    @PUT("avaliacao/")
    Call<Void> editar(@Body Avaliacao avaliacao);

    @DELETE("avaliacao/{id}")
    Call<Void> excluir(@Path("id") long id);

    @GET("avaliacao/mediaAvaliacao/{id}")
    Call<Double> calcularMediaPostagem(@Path("id") long id);

}
