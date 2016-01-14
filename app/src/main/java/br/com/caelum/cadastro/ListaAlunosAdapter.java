package br.com.caelum.cadastro;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Bruna on 11/01/16.
 */
public class ListaAlunosAdapter extends BaseAdapter{

    private final List<Aluno> listaAlunos;
    private final Activity activity;

    public  ListaAlunosAdapter(Activity activity,List<Aluno> listaAlunos) {

        this.activity = activity;
        this.listaAlunos = listaAlunos;
        }

    @Override
    public View getView(int posicao, View convertview, ViewGroup parent){

        View v = activity.getLayoutInflater().inflate(R.layout.item_lista, parent,false);

        Aluno aluno = (Aluno) getItem(posicao);

        if (posicao % 2 == 0){
            v.setBackgroundColor(activity.getResources().getColor(R.color.linha_par));
        }else{
            v.setBackgroundColor(activity.getResources().getColor(R.color.linha_impar));
        }

        ImageView foto = (ImageView) v.findViewById(R.id.item_foto);
        Bitmap bm;

        if(aluno.getCaminhoFoto()!=null){
            bm = BitmapFactory.decodeFile(aluno.getCaminhoFoto());
        }else{
            bm = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_no_image);
        }
        bm = Bitmap.createScaledBitmap(bm,100,100,true);
        foto.setImageBitmap(bm);

        TextView nome = (TextView) v.findViewById(R.id.item_nome);
        nome.setText(aluno.getNome());

        return v;
    }

    @Override
    public long getItemId(int posicao){
        return listaAlunos.get(posicao).getId();
    }
    @Override
    public Object getItem(int posicao){
        return listaAlunos.get(posicao);
    }

    @Override
    public int getCount(){
        return listaAlunos.size();
    }


}
