package br.com.caelum.cadastro.fragment;



import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.dao.AlunoDAO;

import br.com.caelum.cadastro.modelo.Aluno;
import br.com.caelum.cadastro.util.AtualizadorDeLocalização;
import br.com.caelum.cadastro.util.Localizador;

/**
 * Created by Bruna on 15/01/16.
 */
public class MapaFragment extends SupportMapFragment {
    @Override
    public void onResume() {
        super.onResume();

        Localizador localizador = new Localizador(getActivity());
        //LatLng local = localizador.getCordenada("Rua Vergueiro 3185 Vila Mariana");
        //Log.i("MAPA","Coordenadas da Caelum: " + local);

        //this.centralizaNo(local);
        GoogleMap mapa = getMap();
        //mapa.addMarker(new MarkerOptions().title("Caellum").snippet("Ensino e Inovação").position(local));

        AlunoDAO dao = new AlunoDAO(getActivity());
        List<Aluno> alunos = dao.getLista();
        dao.close();

    //busca alunos no banco
        for(Aluno aluno: alunos){
            LatLng localAluno = localizador.getCordenada(aluno.getEndereco());
            if (localAluno!=null  ) {
                if(aluno.getCaminhoFoto()!= null ) {
                    Bitmap bm = BitmapFactory.decodeFile(aluno.getCaminhoFoto());
                    Bitmap bmReduzido = Bitmap.createScaledBitmap(bm, 100, 100, true);
                    BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(bmReduzido);
                    mapa.addMarker(new MarkerOptions().title(aluno.getNome()).snippet(aluno.getEndereco()).position(localAluno).icon(bitmapDescriptor));
                }else {
                    //Bitmap bm = BitmapFactory.decodeResource(R.drawable.ic_no_image);
                    //Bitmap bmReduzido = Bitmap.createScaledBitmap(bm, 100, 100, true);
                    BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.ic_no_image);
                    mapa.addMarker(new MarkerOptions().title(aluno.getNome()).snippet(aluno.getEndereco()).position(localAluno).icon(bitmapDescriptor));
                }
            }else{
                Toast.makeText(getActivity(),"Endereço:" + aluno.getEndereco() +" não localizado!",Toast.LENGTH_LONG).show();
            }
        }

        AtualizadorDeLocalização atualizador = new AtualizadorDeLocalização(getActivity(),this);


    }
    public void centralizaNo(LatLng local){
        CameraUpdate camera = CameraUpdateFactory.newLatLngZoom(local,20);
        GoogleMap mapa = getMap();
        mapa.moveCamera(camera);
        mapa.addMarker(new MarkerOptions().title("Local Agora").position(local).draggable(true));


    }
}
