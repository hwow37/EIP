package com.example.hk.eip.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hk.eip.R;
import com.example.hk.eip.database.DbOpenHelper;

import java.util.Random;

public class QuizActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "EIP_001";

    private ImageView img_ox;
    private TextView tv_quiz_meaning;
    private TextView tv_quiz_count;
    private Button btn_quiz[];

    private DbOpenHelper mDbOpenHelper;
    private Cursor tv_Cursor;
    private Random random;
    private SharedPreferences prefs;

    private int ox[];
    private int count;
    private int wordUnchecked;
    private boolean chk_overlap_name[];
    private boolean chk_overlap_btn[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Shares Preference 호출
        prefs = getSharedPreferences("EasyWordBook", MODE_PRIVATE);
        wordUnchecked = prefs.getInt("key_wordUnchecked",0);

        tv_quiz_meaning = (TextView)findViewById(R.id.tv_quiz_name);
        tv_quiz_meaning.setMovementMethod(new ScrollingMovementMethod());

        img_ox = (ImageView)findViewById(R.id.img_ox);

        tv_quiz_count = (TextView) findViewById(R.id.count_state);

        ox = new int[4];
        btn_quiz = new Button[4];
        btn_quiz[0] = (Button)findViewById(R.id.btn_quiz1);
        btn_quiz[1] = (Button)findViewById(R.id.btn_quiz2);
        btn_quiz[2] = (Button)findViewById(R.id.btn_quiz3);
        btn_quiz[3] = (Button)findViewById(R.id.btn_quiz4);
        Button btn_next_quiz = (Button)findViewById(R.id.btn_next_quiz);

        btn_quiz[0].setOnClickListener(this);
        btn_quiz[1].setOnClickListener(this);
        btn_quiz[2].setOnClickListener(this);
        btn_quiz[3].setOnClickListener(this);
        btn_next_quiz.setOnClickListener(this);

        // DB Create and Open
        mDbOpenHelper = new DbOpenHelper(this);
        mDbOpenHelper.open();

        tv_Cursor = null;
        tv_Cursor = mDbOpenHelper.getAllColumns();

        random = new Random();

        chk_overlap_name = new boolean[tv_Cursor.getCount()];
        chk_overlap_btn = new boolean[4];
        for(int i=0; i < tv_Cursor.getCount()-1; i++){
            chk_overlap_name[i] = false;
        }

        setWord();
        setBtn();
        count = 1;
        tv_quiz_count.setText(count+" / "+wordUnchecked);
    }

    public void setWord(){
        do{
            ox[0] = random.nextInt(tv_Cursor.getCount());
            tv_Cursor.moveToPosition(ox[0]);
        }while(chk_overlap_name[ox[0]] || tv_Cursor.getInt(tv_Cursor.getColumnIndex("check_word"))!=0);
        tv_quiz_meaning.setText(tv_Cursor.getString(tv_Cursor.getColumnIndex("meaning")));
        tv_quiz_meaning.setTextSize(15);
        chk_overlap_name[ox[0]] = true;
    }

    public void setBtn(){
        int posi;
        for(int i=0; i < 4; i++){
            chk_overlap_btn[i] = false;
        }

        do{
            ox[1] = random.nextInt(tv_Cursor.getCount());
        }while(ox[1]==ox[0]);
        do{
            ox[2] = random.nextInt(tv_Cursor.getCount());
        }while(ox[2]==ox[0] || ox[2]==ox[1]);
        do{
            ox[3] = random.nextInt(tv_Cursor.getCount());
        }while(ox[3] == ox[0] || ox[3]==ox[1] || ox[3]==ox[2]);

        for(int i=0; i < 4; i++){
            do{
                posi = random.nextInt(4);
            }while(chk_overlap_btn[posi]);

            tv_Cursor.moveToPosition(ox[i]);
            btn_quiz[posi].setText(tv_Cursor.getString(tv_Cursor.getColumnIndex("name")));
            chk_overlap_btn[posi] = true;
        }
    }

    public void chk_ox(String chk_name){
        tv_Cursor.moveToPosition(ox[0]);

        if(tv_Cursor.getString(tv_Cursor.getColumnIndex("name")).equals(chk_name)){
            Log.i("onClick", "oButton");
            img_ox.setImageResource(R.drawable.img_o);
        }else{
            Log.i("onClick", "xButton");
            img_ox.setImageResource(R.drawable.img_x);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tv_Cursor.close();
        mDbOpenHelper.close();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_quiz1:
                chk_ox((String) btn_quiz[0].getText());
                break;

            case R.id.btn_quiz2:
                chk_ox((String) btn_quiz[1].getText());
                break;

            case R.id.btn_quiz3:
                chk_ox((String) btn_quiz[2].getText());
                break;

            case R.id.btn_quiz4:
                chk_ox((String) btn_quiz[3].getText());
                break;

            case R.id.btn_next_quiz:
                Log.i("onClick", "NextButton");
                Log.i("onClick", count + "//" + wordUnchecked);
                if(count > wordUnchecked-1){
                    Toast toast = Toast.makeText(QuizActivity.this, "마지막 문항입니다.", Toast.LENGTH_SHORT );
                    toast.show();
                    break;
                }

                img_ox.setImageResource(0);
                setWord();
                setBtn();
                count = count+1;
                tv_quiz_count.setText(count+" / "+wordUnchecked);
                break;
        }
    }
}
