package com.team_f.hercules;

import android.app.ProgressDialog;
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

    EditText name, password, address, mail, weight, height, age;
    RadioGroup sex, activity;
    Button register;
    InputStream is;
    String tag = "Error";
    String acti, gendres;
    private static final String TAG = "MainActivity";

    private DatabaseReference mDataBase;

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDb;
    private User u = new User();
    private ProgressDialog mProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        StrictMode.ThreadPolicy tp = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(tp);

        mAuth = FirebaseAuth.getInstance();
        mDb = FirebaseDatabase.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference().child("Hercules");

        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.pass);
        address = (EditText) findViewById(R.id.addr);
        mail = (EditText) findViewById(R.id.email);
        register = (Button) findViewById(R.id.reg);
        weight = (EditText) findViewById(R.id.weight);
        height = (EditText) findViewById(R.id.height);
        age = (EditText) findViewById(R.id.age_input);

        sex = (RadioGroup) findViewById(R.id.rg);
        activity = (RadioGroup) findViewById(R.id.gen);

        int s = sex.getCheckedRadioButtonId();
        int a = activity.getCheckedRadioButtonId();

        final RadioButton sexx = (RadioButton) findViewById(s);
        final RadioButton act = (RadioButton) findViewById(a);

        gendres = sexx.getText().toString();
        acti = act.getText().toString();


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reg(mail.getText().toString(), password.getText().toString(), address.getText().toString(),
                        weight.getText().toString(), height.getText().toString(), gendres, acti, age.getText().toString());
            }
        });
    }

    public void reg(final String email, final String pass, final String address, final String weight, final
    String height, final String gender, final String activity, final String age) {

        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(register.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        } else {

                            DatabaseReference pushData = mDataBase.push();
                            pushData.child("Email").setValue(email);
                            pushData.child("Addresss").setValue(address);
                            pushData.child("Weight").setValue(weight);
                            pushData.child("Height").setValue(height);
                            pushData.child("Gender").setValue(gender);
                            pushData.child("Activity").setValue(activity);
                            pushData.child("Age").setValue(age);
                        /*Intent in = new Intent("com.team_f.hercules.MainActivity");
                        startActivity(in);*/
                        }


                        // ...
                    }
                });

    }
}
