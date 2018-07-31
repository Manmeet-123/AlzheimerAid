package com.decode.alzaid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QuestionActivity extends AppCompatActivity {

    Button btnText, btnImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        btnText = (Button) findViewById(R.id.btn_text);
        btnImage = (Button) findViewById(R.id.btn_image);

        btnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(QuestionActivity.this, QuestionDetailActivity.class);
                startActivity(i);
            }
        });

        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(QuestionActivity.this, ImageQuestionActivity.class);
                startActivity(i);
            }
        });
    }
}
