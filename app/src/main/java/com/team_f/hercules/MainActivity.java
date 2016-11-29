package com.team_f.hercules;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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
    private static final String TAG = "MainActivity";
// ...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=(EditText)findViewById(R.id.email);
        t2=(EditText)findViewById(R.id.pass1);
        mAuth = FirebaseAuth.getInstance();
        b1=(Button)findViewById(R.id.b1);
        b2 = (Button)findViewById(R.id.trainer);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trainer(t1.getText().toString(),t2.getText().toString());
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trainee(t1.getText().toString(),t2.getText().toString());
            }
        });
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

    public void trainer(String email,String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(MainActivity.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }
                        Intent in = new Intent("com.team_f.hercules.trainer_menu");
                        startActivity(in);

                        // ...
                    }
                });
    }

    public void trainee(String email,String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(MainActivity.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }
                        Intent inw = new Intent("com.team_f.hercules.user_screen");
                        startActivity(inw);
                        // ...
                    }
                });
    }
}
