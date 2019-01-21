package com.nl.learn_android_bt1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Note> notes =new ArrayList<>();
    static final String TAG="MainActivity";
    static final int REQUEST_ADD=1;
    static final int REQUEST_EDIT=2;
    static int pos;
    MyAdapter myAdapter;
    void _genarateData(){
        for(int i=0;i<20;i++){
            Note note=new Note();
            note.setTitle("Note "+i);
            note.setContain("This is contain "+i);
            note.setLink("http://google.com.vn");
            notes.add(note);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _genarateData();
        myAdapter=new MyAdapter(MainActivity.this,R.layout.item_user,notes);
        GridView gridView=findViewById(R.id.grdView);
        gridView.setAdapter(myAdapter);
        Button btnAdd=findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AddNote.class);
                startActivityForResult(intent,REQUEST_ADD);
            }
        });
        registerForContextMenu(gridView);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note note=notes.get(position);
                Intent intent=new Intent(MainActivity.this, ViewNote.class);
                intent.putExtra("note",note);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v.getId()==R.id.grdView){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu, menu);
        }
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {
            case R.id.edit:
                //Log.e(TAG,notes.get(info.position).getTitle());
                pos=info.position;
                //int position=info.position;
                Intent intent=new Intent(MainActivity.this,EditNote.class);
                Note note=notes.get(info.position);
                intent.putExtra("note",note);
                //intent.putExtra("pos",position);
                startActivityForResult(intent,REQUEST_EDIT);
                break;
            case R.id.delete:
                notes.remove(info.position);
                myAdapter.notifyDataSetChanged();
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case REQUEST_ADD:
                //
                if(resultCode==AddNote.RESULT_OK){
                    String title=data.getStringExtra("title");
                    String contain=data.getStringExtra("contain");
                    String link=data.getStringExtra("link");
                    Note note=new Note();
                    note.setTitle(title);
                    note.setContain(contain);
                    note.setLink(link);
                    notes.add(note);
                    myAdapter.notifyDataSetChanged();
                }
                break;
            case REQUEST_EDIT:
                //
                if(resultCode==EditNote.RESULT_OK){
                    Note note=(Note)data.getSerializableExtra("note");
                    notes.set(pos,note);
                    myAdapter.notifyDataSetChanged();
                }
                break;
        }

    }
}
