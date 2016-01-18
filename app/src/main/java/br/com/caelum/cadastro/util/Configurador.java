package br.com.caelum.cadastro.util;

import android.content.Context;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;

/**
 * Created by Bruna on 15/01/16.
 */
public class Configurador implements GoogleApiClient.ConnectionCallbacks{

    private AtualizadorDeLocalização atualizador;

    public Configurador(AtualizadorDeLocalização atualizador) {
        this.atualizador = atualizador;
    }

    @Override
    public void onConnected(Bundle bundle) {

        LocationRequest request = LocationRequest.create();
        request.setInterval(2000); //intervalo de requisição de localização a cada 2 seg
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        request.setSmallestDisplacement(50); //minimo deslocamento em m para fazer a requisição

        atualizador.inicia(request);

    }

    @Override
    public void onConnectionSuspended(int i) {
        atualizador.cancela();
        Log.i("Location", "GoogleApiClient connection has been suspended");
    }


}
