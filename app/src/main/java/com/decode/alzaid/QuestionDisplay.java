package com.decode.alzaid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Random;

public class QuestionDisplay extends AppCompatActivity {


    int cnt;
    SharedPreferences pf;
    String opr1,opr2,opr3,opr4,ans,ques,uri;
    int type;
    int selected;
    Bitmap bmp;
    String selans;
    int q;
    int tot,r,w;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_display);

        Toast welcomeMsg = Toast.makeText(getApplicationContext(), "Let's get started!", Toast.LENGTH_LONG);
        welcomeMsg.setGravity(Gravity.CENTER, 0, 0);
        welcomeMsg.show();

        pf = getSharedPreferences("Questions",MODE_PRIVATE);
        cnt = pf.getInt("Q",0);

        final TextView questText = (TextView) findViewById(R.id.questionText);

        final ImageView questImage = (ImageView) findViewById(R.id.questionImage);

        final Button op1 = (Button) findViewById(R.id.button1);
        final Button op2 = (Button) findViewById(R.id.button2);
        final Button op3 = (Button) findViewById(R.id.button3);
        final Button op4 = (Button) findViewById(R.id.button4);

        op1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                op1.setBackgroundColor(getResources().getColor(R.color.button_checked));
                op2.setBackgroundColor(getResources().getColor(R.color.button_unchecked));
                op3.setBackgroundColor(getResources().getColor(R.color.button_unchecked));
                op4.setBackgroundColor(getResources().getColor(R.color.button_unchecked));
                selected = 1;
            }
        });

        op2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                op1.setBackgroundColor(getResources().getColor(R.color.button_unchecked));
                op2.setBackgroundColor(getResources().getColor(R.color.button_checked));
                op3.setBackgroundColor(getResources().getColor(R.color.button_unchecked));
                op4.setBackgroundColor(getResources().getColor(R.color.button_unchecked));
                selected = 2;
            }
        });

        op3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                op1.setBackgroundColor(getResources().getColor(R.color.button_unchecked));
                op2.setBackgroundColor(getResources().getColor(R.color.button_unchecked));
                op3.setBackgroundColor(getResources().getColor(R.color.button_checked));
                op4.setBackgroundColor(getResources().getColor(R.color.button_unchecked));
                selected = 3;
            }
        });

        op4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                op1.setBackgroundColor(getResources().getColor(R.color.button_unchecked));
                op2.setBackgroundColor(getResources().getColor(R.color.button_unchecked));
                op3.setBackgroundColor(getResources().getColor(R.color.button_unchecked));
                op4.setBackgroundColor(getResources().getColor(R.color.button_checked));
                selected = 4;
            }
        });

        final Button submitBtn = (Button) findViewById(R.id.submitButton);

        final ArrayList<String> array = new ArrayList<>();
        for(int i=1;i<=cnt;i++){
            array.add(Integer.toString(i));
        }

        Log.d("Quiz",Integer.toString(cnt));
            final Random rnd = new Random();
            q = rnd.nextInt(cnt-1)+1;

            int opr = rnd.nextInt(3);
            opr1 = pf.getString(array.get(q)+"_op"+Integer.toString((opr+1)%4+1),"null");
            opr2 = pf.getString(array.get(q)+"_op"+Integer.toString((opr+2)%4+1),"null");
            opr3 = pf.getString(array.get(q)+"_op"+Integer.toString((opr+3)%4+1),"null");
            opr4 = pf.getString(array.get(q)+"_op"+Integer.toString((opr+4)%4+1),"null");
            op1.setText(opr1);
            op2.setText(opr2);
            op3.setText(opr3);
            op4.setText(opr4);
            ans = pf.getString(array.get(q)+"_ans","null");
            ques = pf.getString(array.get(q)+"_ques","null");
            type = pf.getInt(array.get(q)+"_type",1);
            array.remove(q);
            cnt--;
            if(type == 1){
                questText.setText(ques);
                questText.setVisibility(View.VISIBLE);
                questImage.setVisibility(View.GONE);
            }
            if(type == 2){
                Glide.with(getApplicationContext()).load(Uri.parse(ques)).into(questImage);
                questText.setVisibility(View.GONE);
                questImage.setVisibility(View.VISIBLE);
            }
        selected = 0;
        tot=0;r=0;w=0;
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(submitBtn.getText().toString().equals("Submit")){
                    tot++;
                    switch(selected) {
                        case 1:
                            selans = op1.getText().toString();
                            submitBtn.setText("Continue");
                            if(selans.equals(ans)){
                                op1.setBackgroundColor(getResources().getColor(R.color.Green));
                                r++;
                            }
                            else{
                                op1.setBackgroundColor(getResources().getColor(R.color.authui_colorPrimaryDark));
                                w++;
                            }
                            break;
                        case 2:
                            selans = op2.getText().toString();
                            submitBtn.setText("Continue");
                            if(selans.equals(ans)){
                                op2.setBackgroundColor(getResources().getColor(R.color.Green));
                                r++;
                            }
                            else{
                                op2.setBackgroundColor(getResources().getColor(R.color.authui_colorPrimaryDark));
                                w++;
                            }
                            break;
                        case 3:
                            selans = op3.getText().toString();
                            submitBtn.setText("Continue");
                            if(selans.equals(ans)){
                                op3.setBackgroundColor(getResources().getColor(R.color.Green));
                                r++;
                            }
                            else{
                                op3.setBackgroundColor(getResources().getColor(R.color.authui_colorPrimaryDark));
                                w++;
                            }
                            break;
                        case 4:
                            selans = op4.getText().toString();
                            submitBtn.setText("Continue");
                            if(selans.equals(ans)){
                                op4.setBackgroundColor(getResources().getColor(R.color.Green));
                                r++;
                            }
                            else{
                                op4.setBackgroundColor(getResources().getColor(R.color.authui_colorPrimaryDark));
                                w++;
                            }
                            break;
                        case 0:
                            Toast.makeText(getApplicationContext(), "Error, Pick an answer!", Toast.LENGTH_LONG).show();
                            break;
                    }
                    if(selans.equals(ans)){

                        r++;
                    }
                    else{
                        w++;
                    }
                }
                else{
                    if(tot == 3){
                        Intent intent = new Intent(QuestionDisplay.this,QuizReport.class);
                        intent.putExtra("Correct",r);
                        intent.putExtra("Total",tot);
                        startActivity(intent);
                        finish();
                    }
                    q = rnd.nextInt(cnt-1)+1;

                    int opr = rnd.nextInt(3);
                    opr1 = pf.getString(array.get(q)+"_op"+Integer.toString((opr+1)%4+1),"null");
                    opr2 = pf.getString(array.get(q)+"_op"+Integer.toString((opr+2)%4+1),"null");
                    opr3 = pf.getString(array.get(q)+"_op"+Integer.toString((opr+3)%4+1),"null");
                    opr4 = pf.getString(array.get(q)+"_op"+Integer.toString((opr+4)%4+1),"null");
                    op1.setText(opr1);
                    op2.setText(opr2);
                    op3.setText(opr3);
                    op4.setText(opr4);
                    ans = pf.getString(array.get(q)+"_ans","null");
                    ques = pf.getString(array.get(q)+"_ques","null");
                    type = pf.getInt(array.get(q)+"_type",1);
                    array.remove(q);
                    cnt--;
                    if(type == 1){
                        questText.setText(ques);
                        questText.setVisibility(View.VISIBLE);
                        questImage.setVisibility(View.GONE);
                    }
                    if(type == 2){
                        Glide.with(getApplicationContext()).load(Uri.parse(ques)).into(questImage);
                        questText.setVisibility(View.GONE);
                        questImage.setVisibility(View.VISIBLE);
                    }
                    op1.setBackgroundColor(getResources().getColor(R.color.button_unchecked));
                    op2.setBackgroundColor(getResources().getColor(R.color.button_unchecked));
                    op3.setBackgroundColor(getResources().getColor(R.color.button_unchecked));
                    op4.setBackgroundColor(getResources().getColor(R.color.button_unchecked));
                    selected = 0;
                    submitBtn.setText("Submit");

                }
            }
        });


    }
}
