package br.com.caelum.cadastro;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import java.util.List;

import br.com.caelum.cadastro.adapter.GaleriaAlunosAdapter;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.modelo.Aluno;


/**
 * Created by bruna on 17/01/16.
 */
public class GaleriaActivity extends ActionBarActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> alunos = dao.getLista();
        dao.close();

        setContentView(R.layout.activity_galeria);
        ViewPager gallery = (ViewPager) findViewById(R.id.gallery);

        GaleriaAlunosAdapter adapter = new GaleriaAlunosAdapter(alunos,this);
        gallery.setAdapter(adapter);

    }
}
