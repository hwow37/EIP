package com.example.hk.eip.dialog;


import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hk.eip.R;

public class DeleteAskDialog extends Dialog {
    TextView tv_dia;
    Button btn_ok;
    Button btn_cancel;

    public DeleteAskDialog(Context context){
        super(context,R.style.FilterDialogTheme);

        setContentView(R.layout.dia_del);
        setTitle("삭제하시겠습니까?");

        tv_dia = (TextView)findViewById(R.id.tv_dia);
        btn_ok = (Button)findViewById(R.id.btn_dia_ok);
        btn_cancel = (Button)findViewById(R.id.btn_dia_cancel);
    }

    public void setOkClickListener(View.OnClickListener _okListener){
        btn_ok.setOnClickListener(_okListener);
    }

    public void setCancelClickListener(View.OnClickListener _cancelListener){
        btn_cancel.setOnClickListener(_cancelListener);
    }

    public void setContent(String contentText){
        tv_dia.setText(contentText);
        tv_dia.setTextSize(20);
    }
}
