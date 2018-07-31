package com.decode.alzaid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class QuizReport extends AppCompatActivity {

    TextView tvAc, tvTotal, tvPerc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_report);

        tvAc = (TextView) findViewById(R.id.tv_ac);
        tvTotal = (TextView) findViewById(R.id.tv_total);
        tvPerc = (TextView) findViewById(R.id.tv_perc);

        Intent intent = getIntent();
        Integer ac = intent.getIntExtra("Correct", 10);
        Integer total = intent.getIntExtra("Total", 10);
        Double perc = (1.0*ac*100)/total;

        tvAc.setText(ac.toString());
        tvTotal.setText(total.toString());
        tvPerc.setText(String.format("%.2f", perc));

    }
}
