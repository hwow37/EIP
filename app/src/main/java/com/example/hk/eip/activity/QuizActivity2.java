package com.example.hk.eip.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hk.eip.R;
import com.example.hk.eip.database.DbOpenHelper;

import java.util.Random;

public class QuizActivity2 extends Activity implements View.OnClickListener {
    private static final String TAG = "EIP_001";

    private ImageView img_ox;
    private TextView tv_quiz_meaning;
    private TextView tv_quiz_count;
    private TextView tv_quiz[];
    private CheckBox cb_quiz[];

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
        setContentView(R.layout.activity_quiz2);

        // Shares Preference 호출
        prefs = getSharedPreferences("EasyWordBook", MODE_PRIVATE);
        wordUnchecked = prefs.getInt("key_wordUnchecked", 0);

        tv_quiz_meaning = (TextView) findViewById(R.id.tv_quiz_name2);
        tv_quiz_meaning.setMovementMethod(new ScrollingMovementMethod());

        img_ox = (ImageView) findViewById(R.id.img_ox2);
        tv_quiz_count = (TextView) findViewById(R.id.count_state2);

        ox = new int[4];

        // 문제 내용 셋팅
        tv_quiz = new TextView[4];
        tv_quiz[0] = (TextView) findViewById(R.id.tv_quiz1_2);
        tv_quiz[1] = (TextView) findViewById(R.id.tv_quiz2_2);
        tv_quiz[2] = (TextView) findViewById(R.id.tv_quiz3_2);
        tv_quiz[3] = (TextView) findViewById(R.id.tv_quiz4_2);
        tv_quiz[0].setMovementMethod(new ScrollingMovementMethod());
        tv_quiz[1].setMovementMethod(new ScrollingMovementMethod());
        tv_quiz[2].setMovementMethod(new ScrollingMovementMethod());
        tv_quiz[3].setMovementMethod(new ScrollingMovementMethod());

        // next 버튼
        Button btn_next_quiz = (Button) findViewById(R.id.btn_next_quiz2);
        btn_next_quiz.setOnClickListener(this);

        // 체크박스 셋팅
        cb_quiz = new CheckBox[4];
        cb_quiz[0] = (CheckBox) findViewById(R.id.cb_1);
        cb_quiz[1] = (CheckBox) findViewById(R.id.cb_2);
        cb_quiz[2] = (CheckBox) findViewById(R.id.cb_3);
        cb_quiz[3] = (CheckBox) findViewById(R.id.cb_4);
        cb_quiz[0].setChecked(false);
        cb_quiz[0].setOnClickListener(this);
        cb_quiz[1].setOnClickListener(this);
        cb_quiz[2].setOnClickListener(this);
        cb_quiz[3].setOnClickListener(this);

        // 퀴즈 체크 버튼
        Button btn_cheak_quiz = (Button) findViewById(R.id.btn_cheak_quiz2);
        btn_cheak_quiz.setOnClickListener(this);

        // DB Create and Open
        mDbOpenHelper = new DbOpenHelper(this);
        mDbOpenHelper.open();

        tv_Cursor = null;
        tv_Cursor = mDbOpenHelper.getAllColumns();

        random = new Random();

        chk_overlap_name = new boolean[tv_Cursor.getCount()];
        chk_overlap_btn = new boolean[4];
        for (int i = 0; i < tv_Cursor.getCount() - 1; i++) {
            chk_overlap_name[i] = false;
        }

