package br.com.caelum.cadastro.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import br.com.caelum.cadastro.Aluno;
import br.com.caelum.cadastro.AlunoConverter;
import br.com.caelum.cadastro.AlunoDAO;
import br.com.caelum.cadastro.WebClient;

/**
 * Created by Bruna  on 13/01/16.
 */
public class EnviaAlunosTask extends AsyncTask<Object, Object, String>{

    private Context context;
    private ProgressDialog pd;

    public EnviaAlunosTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Object... params) {
        //busca a lista alunos
        AlunoDAO dao = new AlunoDAO(context);
        List<Aluno> alunos = dao.getLista();
        dao.close();
        //converte para JSON
        String json = new AlunoConverter().toJSON(alunos);
        //envia para a Caelum
        WebClient client = new WebClient();
        String resp = client.post(json);
        return resp;
     }

    @Override
    protected void onPreExecute() {
        pd = ProgressDialog.show(context,"Aguarde...","Envio de dados para a web", true, true);
     }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        pd.dismiss();
    }
}
