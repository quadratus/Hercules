package com.team_f.hercules;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
EditText t1,t2;
    Button b1,b2;
    String s1,s2;
    private FirebaseAuth mAuth;
// ...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=(EditText)findViewById(R.id.email);
        t2=(EditText)findViewById(R.id.pass);
        mAuth = FirebaseAuth.getInstance();
        b1=(Button)findViewById(R.id.b1);
    }

    @Override
    public void onClick(View view) {
        s1=t1.getText().toString();
        s2=t2.getText().toString();
        mAuth.signInWithEmailAndPassword(s1,s2)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, " login failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Intent in = new Intent("com.team_f.hercules.user_screen");
                            startActivity(in);
                            finish();

                        }

                        // ...
                    }
                });

    }

    public void reg(View v){
        Intent reg = new Intent("com.team_f.hercules.register");
        startActivity(reg);
    }
}
