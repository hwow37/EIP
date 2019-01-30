package com.example.hk.eip.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hk.eip.R;
import com.example.hk.eip.database.CustomAdapter;
import com.example.hk.eip.database.DbOpenHelper;
import com.example.hk.eip.database.InfoClass;
import com.example.hk.eip.dialog.DeleteAskDialog;
import com.example.hk.eip.log.DLog;

import java.util.ArrayList;
import java.util.Collections;

public class ListSetActivity extends Activity {
    private static final String TAG = "EIP_001";

    private EditText et_name;
    private EditText et_meaning;
    private ListView mListView;
    private DeleteAskDialog delAsk;

    private DbOpenHelper mDbOpenHelper;
    private Cursor mCursor;
    private InfoClass mInfoClass;
    private ArrayList<InfoClass> mInfoArray;
    private CustomAdapter mAdapter;
    private SharedPreferences prefs;

    private AdapterView<?> del_LongClick;
    private int del_position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_set);

        et_name = (EditText) findViewById(R.id.et_name);
        et_meaning = (EditText) findViewById(R.id.et_meaning);
        mListView = (ListView) findViewById(R.id.view_list_set);

        // dialog set
        delAsk = new DeleteAskDialog(this);

        delAsk.setOkClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = mDbOpenHelper.deleteColumn(((InfoClass) del_LongClick.getAdapter().getItem(del_position))._id);

                DLog.e(TAG, "result = " + result);

                if (result) {
                    mInfoArray.remove(del_position);
                    mAdapter.setArrayList(mInfoArray);
                    mAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getApplicationContext(), "INDEX를 확인해 주세요.", Toast.LENGTH_LONG).show();
                }

                delAsk.dismiss();
            }
        });

        delAsk.setCancelClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delAsk.dismiss();
            }
        });

        delAsk.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
            }
        });

        prefs = getSharedPreferences("EasyWordBook", MODE_PRIVATE);

        // DB Create and Open
        mDbOpenHelper = new DbOpenHelper(this);
        mDbOpenHelper.open();

        mInfoArray = new ArrayList<InfoClass>();

        doWhileCursorToArray();
        Collections.reverse(mInfoArray);

        // Log 남기기
        for (InfoClass i : mInfoArray) {
            DLog.d(TAG, "ID = " + i._id);
            DLog.d(TAG, "name = " + i.name);
            DLog.d(TAG, "meaning = " + i.meaning);
        }

        mAdapter = new CustomAdapter(this, mInfoArray);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemLongClickListener(longClickListener);
    }

    @Override
    protected void onPause() {
        super.onPause();

        mCursor = null;
        mCursor = mDbOpenHelper.getAllColumns();

        int count_chk = 0;
        while (mCursor.moveToNext()) {
            if (mCursor.getInt(mCursor.getColumnIndex("check_word")) == 0) {
                count_chk++;
            }
        }

        SharedPreferences.Editor ed = prefs.edit();
        ed.putInt("key_wordCount", mCursor.getCount());
        ed.putInt("key_wordUnchecked", count_chk);
        ed.commit();

        mCursor.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mDbOpenHelper.close();
    }

    /**
     * ListView의 Item을 롱클릭 할때 호출 ( 선택한 아이템의 DB 컬럼과 Data를 삭제 한다. )
     */
    private AdapterView.OnItemLongClickListener longClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
            del_LongClick = arg0;
            del_position = position;

            delAsk.setContent(mAdapter.getText(position));
            delAsk.show();

            return false;
        }
    };

    /**
     * DB에서 받아온 값을 ArrayList에 Add
     */
    private void doWhileCursorToArray() {

        mCursor = null;
        mCursor = mDbOpenHelper.getAllColumns();
        DLog.e(TAG, "COUNT = " + mCursor.getCount());

        while (mCursor.moveToNext()) {

            mInfoClass = new InfoClass(
                    mCursor.getInt(mCursor.getColumnIndex("_id")),
                    mCursor.getString(mCursor.getColumnIndex("name")),
                    mCursor.getString(mCursor.getColumnIndex("meaning")),
                    mCursor.getInt(mCursor.getColumnIndex("check_word")));

            mInfoArray.add(mInfoClass);
        }
    }

    /**
     * OnClick Button
     *
     * @param v
     */
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                mDbOpenHelper.insertColumn(
                        et_name.getText().toString().trim(),
                        et_meaning.getText().toString().trim(),
                        0);

                mInfoArray.clear();

                doWhileCursorToArray();
                Collections.reverse(mInfoArray);

                mAdapter.setArrayList(mInfoArray);
                mAdapter.notifyDataSetChanged();

                et_name.setText("");
                et_meaning.setText("");

                break;
        }
    }
}
