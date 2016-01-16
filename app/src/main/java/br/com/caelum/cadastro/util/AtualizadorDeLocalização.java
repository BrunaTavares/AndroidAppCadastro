package br.com.caelum.cadastro.util;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import br.com.caelum.cadastro.fragment.MapasFragment;

/**
 * Created by Bruna on 15/01/16.
 */
public class AtualizadorDeLocalização implements GoogleApiClient.ConnectionCallbacks,LocationListener{

    private GoogleApiClient client;
    private MapasFragment mapa;


    public AtualizadorDeLocalização(Context context, MapasFragment mapa) {
        this.mapa = mapa;
        Configurador config = new Configurador(this);

        this.client = new GoogleApiClient.Builder(context).addApi(LocationServices.API).addConnectionCallbacks(config).build();
        this.client.connect();
    }
    public void inicia(LocationRequest request){
        LocationServices.FusedLocationApi.requestLocationUpdates(client,request,this);
    }
    public void cancela(){
        LocationServices.FusedLocationApi.removeLocationUpdates(client,this);
        this.client.disconnect();
    }
    @Override
    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        LatLng local = new LatLng(latitude,longitude);

        this.mapa.centralizaNo(local);


    }


    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
