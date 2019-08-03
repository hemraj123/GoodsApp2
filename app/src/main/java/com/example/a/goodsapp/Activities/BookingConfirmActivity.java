package com.example.a.goodsapp.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.a.goodsapp.R;
import com.google.android.gms.common.api.Response;

import java.util.HashMap;
import java.util.Map;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class BookingConfirmActivity  extends AppCompatActivity
{
    Button confirm_booking;
    EditText name1,mobile_no1;

    String Server_url="http://192.168.1.207:8000/api/porter/";
  //  AlertDialog.Builder builder;
    private BottomSheetDialog mBottomSheetDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookingconfirm);

        final TextView tvcost  = (TextView) findViewById(R.id.tvcost);
        final TextView pickup1 = (TextView) findViewById(R.id.pickup1);
        final TextView drop1 = (TextView) findViewById(R.id.drop1);

        final TextView tvDistance1 = (TextView) findViewById(R.id.tvDistance1);
        final TextView tvDuration1  = (TextView) findViewById(R.id.tvDuration1);

        mBottomSheetDialog=new BottomSheetDialog(BookingConfirmActivity.this);


        confirm_booking=(Button)findViewById(R.id.confirm_book);
        name1=(EditText)findViewById(R.id.name);
        mobile_no1=(EditText)findViewById(R.id.mobile_no);

        Intent intent = getIntent();
        String pri = intent.getStringExtra("value2");
        String pooja=intent.getStringExtra("value2");
        String sri=intent.getStringExtra("value2");


        String  pickup111=intent.getStringExtra("value6");
        String  drop111=intent.getStringExtra("value7");
        String  s5=intent.getStringExtra("value");
        String  s6=intent.getStringExtra("value1");

        tvcost.setText(pri);
        tvcost.setText(pooja);
        tvcost.setText(sri);
        pickup1.setText(pickup111);
        drop1.setText(drop111);
        tvDistance1.setText(s5);
        tvDuration1.setText(s6);
        //     builder=new AlertDialog.Builder(BookingConfirmActivity.this);


        confirm_booking.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                final String name,mobile_no,tvpickup,tvdrop,tvtvDistance,tvtvcost;

                name=name1.getText().toString();
                mobile_no=mobile_no1.getText().toString();

                if (name.isEmpty())
                {
                    Toast.makeText(BookingConfirmActivity.this, "Please enter name", Toast.LENGTH_SHORT).show();

                    return;
                }
                if (mobile_no.isEmpty())
                {
                    Toast.makeText(BookingConfirmActivity.this, "Please enter mobile number", Toast.LENGTH_SHORT).show();
                    return;
                }


                tvpickup=pickup1.getText().toString();
                tvdrop=drop1.getText().toString();
                tvtvDistance=tvDistance1.getText().toString();
                tvtvcost=tvcost.getText().toString();

                StringRequest stringRequest=new StringRequest(Request.Method.POST, Server_url,
                        new com.android.volley.Response.Listener<String>()

                        {

                         @Override
                            public void onResponse(String response)
                            {
                              //  builder.setTitle("Thank you,your booking is confirm.");

                              //  builder.setMessage("responce :"+ response );

/*
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        name1.setText("");
                                        mobile_no1.setText("");
                                        pickup1.setText("");
                                        drop1.setText("");
                                        tvDistance1.setText("");
                                        tvcost.setText("");

                                        Intent i=new Intent(BookingConfirmActivity.this,MapsActivity.class);
                                        startActivity(i);
                                    }
                                });
                                AlertDialog alertDialog= builder.create();
                                alertDialog.show();   */

                                openBottomSheetDialog();


                            }
                            private void openBottomSheetDialog()
                            {

                                View dialogLayout=getLayoutInflater().inflate(R.layout.dialog_layout,null);
                                mBottomSheetDialog.setContentView(dialogLayout);
                                mBottomSheetDialog.show();
                                AppCompatButton OkBtn=dialogLayout.findViewById(R.id.btn);
                                OkBtn.setOnClickListener(new View.OnClickListener()
                                {
                                    @Override
                                    public void onClick(View v)
                                    {

                                        mBottomSheetDialog.dismiss();

                                        name1.setText("");
                                        mobile_no1.setText("");
                                        pickup1.setText("");
                                        drop1.setText("");
                                        tvDistance1.setText("");
                                        tvcost.setText("");
                                        tvDuration1.setText("");

                                        Intent i=new Intent(BookingConfirmActivity.this,MapsActivity.class);
                                        startActivity(i);

                                    }
                                });

                            }
                        }

                        , new com.android.volley.Response.ErrorListener()
                {


                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(BookingConfirmActivity.this, "Error...........", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();

                    }
                 }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("name", name);
                        params.put("mobile_no", mobile_no);
                        params.put("pick_up",tvpickup);
                        params.put("drop_location",tvdrop);
                        params.put("fare",tvtvcost);
                        params.put("distance",tvtvDistance);

                        return params;
                    }
                };
                Mysingleton.getInstance(BookingConfirmActivity.this).addTorequestque(stringRequest);


            }




        });






    }
}

