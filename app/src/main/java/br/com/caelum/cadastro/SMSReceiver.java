package br.com.caelum.cadastro;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.util.Objects;

/**
 * Created by Bruna on 12/01/16.
 */
public class SMSReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context ctx, Intent data){

        Bundle bundle = data.getExtras();
        Object[] messages = (Object[]) bundle.get("pdus");
        byte[] pdu = (byte[]) messages[0];

        SmsMessage sms = SmsMessage.createFromPdu(pdu);
        String telefone = sms.getOriginatingAddress();
        Toast.makeText(ctx,"Chegou o SMS do telefone "+ telefone +" !!!",Toast.LENGTH_LONG).show();

        AlunoDAO dao = new AlunoDAO(ctx);

        if(dao.isAluno(telefone)){
            MediaPlayer mp = MediaPlayer.create(ctx,R.raw.msg);
            mp.start();
        }

    }
}
