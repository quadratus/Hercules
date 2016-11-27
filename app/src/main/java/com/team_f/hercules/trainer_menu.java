package com.team_f.hercules;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class trainer_menu extends AppCompatActivity {
    private static int SPLASH_DISPLAY_LENGTH = 3000;
    FirebaseDatabase db;
    DatabaseReference myref;
    Spinner sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.trainer_menu);
        db=FirebaseDatabase.getInstance();
        myref=db.getReference();
        sp=(Spinner)findViewById(R.id.spinner);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                SharedPreferences sharedPr = getSharedPreferences("mypref",MODE_PRIVATE);
                String checkMail = sharedPr.getString("email",null);

                if(checkMail != null && !checkMail.isEmpty()) {
                    Intent mainIntent = new Intent(trainer_menu.this, user_screen.class);
                    trainer_menu.this.startActivity(mainIntent);
                    trainer_menu.this.finish();
                }
                else {
                    Intent el = new Intent("com.xxyoxx.erevna.login");
                    startActivity(el);
                }
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
