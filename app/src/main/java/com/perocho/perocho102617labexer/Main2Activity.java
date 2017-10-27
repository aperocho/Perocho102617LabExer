package com.perocho.perocho102617labexer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2Activity extends AppCompatActivity {

    Button btn_shared;
    Button btn_internal;
    Button btn_clr;
    Button btn_bck;
    TextView textView3;
    FileInputStream fis;
    BufferedReader br;
    String user, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_shared = (Button) findViewById(R.id.btn_shared);
        btn_internal = (Button) findViewById(R.id.btn_internal);
        btn_clr = (Button) findViewById(R.id.btn_clr);
        btn_bck = (Button) findViewById(R.id.btn_bck);
        textView3 = (TextView) findViewById(R.id.textView3);

    }

    public void btnBck (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void btnClr (View view) {
        textView3.setText("");
    }

    public void load(View view) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());;
        String user = preferences.getString("username","");
        String pwd = preferences.getString("password","");
        textView3.setText("The password of " + user + " is " + pwd);
    }

    public void store (View view) throws IOException {
        String read = "";
        try{
            fis = openFileInput("output.txt");
            br = new BufferedReader(new InputStreamReader(fis));
            if ((read = br.readLine()) != null)
                user = read;
            if ((read = br.readLine()) != null)
                pwd = read;
            fis.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        textView3.setText("The password of " + user + " is " + pwd);
    }
}
