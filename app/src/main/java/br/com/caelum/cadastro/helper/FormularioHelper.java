package br.com.caelum.cadastro.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import br.com.caelum.cadastro.modelo.Aluno;
import br.com.caelum.cadastro.FormularioActivity;
import br.com.caelum.cadastro.R;

/**
 * Created by Bruna on 06/01/16.
 */
public class FormularioHelper {

    private EditText nome;
    private EditText telefone;
    private EditText endereco;
    private EditText site;
    private RatingBar nota;
    private Button botaoFoto;
    private ImageView foto;


    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity){

        nome = (EditText) activity.findViewById(R.id.formulario_nome);
        telefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        endereco = (EditText) activity.findViewById(R.id.formulario_endereco);
        site = (EditText) activity.findViewById(R.id.formulario_site);
        nota = (RatingBar)activity.findViewById(R.id.formulario_nota);
        botaoFoto = (Button) activity.findViewById(R.id.formulario_botao_foto);
        foto = (ImageView) activity.findViewById(R.id.formulario_foto);

        aluno = new Aluno();
    }
    public Aluno pegaAlunoDoFormulario(){


        aluno.setNome(nome.getText().toString());
        aluno.setTelefone(telefone.getText().toString());
        aluno.setEndereco(endereco.getText().toString());
        aluno.setSite(site.getText().toString());
        aluno.setNota(Double.valueOf(nota.getProgress()));
        aluno.setCaminhoFoto((String) foto.getTag());

        return aluno;
    }

    public void  insereDadosNoFormulario(Aluno aluno){


        nome.setText(aluno.getNome());
        telefone.setText(aluno.getTelefone());
        endereco.setText(aluno.getEndereco());
        site.setText(aluno.getSite());
        nota.setProgress(aluno.getNota().intValue());
        if (aluno.getCaminhoFoto()!= null){
            carregaImagem(aluno.getCaminhoFoto());
        }

        this.aluno = aluno;

    }


    public boolean temNome(){
        return !nome.getText().toString().isEmpty();
    }
    public void mostraError(){
        nome.setError("Campo não pode ser vazio!");
    }



   public void carregaImagem(String localFoto){
       Bitmap bm = BitmapFactory.decodeFile(localFoto);
       Bitmap bmReduzido = Bitmap.createScaledBitmap(bm,400,300,true);

       //ExifInterface exif = new ExifInterface(bm);
       //int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
       //System.out.print(orientation);
       foto.setImageBitmap(bmReduzido);
       foto.setScaleType(ImageView.ScaleType.FIT_XY);
       foto.setTag(localFoto);

   }

    public Button getBotaoFoto() {
        return botaoFoto;
    }


}
