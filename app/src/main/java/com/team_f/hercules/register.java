package com.team_f.hercules;

import android.content.Intent;
import android.nfc.Tag;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.io.InputStream;

public class register extends AppCompatActivity {

    EditText name, password, address, mail,weight,height;
    RadioGroup sex,activity;
    Button register;
    InputStream is;
    String tag = "Error";
    private static final String TAG = "MainActivity";

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDb;
    private DatabaseReference rootRef;
    private User u= new User();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        StrictMode.ThreadPolicy tp = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(tp);

        mAuth = FirebaseAuth.getInstance();
        mDb = FirebaseDatabase.getInstance();
        rootRef = mDb.getReference();

        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.pass);
        address = (EditText) findViewById(R.id.addr);
        mail = (EditText) findViewById(R.id.email);
        register = (Button) findViewById(R.id.reg);
        weight = (EditText)findViewById(R.id.weight) ;
        height = (EditText)findViewById(R.id.height);

        sex = (RadioGroup) findViewById(R.id.rg);
        activity=(RadioGroup)findViewById(R.id.gen);

    }

    public void reg(View v){

    }
}
