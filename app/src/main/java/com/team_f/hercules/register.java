package com.team_f.hercules;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.InputStream;

public class register extends AppCompatActivity {

    EditText name, password, address, mail;
    RadioGroup sex;
    Button register;
    InputStream is;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.pass);
        address = (EditText) findViewById(R.id.addr);
        mail = (EditText) findViewById(R.id.email);
        register = (Button) findViewById(R.id.reg);

        sex = (RadioGroup) findViewById(R.id.rg);


        //TODO Separate from onCreate.

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (register.isPressed()) {
                    int r = sex.getCheckedRadioButtonId();
                    RadioButton rb = (RadioButton) findViewById(r);
                    String gender = rb.getText().toString();
                    String uname = name.getText().toString();
                    String pass = password.getText().toString();
                    String addr = address.getText().toString();
                    String eaddr = mail.getText().toString();
                }
            }
        });
    }
}
