package com.test.gracelu.test3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class AddLocActivity extends AppCompatActivity {

    private static  ImageButton FAB = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_loc);

        Button done = (Button) findViewById(R.id.doneLoc);
        final EditText eN   = (EditText)findViewById(R.id.editName);
        final EditText eS   = (EditText)findViewById(R.id.editStreet);
        final EditText eC   = (EditText)findViewById(R.id.editCity);
        final EditText eZ   = (EditText)findViewById(R.id.editZip);
        final DBHandler db = new DBHandler(this, null, null, 1);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //make a new location and add to database
                Location news = new Location();
                news.setName(eN.getText().toString());
                news.setStreet(eS.getText().toString());
                news.setCity(eC.getText().toString());
                news.setZipcode(Integer.parseInt(String.valueOf(eZ.getText())));

                db.addLocation(news);
                Intent i = new Intent(AddLocActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

        FAB = (ImageButton) findViewById(R.id.floatingActionButton2);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddLocActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
