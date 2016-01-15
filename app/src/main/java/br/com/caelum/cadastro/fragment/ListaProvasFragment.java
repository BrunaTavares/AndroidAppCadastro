package br.com.caelum.cadastro.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import br.com.caelum.cadastro.ProvasActivity;
import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.modelo.Prova;

/**
 * Created by Bruna on 13/01/16.
 */
public class ListaProvasFragment extends android.support.v4.app.Fragment{

    private ListView listViewProvas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View layoutProvas= inflater.inflate(R.layout.fragment_lista_provas,container,false);
        listViewProvas = (ListView) layoutProvas.findViewById(R.id.lista_provas_listview);

        Prova prova1 = new Prova("10/02/2016","Redes de Computadores II");
        prova1.setTopicos(Arrays.asList("Ipv6","Calculo de Subredes","Todo bimestre!"));
        Prova prova2 = new Prova("15/02/2016","Métodos Numéricos");
        prova2.setTopicos(Arrays.asList("Linearização","Métodos de Runge-Kuta"));

        List<Prova> provas = Arrays.asList(prova1,prova2);

        listViewProvas.setAdapter(new ArrayAdapter<Prova>(getActivity(),android.R.layout.simple_list_item_1,provas));

        listViewProvas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int posicao, long id) {
                Prova selecionada = (Prova) adapter.getItemAtPosition(posicao);

                ProvasActivity calendarioProvas = (ProvasActivity) getActivity();
                calendarioProvas.selecionaProva(selecionada);
                //Toast.makeText(getActivity(), "Prova Selecionada: " + selecionada.toString(), Toast.LENGTH_LONG).show();

            }
        });



        return layoutProvas;
    }
}
