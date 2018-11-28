package com.integrador.txtandroid.Services;

import com.integrador.txtandroid.Model.Diario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DiarioService {

    @POST("diario/")
    Call<Diario> cadastrar(@Body Diario diario);

    @PUT("diario/")
    Call<Void> editar(@Body Diario diario);

    @DELETE("diario/{id}")
    Call<Void> excluir(@Path("id") long id);

    @GET("diario/listar/{id}")
    Call<List<Diario>> buscarTodosPorIdUsuario(@Path("id") long id);

    @GET("diario/{id}")
    Call<Diario> buscarPorId(@Path("id") long id);
}
