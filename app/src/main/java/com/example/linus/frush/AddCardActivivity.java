package com.example.linus.frush;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddCardActivivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card_activivity);

        String s1 = getIntent().getStringExtra("stringKey1");
        String s2 = getIntent().getStringExtra("stringKey2");
        String s3 = getIntent().getStringExtra("stringKey3");
        String s4 = getIntent().getStringExtra("stringKey4");

        ((TextView)findViewById(R.id.newquestion)).setText(s1);
        ((TextView) findViewById(R.id.newanswer)).setText(s2);
        ((TextView) findViewById(R.id.wronganswer1)).setText(s3);
        ((TextView) findViewById(R.id.wronganswer2)).setText(s4);

        findViewById(R.id.cancelIcon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        findViewById(R.id.saveIcon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string1 = ((EditText) findViewById(R.id.newquestion)).getText().toString();
                String string2 = ((EditText) findViewById(R.id.newanswer)).getText().toString();
                String string3 = ((EditText) findViewById(R.id.wronganswer1)).getText().toString();
                String string4 = ((EditText) findViewById(R.id.wronganswer2)).getText().toString();

                if(string1.isEmpty()|| string2.isEmpty() || string3.isEmpty() || string4.isEmpty()){
                    //Snackbar.make(findViewById(R.id.secondaryroot),
                    //        "OOPS!  Make sure you fill out the fields",
                    //        Snackbar.LENGTH_SHORT).show()
                    Toast.makeText(AddCardActivivity.this,
                            "OOPS!  Make sure you fill out the fields",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                Intent data = new Intent();
                data.putExtra("string1", string1);
                data.putExtra("string2", string2);
                data.putExtra("string3", string3);
                data.putExtra("string4", string4);

                setResult(RESULT_OK, data);
                finish();

            }

        });


        }
}