        setWord();
        setBtn();
        count = 1;
        tv_quiz_count.setText(count + " / " + wordUnchecked);
    }

    public void setWord() {
        do {
            ox[0] = random.nextInt(tv_Cursor.getCount());
            tv_Cursor.moveToPosition(ox[0]);
        }
        while (chk_overlap_name[ox[0]] || tv_Cursor.getInt(tv_Cursor.getColumnIndex("check_word")) != 0);
        tv_quiz_meaning.setText(tv_Cursor.getString(tv_Cursor.getColumnIndex("name")));
        tv_quiz_meaning.setTextSize(15);
        chk_overlap_name[ox[0]] = true;
    }

    public void setBtn() {
        int posi;
        for (int i = 0; i < 4; i++) {
            chk_overlap_btn[i] = false;
        }

        do {
            ox[1] = random.nextInt(tv_Cursor.getCount());
        } while (ox[1] == ox[0]);
        do {
            ox[2] = random.nextInt(tv_Cursor.getCount());
        } while (ox[2] == ox[0] || ox[2] == ox[1]);
        do {
            ox[3] = random.nextInt(tv_Cursor.getCount());
        } while (ox[3] == ox[0] || ox[3] == ox[1] || ox[3] == ox[2]);

        for (int i = 0; i < 4; i++) {
            do {
                posi = random.nextInt(4);
            } while (chk_overlap_btn[posi]);

            tv_Cursor.moveToPosition(ox[i]);
            tv_quiz[posi].setText(tv_Cursor.getString(tv_Cursor.getColumnIndex("meaning")));
            chk_overlap_btn[posi] = true;
        }
    }

    public void chk_ox(String chk_name) {
        tv_Cursor.moveToPosition(ox[0]);

        if (tv_Cursor.getString(tv_Cursor.getColumnIndex("meaning")).equals(chk_name)) {
            Log.i("onClick", "oButton");
            img_ox.setImageResource(R.drawable.img_o);
        } else {
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

    /*public void setOnCheckedChangeListener(CompoundButton buttonView, boolean isChecked) {
        if (cb_quiz[0].isChecked() == true) {
            cb_quiz[1].setChecked(false);
            cb_quiz[2].setChecked(false);
            cb_quiz[3].setChecked(false);
        } else if (cb_quiz[1].isChecked() == true) {
            cb_quiz[0].setChecked(false);
            cb_quiz[2].setChecked(false);
            cb_quiz[3].setChecked(false);

        } else if (cb_quiz[2].isChecked() == true) {
            cb_quiz[0].setChecked(false);
            cb_quiz[1].setChecked(false);
            cb_quiz[3].setChecked(false);

        } else if (cb_quiz[3].isChecked() == true) {
            cb_quiz[0].setChecked(false);
            cb_quiz[1].setChecked(false);
            cb_quiz[2].setChecked(false);
        }
    };*/

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cb_1:
                cb_quiz[1].setChecked(false);
                cb_quiz[2].setChecked(false);
                cb_quiz[3].setChecked(false);
                break;

            case R.id.cb_2:
                cb_quiz[0].setChecked(false);
                cb_quiz[2].setChecked(false);
                cb_quiz[3].setChecked(false);
                break;

            case R.id.cb_3:
                cb_quiz[0].setChecked(false);
                cb_quiz[1].setChecked(false);
                cb_quiz[3].setChecked(false);
                break;

            case R.id.cb_4:
                cb_quiz[0].setChecked(false);
                cb_quiz[1].setChecked(false);
                cb_quiz[2].setChecked(false);
                break;

            case R.id.btn_cheak_quiz2:
                if (cb_quiz[0].isChecked()) chk_ox("" + tv_quiz[0].getText());
                else if (cb_quiz[1].isChecked()) chk_ox("" + tv_quiz[1].getText());
                else if (cb_quiz[2].isChecked()) chk_ox("" + tv_quiz[2].getText());
                else if (cb_quiz[3].isChecked()) chk_ox("" + tv_quiz[3].getText());
                break;

            case R.id.btn_next_quiz2:
                Log.i("onClick", "NextButton");
                Log.i("onClick", count + "//" + wordUnchecked);
                if (count > wordUnchecked - 1) {
                    Toast toast = Toast.makeText(QuizActivity2.this, "마지막 문항입니다.", Toast.LENGTH_SHORT);
                    toast.show();
                    break;
                }

                img_ox.setImageResource(0);
                setWord();
                setBtn();
                count = count + 1;
                tv_quiz_count.setText(count + " / " + wordUnchecked);
                cb_quiz[0].setChecked(false);
                cb_quiz[1].setChecked(false);
                cb_quiz[2].setChecked(false);
                cb_quiz[3].setChecked(false);
                break;
        }
    }
}
