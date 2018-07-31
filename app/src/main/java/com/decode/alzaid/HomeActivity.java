package com.decode.alzaid;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    String[] itemList = {"Home","Call Home","New Question","Settings"};
    private RecyclerView recyclerView;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference dataref = FirebaseDatabase.getInstance().getReference().child(user.getUid());

    Button sos;
    Button quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataref.setValue("huehuehue");
        setContentView(R.layout.activity_home);
        List<String> st = Arrays.asList(itemList);
        recyclerView = (RecyclerView) findViewById(R.id.home_recycler_view);
        sos = (Button)findViewById(R.id.home_sos);
        quiz = (Button)findViewById(R.id.home_quiz);

        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,QuestionDisplay.class);
                startActivity(intent);
            }
        });

        sos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("sms:" + "0123456789"));
                sendIntent.putExtra("sms_body", "SOS! - Lat : 28.7502331 , Long : 77.1141703");
                startActivity(sendIntent);

            }
        });

        RecyclerView.Adapter mAdapter = new HomeAdapter(st,getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);


    }
}
