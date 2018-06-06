package com.selfapps.dok.view.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import com.selfapps.dok.R;
import com.selfapps.dok.data.Communicator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;

import static com.selfapps.dok.data.NetworkConstants.POI_URL;

public class SplashActivity extends AppCompatActivity {
    Button clear, update;
    TextView container;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        container = (TextView) findViewById(R.id.tv_text);
        imageView = (ImageView) findViewById(R.id.img_test);
        update = (Button) findViewById(R.id.btn_update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLoad();
            }
        });
        clear = (Button) findViewById(R.id.btn_clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container.setText("");
            }
        });


    }

    private void doLoad() {

        MyAcyncTAsk tAsk = new MyAcyncTAsk();
        tAsk.execute();

        Communicator.loadImageFromCache(imageView,"f4250a29-2b9f-4986-bcaa-f41b1b65e5e0.jpg");

    }

    class MyAcyncTAsk extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            String res = "";
            String url = POI_URL;
            final String user = "kristirom29@gmail.com";
            final String password = "Ma-1-Tp-2";
            String userPass = user+":"+password;
            try {
                Authenticator.setDefault(new Authenticator(){
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user,password.toCharArray());
                    }});
                HttpURLConnection c = (HttpURLConnection) new URL(url).openConnection();
                c.setUseCaches(false);
                c.setRequestProperty("Authorization", "basic " +
                        Base64.encode(userPass.getBytes(), Base64.NO_WRAP));
                c.connect();
                String line;
                StringBuffer stringBuffer = new StringBuffer();
                BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
                while ((line = in.readLine()) != null) {
                    stringBuffer.append(line).append('\n');
                }
                res = stringBuffer.toString();


                in.close();
                c.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
                res = e.getMessage();
            }
            return res;
        }

        @Override
        protected void onPostExecute(String s) {
            container.setText(s);

        }
    }

}
