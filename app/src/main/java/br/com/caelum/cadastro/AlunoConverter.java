package br.com.caelum.cadastro;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

/**
 * Created by Bruna on 12/01/16.
 */
public class AlunoConverter {
    public String toJSON(List<Aluno> alunos) {
        JSONStringer jsonStringer = new JSONStringer();
        try {
            jsonStringer.object().key("list").array().object().key("aluno").array();
            for (Aluno aluno : alunos) {
                jsonStringer.object()
                        .key("id").value(aluno.getId())
                        .key("nome").value(aluno.getNome())
                        .key("telefone").value(aluno.getTelefone())
                        .key("endereco").value(aluno.getEndereco())
                        .key("site").value(aluno.getSite())
                        .key("nota").value(aluno.getNota())
                        .key("caminhoFoto").value(aluno.getCaminhoFoto())
                        .endObject();
            }
            jsonStringer.endArray().endObject().endArray().endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonStringer.toString();
    }
}
