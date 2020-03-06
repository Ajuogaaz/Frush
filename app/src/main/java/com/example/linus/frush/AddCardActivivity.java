package com.example.linus.frush;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddCardActivivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card_activivity);

        String s1 = getIntent().getStringExtra("stringKey1");
        String s2 = getIntent().getStringExtra("stringKey2");

        ((TextView)findViewById(R.id.newquestion)).setText(s1);
        ((TextView) findViewById(R.id.newanswer)).setText(s2);

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

                Intent data = new Intent();
                data.putExtra("string1", string1);
                data.putExtra("string2", string2);

                setResult(RESULT_OK, data);
                finish();
            }
        });


    }
}
