package com.perocho.perocho102617labexer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static java.lang.System.lineSeparator;

public class MainActivity extends AppCompatActivity {

    EditText eText_pass;
    EditText eText_name;
    Button btn_shared;
    Button btn_internal;
    Button btn_next;
    FileOutputStream fos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eText_pass = (EditText) findViewById(R.id.eText_pass);
        eText_name = (EditText) findViewById(R.id.eText_name);
        btn_shared = (Button) findViewById(R.id.btn_shared);
        btn_internal = (Button) findViewById(R.id.btn_internal);
        btn_next = (Button) findViewById(R.id.btn_next);
    }

    public void shared (View view){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", eText_name.getText().toString());
        editor.putString("password", eText_pass.getText().toString());
        editor.commit();
        Toast.makeText(this, "Data Saved!", Toast.LENGTH_SHORT).show();

    }
    public void store (View view) {
        String username = eText_name.getText().toString();
        String space = ("\r\n");
        String password = eText_pass.getText().toString();
        try {
            fos = openFileOutput("output.txt", Context.MODE_PRIVATE);
            fos.write(username.getBytes());
            fos.write(space.getBytes());
            fos.write(password.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Message saved!", Toast.LENGTH_SHORT).show();
    }

    public void next (View view){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}

