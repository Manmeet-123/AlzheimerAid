package com.decode.alzaid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.soundcloud.android.crop.Crop;

import java.io.File;

import static java.lang.Math.abs;

public class ImageQuestionActivity extends AppCompatActivity {

    private static final String TAG = "ImageQuestionActivity";

    Button addImage, addQuestioni;
    ImageView ivImage;
    EditText etAnsi, etOp1i, etOp2i, etOp3i;
    String ans, op1, op2, op3;
    SharedPreferences pf;
    int cnt;
    public static final int PICK_IMAGE_REQUEST = 51;
    Uri destination;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_question);

        addImage = (Button) findViewById(R.id.btn_add_image);
        addQuestioni = (Button) findViewById(R.id.btn_add_questioni);
        ivImage = (ImageView) findViewById(R.id.iv_image);
        etAnsi = (EditText) findViewById(R.id.et_answeri);
        etOp1i = (EditText) findViewById(R.id.et_option1i);
        etOp2i = (EditText) findViewById(R.id.et_option2i);
        etOp3i = (EditText) findViewById(R.id.et_option3i);

        pf = getSharedPreferences("Questions",MODE_PRIVATE);
        cnt = pf.getInt("Q",0);

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

            }
        });

        addQuestioni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ans = etAnsi.getText().toString();
                op1 = etOp1i.getText().toString();
                op2 = etOp2i.getText().toString();
                op3 = etOp3i.getText().toString();

                SharedPreferences.Editor ed = pf.edit();
                cnt++;
                ed.putInt(Integer.toString(cnt)+"_type",2);
                ed.putString(Integer.toString(cnt)+"_ans",ans);
                ed.putString(Integer.toString(cnt)+"_op4",ans);
                ed.putString(Integer.toString(cnt)+"_op1",op1);
                ed.putString(Integer.toString(cnt)+"_op2",op2);
                ed.putString(Integer.toString(cnt)+"_op3",op3);
                ed.putString(Integer.toString(cnt)+"_ques",destination.toString());
                ed.putInt("Q",cnt);
                ed.commit();
                finish();

                Log.d(TAG, "onClick: "+ans+" "+op1+" "+op2+" "+op3  );
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            uri = data.getData();

            Log.d(TAG, "onActivityResult: " + uri.hashCode());
            destination = Uri.fromFile(new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "cropped" + uri.hashCode()));
            Log.d(TAG, "onActivityResult: "+destination);
            Crop.of(uri, destination).asSquare().start(this);
           ivImage.setVisibility(View.VISIBLE);

            Glide.with(this).load(destination).fitCenter().into(ivImage);

        }


    }
}
