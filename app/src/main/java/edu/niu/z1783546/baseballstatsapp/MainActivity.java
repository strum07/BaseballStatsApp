package edu.niu.z1783546.baseballstatsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button gobutton;

        gobutton = (Button)findViewById(R.id.button_compute);

        gobutton.setOnClickListener(compute);

        final View.OnClickListener compute = new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        };
    }
}
