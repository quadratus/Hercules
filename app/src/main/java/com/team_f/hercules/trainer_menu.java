package com.team_f.hercules;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.ui.database.*;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class trainer_menu extends Activity {
    private RecyclerView mList;
    private DatabaseReference mDatabase;

    private static int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.trainer_menu);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Hercules");
        mList = (RecyclerView) findViewById(R.id.list);
        mList.setHasFixedSize(true);
        mList.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<blog, BlogViewHolder> tb = new FirebaseRecyclerAdapter<blog, BlogViewHolder>(
                blog.class,
                R.layout.list_row,
                BlogViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, blog model, int position) {
                final String post_key = getRef(position).getKey();
                viewHolder.setTitle(model.getName());
                viewHolder.setDesc(model.getEmail());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent client = new Intent(trainer_menu.this,client_view.class);
                        startActivity(client);
                    }
                });

            }
        };

        mList.setAdapter(tb);


    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public BlogViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setTitle(String title) {
            TextView card_title = (TextView) mView.findViewById(R.id.post_title);
        }

        public void setDesc(String desc) {
            TextView post_desc = (TextView) mView.findViewById(R.id.post_text);
            post_desc.setText(desc);
        }


    }
}
