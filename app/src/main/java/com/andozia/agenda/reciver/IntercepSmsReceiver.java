package com.andozia.agenda.reciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;


public class IntercepSmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();

        try{
            if (bundle != null){
                final Object[] pdusObjects = (Object[]) bundle.get("pdus");

                if (pdusObjects != null){
                    SmsMessage currentMessage;

                    for (Object apuds : pdusObjects){

                        if (Build.VERSION.SDK_INT >= 19){
                            SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
                            currentMessage = messages[0];
                        }else{
                            currentMessage = SmsMessage.createFromPdu((byte[]) apuds);
                        }

                        String message = currentMessage.getDisplayMessageBody();

                        String protocol[] = message.split(" ");
                        String banco = protocol[0].split("\\{")[1];
                        String cartao = protocol[3];
                        String finalCartao = protocol[6];
                        String valor = protocol[9];
                        String data = protocol[12];
                        String hora = protocol[14];

                        String estabelecimento = "";

                        for (int i = 15; i < protocol.length; i++){
                            if (i == protocol.length -1){
                                estabelecimento += protocol[i];
                            }else{
                                estabelecimento += protocol[i] + " ";
                            }
                        }

                        String res = String.format("%1$s Informa: Transação %2$s cartão final %3$s de R$ %4s aprovada em %5s as %6$s %7$s", banco, cartao, finalCartao, valor, data, hora, estabelecimento );

                        Log.i("SmsBroadcastReceiver", res );
                        Toast.makeText(context, res, Toast.LENGTH_LONG).show();

                    }
                }
            }

        }catch (Exception e){
            Log.e("SmsBroadcastReceiver", e.getMessage());
        }

    }
}
