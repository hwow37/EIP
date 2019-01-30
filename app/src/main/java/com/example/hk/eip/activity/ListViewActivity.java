package com.example.hk.eip.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hk.eip.R;
import com.example.hk.eip.database.CustomAdapter;
import com.example.hk.eip.database.DbOpenHelper;
import com.example.hk.eip.database.InfoClass;
import com.example.hk.eip.log.DLog;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListViewActivity extends Activity {
    private static final String TAG = "EIP_001";

    private EditText mEditText;
    private ListView mListView;
    private Button btn_search;
    private Spinner spn_arrWay;

    private DbOpenHelper mDbOpenHelper;
    private Cursor mCursor;
    private InfoClass mInfoClass;
    private ArrayList<InfoClass> mInfoArray;
    private CustomAdapter mAdapter;
    private ArrayAdapter<String> spnAdapter;

    private String[] wayData;
    private String arr_way = "최근에 추가된 용어 순서로";
    private String last_arr_way;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        mListView = (ListView) findViewById(R.id.view_list);
        btn_search = (Button) findViewById(R.id.btn_search);
        spn_arrWay = (Spinner) findViewById(R.id.spn_arrway);

        // Spinner
        wayData = getResources().getStringArray(R.array.array_way);
        spnAdapter = new ArrayAdapter<String>(this, R.layout.spinner_dropdown_item, wayData);
        spn_arrWay.setAdapter(spnAdapter);
        spn_arrWay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                arr_way = spnAdapter.getItem((int) id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // DB Create and Open
        mDbOpenHelper = new DbOpenHelper(this);
        mDbOpenHelper.open();

        mInfoArray = new ArrayList<InfoClass>();

        // Log 남기기
        for (InfoClass i : mInfoArray) {
            DLog.d(TAG, "ID = " + i._id);
            DLog.d(TAG, "name = " + i.name);
            DLog.d(TAG, "meaning = " + i.meaning);
            DLog.d(TAG, "check_word = " + i.check_word);
        }

        mAdapter = new CustomAdapter(this, mInfoArray);
        mListView.setAdapter(mAdapter);

        setArr(arr_way);

        mListView.setOnItemClickListener(ClickListener);
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

        SharedPreferences prefs = getSharedPreferences("EasyWordBook", MODE_PRIVATE);
        SharedPreferences.Editor ed = prefs.edit();
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
     * ListView의 Item을 클릭 할때 호출
     */
    private AdapterView.OnItemClickListener ClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
            boolean result = mDbOpenHelper.checkWordState(((InfoClass) arg0.getAdapter().getItem(position))._id,
                    ((InfoClass) arg0.getAdapter().getItem(position)).check_word);

            if (result) {
                setArr(last_arr_way);
            } else {
                Toast.makeText(getApplicationContext(), "INDEX를 확인해 주세요.", Toast.LENGTH_LONG).show();
            }
        }
    };

    private void setArr(String way){
        switch (way) {
            case "최근에 추가된 용어 순서로":
                mInfoArray.clear();
                doWhileCursorToArray();
                Collections.reverse(mInfoArray);
                mAdapter.setArrayList(mInfoArray);
                mAdapter.notifyDataSetChanged();
                last_arr_way = "최근에 추가된 용어 순서로";

                break;
            case "오래된 용어 순서로":
                mInfoArray.clear();
                doWhileCursorToArray();
                mAdapter.setArrayList(mInfoArray);
                mAdapter.notifyDataSetChanged();
                last_arr_way = "오래된 용어 순서로";

                break;
            case "용어 오름차순으로":
                mInfoArray.clear();
                doWhileCursorToArray();
                Collections.sort(mInfoArray, meanComparator);
                mAdapter.setArrayList(mInfoArray);
                mAdapter.notifyDataSetChanged();
                last_arr_way = "용어 오름차순으로";

                break;
            case "용어 내림차순으로":
                mInfoArray.clear();
                doWhileCursorToArray();
                Collections.sort(mInfoArray, meanComparator);
                Collections.reverse(mInfoArray);
                mAdapter.setArrayList(mInfoArray);
                mAdapter.notifyDataSetChanged();
                last_arr_way = "용어 내림차순으로";

                break;
        }
    }

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

        mCursor.close();
    }

    /**
     * OnClick Button
     *
     * @param v
     */
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search:
                setArr(arr_way);

                break;
        }
    }

    // 용어 정렬
    private final static Comparator<InfoClass> meanComparator = new Comparator<InfoClass>() {
        private final Collator collator = Collator.getInstance();

        @Override
        public int compare(InfoClass object1, InfoClass object2) {
            return collator.compare(object1.getName(), object2.getName());
        }
    };
}
