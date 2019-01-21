package com.nl.learn_android_bt1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditNote extends AppCompatActivity {
    public static final int RESULT_OK=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        Note note=(Note)getIntent().getSerializableExtra("note");
        //int pos=getIntent().getIntExtra("pos",-1);
        final EditText edtTitle=findViewById(R.id.edtTitle);
        final EditText edtContain=findViewById(R.id.edtContain);
        final EditText edtLink=findViewById(R.id.edtLink);
        edtTitle.setText(note.getTitle());
        edtContain.setText(note.getContain());
        edtLink.setText(note.getLink());
        Button btnSave=findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note note=new Note();
                note.setTitle(edtTitle.getText().toString());
                note.setContain(edtContain.getText().toString());
                note.setLink(edtLink.getText().toString());
                Intent intent=new Intent();
                intent.putExtra("note",note);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
