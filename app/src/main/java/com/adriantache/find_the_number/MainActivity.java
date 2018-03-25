package com.adriantache.find_the_number;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    //start at 103, due to rule 7
    int currentNumber = 103;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);


    }


    public void updateNumber(){
        textView.setText(currentNumber);
    }

    public void foundNumber(){
        textView.setTextColor(0x388E3C);
    }
}
