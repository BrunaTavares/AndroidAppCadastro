package br.com.caelum.cadastro;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.caelum.cadastro.task.EnviaAlunosTask;


public class ListaAlunosActivity extends ActionBarActivity {

    private ListView lista;
    private List<Aluno> alunos;

    public void carregaLista(){

        AlunoDAO dao = new AlunoDAO(this);
        this.alunos = dao.getLista();
        dao.close();

        //ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this,android.R.layout.simple_list_item_1,alunos);
        ListaAlunosAdapter adapter = new ListaAlunosAdapter(this,alunos);
        this.lista.setAdapter(adapter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        this.lista = (ListView) findViewById(R.id.lista_alunos);

        registerForContextMenu(lista);
        //String[] alunos = {"Clark Kent", "Peter Parker", "Bruce Wayne","João","Maria","Marta","Bruna","Fernando","Andre","Fabio","Paulo","Wonder Woman"};
         //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,alunos);
        //lista.setAdapter(adapter);
        //quero clicar e saber em quem foi clicado

        lista.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    //adapter view é o contexto do cara que foi clicado é um item de quem? da lista que é o pai
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // ele retorna um object que é do tipo genérico
                        Aluno alunoSelecionado = (Aluno) parent.getItemAtPosition(position);
                        Intent intent = new Intent(ListaAlunosActivity.this,FormularioActivity.class);
                        intent.putExtra("alunoSelecionado",alunoSelecionado);
                        startActivity(intent);
                        //Toast.makeText(ListaAlunosActivity.this, "Item Clicado" + alunoSelecionado.getNome(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        lista.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        //retorna o objeto que estava na lista mas agora é do tipo Aluno
                        Aluno aluno = (Aluno) parent.getItemAtPosition(position);
                        //Toast.makeText(ListaAlunosActivity.this, "Aluno Selecionado: " + aluno.getNome(), Toast.LENGTH_LONG).show();
                        return false;
                    }
                }
        );

        Button inserir = (Button) findViewById(R.id.lista_alunos_floating_button);

        inserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(ListaAlunosActivity.this,"Inserir Clicado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListaAlunosActivity.this,FormularioActivity.class);
                startActivity(intent);
                }
        } );


    }
    @Override
    public void onResume(){
        super.onResume();
        carregaLista();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_alunos, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_enviar_notas:
                new EnviaAlunosTask(this).execute();
                return true;
            case R.id.menu_receber_provas:
                Intent provas = new Intent(this,ProvasActivity.class);
                startActivity(provas);
                return true;
        }


          //  case R.id.menu_ok:
            //    Toast.makeText(ListaAlunosActivity.this,"Menu Item OK",Toast.LENGTH_LONG).show();
        //}


        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Aluno alunoSelecionado = (Aluno) lista.getAdapter().getItem(info.position);

        MenuItem ligar = menu.add("Ligar"); //chama o discador passa o telefone
        Intent intentLigar = new Intent(Intent.ACTION_DIAL);
        intentLigar.setData(Uri.parse("tel: "+alunoSelecionado.getTelefone()));
        ligar.setIntent(intentLigar);

        MenuItem sms = menu.add("Enviar SMS"); //chama o mensagem e passa o telefone e uma mensagem padrao
        Intent intentSms = new Intent(Intent.ACTION_VIEW);
        intentSms.setData(Uri.parse("sms: "+alunoSelecionado.getTelefone()));
        String mensagem = "Ola, " + alunoSelecionado.getNome() + "\n" +
                "Seja Bem Vindo!";
        intentSms.putExtra("sms_body",mensagem);
        sms.setIntent(intentSms);

        MenuItem mapa = menu.add("Achar no Mapa");
        Intent intentMapa = new Intent(Intent.ACTION_VIEW);
        intentMapa.setData(Uri.parse("geo:0,0?z=14&q="+Uri.encode(alunoSelecionado.getEndereco())));
        mapa.setIntent(intentMapa);
        

        MenuItem navegar= menu.add("Navegar no site");
        Intent intentSite = new Intent(Intent.ACTION_VIEW);
        intentSite.setData(Uri.parse("http:"+alunoSelecionado.getSite()));
        navegar.setIntent(intentSite);

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AlunoDAO dao = new AlunoDAO(ListaAlunosActivity.this);
                dao.deletar(alunoSelecionado);
                dao.close();
                carregaLista();
                return false;
            }
        });



    }


    }
