package br.com.caelum.cadastro;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.File;

import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.helper.FormularioHelper;
import br.com.caelum.cadastro.modelo.Aluno;


public class FormularioActivity extends ActionBarActivity {

    private FormularioHelper helper;
    //numero definido para a intent de tirar foto
    private int tira_foto = 1;
    private String localArquivoFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("alunoSelecionado");
        helper = new FormularioHelper(this);
        if (aluno != null){

            helper.insereDadosNoFormulario(aluno);
             }

        Button foto = helper.getBotaoFoto();
        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localArquivoFoto = getExternalFilesDir(null)+"/"+System.currentTimeMillis()+".jpg";
                //converte para o tipo Uri
                Uri uriFoto = Uri.fromFile(new File(localArquivoFoto));
                //intent anonimo para chamar a camera
                Intent irParaCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                irParaCamera.putExtra(MediaStore.EXTRA_OUTPUT,uriFoto);
                //activity com retorno de intent
                startActivityForResult(irParaCamera,tira_foto);
            }
        });
        /*Button salvar = (Button) findViewById(R.id.formulario_botaoSalvar);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FormularioActivity.this,"Você Salvou",Toast.LENGTH_LONG).show();
                finish();
            }
        });*/

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == tira_foto){
            if(resultCode== Activity.RESULT_OK){
                helper.carregaImagem(localArquivoFoto);
            }else{
                localArquivoFoto=null;
            }

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_formulario, menu);
        //return true é exibido o menu
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()) {
            case R.id.menu_formulario_ok:

                Aluno aluno = helper.pegaAlunoDoFormulario();
                AlunoDAO dao = new AlunoDAO(this);

                if (helper.temNome()){
                    if(aluno.getId()!= null){
                        dao.atualiza(aluno);
                    }else {
                        dao.insere(aluno);
                       }
                    dao.close();
                }else {
                    helper.mostraError();
                }
                //Toast.makeText(FormularioActivity.this,"Objeto aluno criado: " + aluno.getNome(),Toast.LENGTH_LONG).show();
                //Volta para a outra tela
                finish();
                return false;
       default:
           return super.onOptionsItemSelected(item);
       }


    }


}
