package br.com.caelum.cadastro;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import br.com.caelum.cadastro.fragment.DetalhesProvaFragment;
import br.com.caelum.cadastro.fragment.ListaProvasFragment;
import br.com.caelum.cadastro.modelo.Prova;

/**
 * Created by android5519 on 13/01/16.
 */
public class ProvasActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);

        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (isTablet()){
            transaction.replace(R.id.provas_lista, new ListaProvasFragment());
            transaction.replace(R.id.provas_detalhes, new DetalhesProvaFragment());
        }else{
            transaction.replace(R.id.provas_view, new ListaProvasFragment());
        }
        transaction.addToBackStack(null);
        transaction.commit();

    }
     public boolean isTablet(){
         return getResources().getBoolean(R.bool.isTablet);

     }

    public void selecionaProva(Prova prova){

        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();

        if(isTablet()) {
            DetalhesProvaFragment detalhesProva = (DetalhesProvaFragment) manager.findFragmentById(R.id.provas_detalhes);
            detalhesProva.populaDados(prova);
        }else {

            Bundle argumentos = new Bundle();
            argumentos.putSerializable("prova", prova);

            DetalhesProvaFragment detalhesProva = new DetalhesProvaFragment();
            detalhesProva.setArguments(argumentos);

            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.provas_view,detalhesProva);
            transaction.addToBackStack(null);
            transaction.commit();

            }
        }
    }


