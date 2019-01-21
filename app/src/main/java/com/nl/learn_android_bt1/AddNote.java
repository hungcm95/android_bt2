package com.nl.learn_android_bt1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNote extends AppCompatActivity {
    public static final int RESULT_OK=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        Button btnOk=findViewById(R.id.btnOK);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edtTitle=findViewById(R.id.edtAddTitle);
                EditText edtContain=findViewById(R.id.edtAddContain);
                EditText edtLink=findViewById(R.id.edtAddLink);
                Intent intent=new Intent();
                intent.putExtra("title",edtTitle.getText().toString());
                intent.putExtra("contain",edtContain.getText().toString());
                intent.putExtra("link",edtLink.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
