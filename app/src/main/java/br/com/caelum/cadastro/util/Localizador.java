package br.com.caelum.cadastro.util;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Bruna on 15/01/16.
 */
public class Localizador {

    private Geocoder geo;

    public Localizador(Context context) {
        geo = new Geocoder(context);
    }

    public LatLng getCordenada(String endereco) {
        List<Address> listaEnderecos = null;
        try {
            listaEnderecos = geo.getFromLocationName(endereco, 1);

            if (!listaEnderecos.isEmpty()) {
                Address adress = listaEnderecos.get(0);
                return new LatLng(adress.getLatitude(), adress.getLongitude());
            } else {
                return null;
            }
        } catch (IOException e) {
            return null;
        }
    }


}
