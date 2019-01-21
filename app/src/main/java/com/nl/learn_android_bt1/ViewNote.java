package com.nl.learn_android_bt1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ViewNote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);
        TextView tvTitle=findViewById(R.id.tvTitleView);
        TextView tvContain=findViewById(R.id.tvContainView);
        final TextView tvLink=findViewById(R.id.tvLinkView);
        Note note=(Note)getIntent().getSerializableExtra("note");
        tvTitle.setText(note.getTitle());
        tvContain.setText(note.getContain());
        tvLink.setText(note.getLink());
        tvLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=tvLink.getText().toString();
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
    }
}
