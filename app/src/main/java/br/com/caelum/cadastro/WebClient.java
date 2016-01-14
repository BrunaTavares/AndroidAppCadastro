package br.com.caelum.cadastro;

import android.content.Entity;
import android.media.ExifInterface;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Bruna on 12/01/16.
 */
public class WebClient {
    public String post(String json){
        HttpPost post = new HttpPost("http://www.caelum.com.br/mobile");
        try {
            post.setEntity(new StringEntity(json));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        post.setHeader("Content-type","application/JSON");
        post.setHeader("Accept", "application/json");

        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = null;
        try {
            response = client. execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String jsonResposta = null;
        try {
            jsonResposta = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  jsonResposta;
        }
    }

