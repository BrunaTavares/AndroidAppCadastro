package br.com.caelum.cadastro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Bruna on 14/01/16.
 */
public class DetalhesProvaFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState){
      View layoutDetalhes = inflater.inflate(R.layout.fragment_detalhes_prova, container, false);
      return layoutDetalhes;
  }
}
