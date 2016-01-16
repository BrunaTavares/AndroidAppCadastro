package br.com.caelum.cadastro.util;

import android.content.Context;
import android.location.LocationListener;
import android.os.Bundle;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;

/**
 * Created by Bruna on 15/01/16.
 */
public class Configurador implements GoogleApiClient.ConnectionCallbacks{

    private AtualizadorDeLocalização localizacao;



    public Configurador(AtualizadorDeLocalização localizacao ){
        this.localizacao = localizacao;
    }

    @Override
    public void onConnected(Bundle bundle) {
        LocationRequest request = LocationRequest.create();
        request.setInterval(2000); //intervalo de requisição de localização
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        request.setSmallestDisplacement(50); //minimo deslocamento em m para fazer a requisição

        localizacao.inicia(request);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }


}
