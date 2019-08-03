package com.example.a.goodsapp.Activities;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.a.goodsapp.Modules.DirectionFinder;
import com.example.a.goodsapp.Modules.DirectionFinderListener;
import com.example.a.goodsapp.Modules.Route;
import com.example.a.goodsapp.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, DirectionFinderListener {
    private final int REQUEST_LOCATION_PERMISSION = 1;
    private GoogleMap mMap;
    private Button Book;

    private Button btnFindPath;
    private EditText pickup;
    private EditText drop;
    private EditText etPrice;
    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    private ProgressDialog progressDialog;

    String origin;
    String destination;


    Button Vechiles;
    private int requestCode;
    private int resultCode;
    private Intent data;

    public MapsActivity() throws IOException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        requestLocationPermission();

        pickup = (EditText) findViewById(R.id.pickup);
        drop = (EditText) findViewById(R.id.drop);

   //   Button Book = (Button) findViewById(R.id.btnFindPath1);
        Vechiles    = (Button) findViewById(R.id.vech1);

        ImageView place = (ImageView) findViewById(R.id.img);

        ImageView place1 = (ImageView) findViewById(R.id.img1);

            //only use karnai kai liye isko bnaya h (temperory)
        /*     Vechiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MapsActivity.this, VechilesActivity.class);
                startActivity(intent);
            }
        });    */




      /*   Book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                sendRequest();


            }
        });            */



        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                openPlacePicker();
            }
        });

        place1.setOnClickListener(new View.OnClickListener() {
            @Override
    public void onClick(View v)
    {
        openPlacePicker1();
    }
    });
    }


    public void sendRequest()
    {


        String origin = pickup.getText().toString();
        String destination = drop.getText().toString();

        if (origin.isEmpty()) {
            Toast.makeText(this, "Please enter pick up location!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (destination.isEmpty())
        {
            Toast.makeText(this, "Please enter drop location!", Toast.LENGTH_SHORT).show();
            return;
        }
        try
        {
            new DirectionFinder( this, origin, destination).execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    /*    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }

            Address location = address.get(0);
            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (IOException ex) {

            ex.printStackTrace();
        }

        return p1;
    }
    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
*/



public void openPlacePicker()
    {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            // for activty
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST1);

        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    public void openPlacePicker1()
    {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            // for activty
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST2);

        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }


    private final static int PLACE_PICKER_REQUEST1=999;
    private final static int PLACE_PICKER_REQUEST2=1000;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        this.requestCode = requestCode;
        this.resultCode = resultCode;
        this.data = data;
        super.onActivityResult(requestCode, resultCode, data);
        checkPermissionOnActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK)
        {
            switch (requestCode)
            {
                case PLACE_PICKER_REQUEST1:
                    Place place1 = PlacePicker.getPlace(this, data);
                    String placeName1 = String.format("%s", place1.getName());
                    double latitude1 = place1.getLatLng().latitude;
                    double longitude1 = place1.getLatLng().longitude;
                    pickup.setText(placeName1);
                    break;


                    case PLACE_PICKER_REQUEST2:
                    Place place2 = PlacePicker.getPlace(this, data);
                    String placeName2 = String.format("%s", place2.getName());
                    double latitude2 = place2.getLatLng().latitude;
                    double longitude2 = place2.getLatLng().longitude;
                    drop.setText(placeName2);
                    sendRequest();
                    break;

            }
        }
    }


    private void checkPermissionOnActivityResult(int requestCode, int resultCode, Intent data)
    {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @AfterPermissionGranted(REQUEST_LOCATION_PERMISSION)
    private void requestLocationPermission() {
        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION};
        if(EasyPermissions.hasPermissions(this, perms)) {
            //Toast.makeText(this, "Permission already granted", Toast.LENGTH_SHORT).show();
        }
        else {
            EasyPermissions.requestPermissions(this, "Please grant the location permission", REQUEST_LOCATION_PERMISSION, perms);
        }
    }







    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng india = new LatLng(28.623266, 77.201337);
        mMap.addMarker(new MarkerOptions().position(india).title("Marker in delhi"));
        mMap.addMarker(new MarkerOptions()
                .position(india));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(india, 10));


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        mMap.setMyLocationEnabled(true);
    }

    @Override
    public void onDirectionFinderStart() {
        progressDialog = ProgressDialog.show(this, "Please wait.",
                "Finding direction..!", true);

        if (originMarkers != null) {
            for (Marker marker : originMarkers) {
                marker.remove();
            }
        }

        if (destinationMarkers != null) {
            for (Marker marker : destinationMarkers) {
                marker.remove();
            }
        }

        if (polylinePaths != null) {
            for (Polyline polyline : polylinePaths) {
                polyline.remove();
            }
        }
    }


    @Override
    public void onDirectionFinderSuccess(List<Route> routes)
    {


        progressDialog.dismiss();
        polylinePaths = new ArrayList<>();
        originMarkers = new ArrayList<>();
        destinationMarkers = new ArrayList<>();

        for (Route route : routes) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route.startLocation, 10));




            final TextView s= (TextView) findViewById(R.id.tvDuration);

           s.setText(route.duration.text);





           final TextView s1=(TextView) findViewById(R.id.tvDistance);
           s1.setText(route.distance.text);

                Button Vechiles = (Button) findViewById(R.id.vech1);

                Vechiles.setOnClickListener(new View.OnClickListener() {
                    String s5;
                    String s6;
                    String pickup1;
                    String drop1;

                    @Override
                    public void onClick(View v) {

                        s5 = s1.getText().toString();
                        s6 = s.getText().toString();
                        pickup1 = pickup.getText().toString();
                        drop1 = drop.getText().toString();

                        Intent intent = new Intent(MapsActivity.this, VechilesActivity.class);
                        intent.putExtra("value", s5);
                        intent.putExtra("value1", s6);
                        intent.putExtra("value3",pickup1);
                        intent.putExtra("value4", drop1);

                        startActivity(intent);
                    }
                });




            originMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.start_blue))
                    .title(route.startAddress)
                    .position(route.startLocation)));
            destinationMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.end_green))
                    .title(route.endAddress)
                    .position(route.endLocation)));

            PolylineOptions polylineOptions = new PolylineOptions().
                    geodesic(true).
                    color(Color.BLUE).
                    width(10);

            for (int i = 0; i < route.points.size(); i++)
                polylineOptions.add(route.points.get(i));

            polylinePaths.add(mMap.addPolyline(polylineOptions));
        }
    }
}



