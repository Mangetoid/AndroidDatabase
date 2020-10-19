package com.example.androiddatabaseagiannn;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
public class ShowActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        final myDBClass myDb = new myDBClass(this);
        final ListView listView = (ListView) findViewById(R.id.listView1);
        final String [] myData = myDb.SelectAllData();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, myData);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                String mid = myData[position].split("-")[0];
                String name = myData[position].split("-")[1];
                String tel = myData[position].split("-")[2];



                Intent newActivity = new Intent(ShowActivity.this,InfoActivity.class);
                newActivity.putExtra("id", mid);
                newActivity.putExtra("name", name);
                newActivity.putExtra("tel", tel);
                startActivity(newActivity);
            }
        });
    }
}