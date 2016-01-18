package br.com.caelum.cadastro.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by bruna on 17/01/16.
 */
public class GaleriaAlunosAdapter extends PagerAdapter{

    private List<Aluno> alunos;
    private Activity activity;

    public GaleriaAlunosAdapter(List<Aluno> alunos, Activity activity) {
        this.alunos = alunos;
        this.activity = activity;
    }


    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView foto = new ImageView(activity);
        Aluno aluno = alunos.get(position);

        if(aluno.getCaminhoFoto() != null){
            Bitmap imagem = BitmapFactory.decodeFile(aluno.getCaminhoFoto());
            foto.setImageBitmap(Bitmap.createScaledBitmap(imagem, 200, 200, true));
        }else {
            foto.setImageResource(R.drawable.ic_no_image);
        }
        ((ViewPager) container).addView(foto,0);
        return foto;
    }

}
