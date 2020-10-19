package com.example.androiddatabaseagiannn;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
public class InfoActivity extends AppCompatActivity {
    myDBClass myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        final String mid = getIntent().getExtras().getString("id");
        final TextView txtid = (TextView) findViewById(R.id.txtid);
        final EditText txtname = (EditText) findViewById(R.id.txtname);
        final EditText txttel = (EditText) findViewById(R.id.txttel);
        myDb = new myDBClass(this);
        //newcode

        final ListView listView = (ListView) findViewById(R.id.listView2);
         String [] myData = myDb.SelectAllData();
         ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, myData);

        listView.setAdapter(adapter);
        //newcode


        String data [] = myDb.SelectData(mid);
        txtid.setText(mid);
        txtname.setText(data[1]);
        txttel.setText(data[2]);
        final Button update = (Button) findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
// Open Form Main
                long saveStatus =
                        myDb.UpdateData(mid,txtname.getText().toString(),txttel.getText().toString());
                if(saveStatus <= 0)
                {
                    Toast.makeText(InfoActivity.this,"Update fail. "+saveStatus,
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(InfoActivity.this,"Update Successfully. "+saveStatus,
                            Toast.LENGTH_SHORT).show();
                    final String[] mydata = myDb.SelectAllData();
                     ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(InfoActivity.this,android.R.layout.simple_expandable_list_item_1, mydata);
                    listView.setAdapter(adapter1);


                }
                //Intent newActivity = new Intent(InfoActivity.this,MainActivity.class);
                //startActivity(newActivity);
            }
        });
        final Button delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                long saveStatus = myDb.DeleteData(mid);
                if(saveStatus <= 0)
                {
                    Toast.makeText(InfoActivity.this,"Delete fail. "+saveStatus,
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(InfoActivity.this,"Delete Successfully. "+saveStatus,
                            Toast.LENGTH_SHORT).show();
                }
                Intent newActivity = new Intent(InfoActivity.this,MainActivity.class);
                startActivity(newActivity);
            }
        });
        final Button cancel = (Button) findViewById(R.id.cancle);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent newActivity = new Intent(InfoActivity.this,MainActivity.class);
                startActivity(newActivity);
            }
        });
    }
}