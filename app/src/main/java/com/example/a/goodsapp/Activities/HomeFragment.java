package com.example.a.goodsapp.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.a.goodsapp.R;

public class HomeFragment extends Fragment {
    private Button btnFindPath;
    private EditText etOrigin;
    private EditText etDestination;
    String origin;
    String destination;
    Activity context;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context=getActivity();
        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.home_fragment, container, false);

    }

    public void onStart(){
        super.onStart();
        Button bt=(Button)context.findViewById(R.id.button1);
        bt.setOnClickListener(new OnClickListener()
        {
            public void onClick(View view){

                //create an Intent object
                Intent intent=new Intent(context, MapsActivity.class);
                //add data to the Intent object
                //start the second activity
                startActivity(intent);
            }

        });
    }

}


