package com.example.a.goodsapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a.goodsapp.R;


public class VechilesActivity extends AppCompatActivity {


    String t1 = "5";
    String t2 = "10";
    String t3 = "30";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vechiles);


        final TextView pick11 = (TextView) findViewById(R.id.pick11);
        final TextView drop11 = (TextView) findViewById(R.id.drop11);

        final TextView tempo1 = (TextView) findViewById(R.id.tempo1);
        final TextView minitruck1 = (TextView) findViewById(R.id.minitruck1);
        final TextView truck1 = (TextView) findViewById(R.id.truck1);



        ImageView tempo = (ImageView) findViewById(R.id.tempo);
        ImageView minitruck = (ImageView) findViewById(R.id.minitruck);
        ImageView truck = (ImageView) findViewById(R.id.truck);


        final Intent intent = getIntent();

        String s5 = intent.getStringExtra("value");

        String s6 = intent.getStringExtra("value1");

        String pickup1=intent.getStringExtra("value3");
        String drop1=intent.getStringExtra("value4");

         pick11.setText(pickup1);
         drop11.setText(drop1);

         tempo.setOnClickListener(new View.OnClickListener()
        {
            String pri;
            String pickup111;
            String drop111;

            String s5 = intent.getStringExtra("value");
            String s6 = intent.getStringExtra("value1");


            @Override
            public void onClick(View v)
            {
                pri = tempo1.getText().toString();

                pickup111=pick11.getText().toString();
                drop111=drop11.getText().toString();


                Intent intent = new Intent(VechilesActivity.this, BookingConfirmActivity.class);
                intent.putExtra("value2", pri);
                intent.putExtra("value6",pickup111);
                intent.putExtra("value7",drop111);

                intent.putExtra("value",s5);
                intent.putExtra("value1",s6);
                startActivity(intent);
            }
        });

        minitruck.setOnClickListener(new View.OnClickListener()
        {
            String pooja;
            String pickup111;
            String drop111;

            String s5 = intent.getStringExtra("value");
            String s6 = intent.getStringExtra("value1");


            @Override
            public void onClick(View v)
            {
                pooja = minitruck1.getText().toString();

                pickup111=pick11.getText().toString();
                drop111=drop11.getText().toString();


                Intent intent = new Intent(VechilesActivity.this, BookingConfirmActivity.class);
                intent.putExtra("value2", pooja);
                intent.putExtra("value6",pickup111);
                intent.putExtra("value7",drop111);

                intent.putExtra("value",s5);
                intent.putExtra("value1",s6);
                startActivity(intent);
            }
        });


        truck.setOnClickListener(new View.OnClickListener()
        {
            String sri;
            String pickup111;
            String drop111;

            String s5 = intent.getStringExtra("value");
            String s6 = intent.getStringExtra("value1");


            @Override
            public void onClick(View v)
            {
                sri = truck1.getText().toString();

                pickup111=pick11.getText().toString();
                drop111=drop11.getText().toString();


                Intent intent = new Intent(VechilesActivity.this, BookingConfirmActivity.class);
                intent.putExtra("value2", sri);
                intent.putExtra("value6",pickup111);
                intent.putExtra("value7",drop111);

                intent.putExtra("value",s5);
                intent.putExtra("value1",s6);
                startActivity(intent);
            }
        });













        //   distance and time set nahi karna h yaha par........ s5=distance,s6=time


        //  String s2 = s1.getText().toString();
        String[] parts = s5.split(" ");
        String part1 = parts[0];
        String part2 = parts[1];


        Double a = Double.valueOf(part1);
        Double b = Double.valueOf(t1);
        Double price1 = a * b;

        tempo1.setText(Double.toString(price1));


        Double c = Double.valueOf(part1);
        Double d = Double.valueOf(t2);
        Double price2 = c * d;

        minitruck1.setText(Double.toString(price2));

        Double e = Double.valueOf(part1);
        Double f = Double.valueOf(t3);
        Double price3 = e * f;

        truck1.setText(Double.toString(price3));



      }
    }

