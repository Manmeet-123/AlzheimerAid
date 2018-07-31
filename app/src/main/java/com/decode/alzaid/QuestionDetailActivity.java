package com.decode.alzaid;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.github.florent37.materialtextfield.MaterialTextField;

import java.util.List;
import java.util.Set;
import java.util.prefs.Preferences;

public class QuestionDetailActivity extends AppCompatActivity {

    private static final String TAG = "QuestionDetailActivity";

    Spinner spQuestions;
    EditText etAnswer, etOption1, etOption2, etOption3, etCustomQuestion;
    Button btnAddQuestion;
    MaterialTextField mt;
    String ques, ans, op1, op2, op3;

    SharedPreferences pf;
    Set<String> ms;
    List<String> ml;
    int cnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_detail);

        spQuestions = (Spinner) findViewById(R.id.sp_questions);
        etCustomQuestion = (EditText) findViewById(R.id.et_custom_question);
        etAnswer = (EditText) findViewById(R.id.et_answer);
        etOption1 = (EditText) findViewById(R.id.et_option1);
        etOption2 = (EditText) findViewById(R.id.et_option2);
        etOption3 = (EditText) findViewById(R.id.et_option3);
        btnAddQuestion = (Button) findViewById(R.id.btn_add_question);
        mt = (MaterialTextField)findViewById(R.id.et_custom_case);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.questions_array, android.R.layout.simple_spinner_item);
        spQuestions.setAdapter(adapter);
        pf = getSharedPreferences("Questions",MODE_PRIVATE);
        cnt = pf.getInt("Q",0);

        final String[] itm = getResources().getStringArray(R.array.questions_array);
        spQuestions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                //Object item = parent.getItemAtPosition(pos);
                ques = itm[pos];
                if(ques.equals("Custom Question")){
                    mt.setVisibility(View.VISIBLE);
                    etCustomQuestion.setVisibility(View.VISIBLE);
                    //ques = etCustomQuestion.getText().toString();
                    Log.d("custom",ques);
                }
                else{
                    etCustomQuestion.setVisibility(View.GONE);
                    mt.setVisibility(View.GONE);
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnAddQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ques.equals("Custom Question")){
                    ques = etCustomQuestion.getText().toString();
                    Log.d("custom",ques);
                }
                else{
                    ques = itm[spQuestions.getSelectedItemPosition()];
                }

                ans = etAnswer.getText().toString();
                op1 = etOption1.getText().toString();
                op2 = etOption2.getText().toString();
                op3 = etOption3.getText().toString();
                SharedPreferences.Editor ed = pf.edit();
                cnt++;
                ed.putInt(Integer.toString(cnt)+"_type",1);
                ed.putString(Integer.toString(cnt)+"_ans",ans);
                ed.putString(Integer.toString(cnt)+"_op4",ans);
                ed.putString(Integer.toString(cnt)+"_op1",op1);
                ed.putString(Integer.toString(cnt)+"_op2",op2);
                ed.putString(Integer.toString(cnt)+"_op3",op3);
                ed.putString(Integer.toString(cnt)+"_ques",ques);
                ed.putInt("Q",cnt);
                ed.commit();
                finish();
                Log.d(TAG, "onClick: "+ques+" "+ans+" "+op1+" "+op2+" "+op3);

            }
        });

    }
}
