package edu.niu.z1783546.baseballstatsapp;

/**
 * Created by Sagar Sudhakar on 9/26/2016.
 *
 * Assignment 1
 * TA: Akash Rangojoo
 * Instructor: G Brown
 * Date: 26th September 2016
 */



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText ed1,ed2,ed3,ed4,ed5;
    private Button go;
    private TextView base,slug;
    private Compute compute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setup();

        compute = new Compute();

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                compute.setEtext1(Integer.parseInt(ed1.getText().toString()));
                compute.setEtext2(Integer.parseInt(ed2.getText().toString()));
                compute.setEtext3(Integer.parseInt(ed3.getText().toString()));
                compute.setEtext4(Integer.parseInt(ed4.getText().toString()));
                compute.setEtext5(Integer.parseInt(ed5.getText().toString()));

                compute.computeValues();

                int sanitycheck = Integer.parseInt(ed1.getText().toString())+ Integer.parseInt(ed2.getText().toString())+Integer.parseInt(ed3.getText().toString())+Integer.parseInt(ed4.getText().toString());

                if(Integer.parseInt(ed5.getText().toString())>= sanitycheck)
                {

                    base.setVisibility(View.VISIBLE);
                    slug.setVisibility(View.VISIBLE);

                    base.setText("Total number of bases:"+ String.valueOf(compute.getBases()));
                    slug.setText("Sluggish Percentage is "+String.valueOf(compute.getSlug()));

                }

                else{

                    Toast.makeText(getApplicationContext(),
                            "Check the Input values(bats?) and re-enter them",
                            Toast.LENGTH_LONG).show();
                }

            }
        });




    }

    private void setup()
    {
        ed1= (EditText) findViewById(R.id.totalHits_EdittextView);
        ed2= (EditText) findViewById(R.id.totaldoubles_EdittextView);
        ed3= (EditText) findViewById(R.id.totalTriples_EdittextView);
        ed4= (EditText) findViewById(R.id.totalhomeruns_EdittextView);
        ed5= (EditText) findViewById(R.id.totalbats_EdittextView);

        base = (TextView) findViewById(R.id.base);
        slug = (TextView) findViewById(R.id.slug);

        go = (Button) findViewById(R.id.button_compute);

    }
}

