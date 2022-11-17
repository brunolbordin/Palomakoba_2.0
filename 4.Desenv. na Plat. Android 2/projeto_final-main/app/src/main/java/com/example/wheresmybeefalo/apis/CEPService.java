package com.example.wheresmybeefalo.apis;

import com.example.wheresmybeefalo.models.CEP;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CEPService {

    @GET("{cep}/json/")
    Call<CEP> consultarCEP(@Path("cep") String cep);
}

