package br.com.caelum.cadastro;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import br.com.caelum.cadastro.fragment.DetalhesProvaFragment;
import br.com.caelum.cadastro.fragment.ListaProvasFragment;

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

        transaction.commit();

    }
     public boolean isTablet(){
         return getResources().getBoolean(R.bool.isTablet);

     }
}
