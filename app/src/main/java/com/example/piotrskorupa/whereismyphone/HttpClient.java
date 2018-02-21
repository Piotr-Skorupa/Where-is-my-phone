package com.example.piotrskorupa.whereismyphone;

/**
 * Created by Piotr Skorupa on 2018-02-19.
 */

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.signocom.signomix.client.ClientException;
import com.signocom.signomix.client.SignomixClient;

import java.util.HashMap;


public class HttpClient extends AsyncTask<Void, Void, Void> {

    String url;

    public HttpClient(){



    }

    @Override
    protected Void doInBackground(Void... params) {
        url = "https://signomix.signocom.com";

        SignomixClient client = new SignomixClient(url, true, 1, "main");

        try {
            client.getSessionToken("/api/auth/", Form.getInstance().getLogin(), Form.getInstance().getPassword());
            HashMap<String, String> testparams = new HashMap<>();
            testparams.put("latitude", "0.0000");
            testparams.put("longitude", "0.0000");
            String result = client.sendData("/api/integration", Form.getInstance().getEui(), Form.getInstance().getSecret(), testparams);
            client.closeSession();

        }
        catch (Exception exception){
            Log.e("client-error:" , exception.toString());

        }

        return null;
    }

    public void send(double latitude, double longitude){

   }

}
