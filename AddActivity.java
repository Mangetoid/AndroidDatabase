package com.example.androiddatabaseagiannn;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class AddActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        final Button save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(SaveData())
                {
                    Intent newActivity = new Intent(AddActivity.this,MainActivity.class);
                    startActivity(newActivity);
                }
            }
        });
        final Button cancel = (Button) findViewById(R.id.btnCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent newActivity = new Intent(AddActivity.this,MainActivity.class);
                startActivity(newActivity);
            }
        });
    }
    public boolean SaveData()
    {
        final EditText tName = (EditText) findViewById(R.id.txtName);
        final EditText tTel = (EditText) findViewById(R.id.txtTel);
        final myDBClass myDb = new myDBClass(this);
        long saveStatus = myDb.InsertData(tName.getText().toString(),tTel.getText().toString());
        if(saveStatus <= 0)
        {
            Toast.makeText(AddActivity.this,"Add Data Error. ",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        Toast.makeText(AddActivity.this,"Add Data Successfully. ",
                Toast.LENGTH_SHORT).show();
        return true;
    }
}
