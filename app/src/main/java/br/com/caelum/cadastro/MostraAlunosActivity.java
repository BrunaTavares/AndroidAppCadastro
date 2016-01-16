package br.com.caelum.cadastro;


import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import br.com.caelum.cadastro.fragment.MapasFragment;

/**
 * Created by Bruna on 15/01/16.
 */
public class MostraAlunosActivity extends ActionBarActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostra_alunos);

        MapasFragment mapaFragment = new MapasFragment();

        android.support.v4.app.FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.mostra_alunos_mapa,mapaFragment);
        tx.commit();

    }
}
