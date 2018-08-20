package br.com.claitonneri.requisicaoretrofit.API;

//
// Created by Claiton Neri on 19/08/18.
//

import br.com.claitonneri.requisicaoretrofit.Model.Cep;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CepService {

    @GET("01001000/json/")
    Call<Cep> recuperarCEP();
}
