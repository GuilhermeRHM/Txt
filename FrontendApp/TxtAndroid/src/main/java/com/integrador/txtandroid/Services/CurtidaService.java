package com.integrador.txtandroid.Services;

import com.integrador.txtandroid.Model.Curtida;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CurtidaService {

    @POST("curtida/")
    Call<Curtida> cadastrar(@Body Curtida curtida);

    @DELETE("curtida/{id}")
    Call<Void> excluir(@Path("id") long id);

    @GET("curtida/listar/{id}")
    Call<List<Curtida>> buscarTodasPorIdUsuario(@Path("id") long id);

    @GET("curtida/qntdCurtidas/{id}")
    Call<Integer> buscarQntdCurtidasPorIdPostagem(@Path("id") long id);

}
