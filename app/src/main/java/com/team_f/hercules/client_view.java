package com.team_f.hercules;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class client_view extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private TextView email,addresss,heightt,weightt,activityy,agee,genderr,uname;
    private String mCase_key = null;
    private String name,email_put;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_view);
        StrictMode.ThreadPolicy tp = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(tp);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Hercules");
        mCase_key = getIntent().getExtras().getString("case_id");

        email = (TextView)findViewById(R.id.email);
        addresss = (TextView)findViewById(R.id.address);
        heightt = (TextView)findViewById(R.id.height);
        agee = (TextView)findViewById(R.id.age_display);
        weightt = (TextView)findViewById(R.id.weight);
        activityy = (TextView)findViewById(R.id.activity);
        uname= (TextView)findViewById(R.id.user_name);
        genderr = (TextView)findViewById(R.id.gender);


        mDatabase.child(mCase_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name = (String) dataSnapshot.child("Name").getValue();
                email_put = (String) dataSnapshot.child("Email").getValue();
                String height = (String) dataSnapshot.child("Height").getValue();
                String weight = (String) dataSnapshot.child("Weight").getValue();
                String gender = (String) dataSnapshot.child("Gender").getValue();
                String age = (String) dataSnapshot.child("Age").getValue();
                String address = (String)dataSnapshot.child("Address").getValue();
                String activity_level = (String)dataSnapshot.child("Activity").getValue();


                uname.setText(name);
                email.setText(email_put);
                addresss.setText(address);
                heightt.setText(height);
                weightt.setText(weight);
                activityy.setText(activity_level);
                genderr.setText(gender);
                agee.setText(age);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
