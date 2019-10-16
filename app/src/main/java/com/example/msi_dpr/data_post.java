package com.example.msi_dpr;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.github.javiersantos.appupdater.AppUpdater;
import com.github.javiersantos.appupdater.enums.UpdateFrom;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class data_post extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
GoogleApiClient.OnConnectionFailedListener, LocationListener {


    private Location location;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    List<Address> addresses;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    private static final long UPDATE_INTERVAL = 5000, FASTEST_INTERVAL = 5000; // = 5 seconds
    // lists for permissions
    private ArrayList<String> permissionsToRequest;
    private ArrayList<String> permissionsRejected = new ArrayList<>();
    private ArrayList<String> permissions = new ArrayList<>();
    // integer for permissions results request
    private static final int ALL_PERMISSIONS_RESULT = 1011;

    ProgressDialog dialog;
    DatabaseReference projectListdb, managerListdb, channel_partner_listdb, line_namesdb;
    TextView txtDate, txtIssueTime, txtReturnTime,locationTv;

    CheckBox work_yes,work_no;

    Spinner project_spinner; //project list spinner
    ArrayList<String> projects;
    ValueEventListener project_listener;
    ArrayAdapter<String> project_adapter;

    Spinner manager_spinner;
    ArrayList<String> managers;
    ValueEventListener manager_listener;
    ArrayAdapter<String> manager_adapter;

    TextView lines_spinner;
    SpinnerDialog spinnerDialog;
    ArrayList<String> line_name;
    ValueEventListener lines_listener;
    ArrayAdapter<String> line_adapter;

    Spinner channel_partner_spinner;
    ValueEventListener channel_partner_listner;
    ArrayAdapter<String> channel_partner_adapter;
    ArrayList<String> channel_partner;


    Button submit,signout;
    Geocoder geocoder;
    DatePickerDialog datePickerDialog;
    EditText site_engineer, line_length, route_length, drum_number, location_number, today_work, plan_tomorrow, ehs, remarks, total_completed, drum_length;
    //String managers[] = new String[]{"Rajendra A", "Rajendra A", "Saurav Anand", "Sankar G", "Sankar G", "Sankar G", "Sankar G", "Sankar G", "Sankar G", "Devendra", "Shyama", "Venkateswara", "Rajendra A"};
   // String project[] = new String[]{"AP- 1132 & 636km", "AP- 242km", "BSPTCL", "GETCO- 2226", "GETCO- 2274 P1", "GETCO- 2274 P2", "GETCO- 2275", "GETCO- 2276", "GETCO- 2278", "PGCIL-1851km", "PGCIL-721km", "PTCUL", "TS-216km"};

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_post);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/d/")
                .build();
        final postdata post = retrofit.create(postdata.class);
        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        permissionsToRequest = permissionsToRequest(permissions);
        txtDate = (TextView) findViewById(R.id.in_date);
        txtIssueTime = (TextView) findViewById(R.id.in_ptw_issue_time);
        txtReturnTime = (TextView) findViewById(R.id.in_ptw_return_time);
        site_engineer = (EditText) findViewById(R.id.in_site_engineer);
        channel_partner_spinner = findViewById(R.id.channel_partner);
        line_length = (EditText) findViewById(R.id.in_line_length);
        route_length = (EditText) findViewById(R.id.in_route_length);
        drum_number = (EditText) findViewById(R.id.in_drum_number);
        location_number = (EditText) findViewById(R.id.in_location_number);
        today_work = (EditText) findViewById(R.id.in_today_work);
        plan_tomorrow = (EditText) findViewById(R.id.in_plan_tomorrow);
        ehs = (EditText) findViewById(R.id.in_ehs);
        lines_spinner=(TextView) findViewById(R.id.search_line);
        remarks = (EditText) findViewById(R.id.in_remarks);
        submit = (Button) findViewById(R.id.btn_submit);
        total_completed = (EditText) findViewById(R.id.in_total_completed);
        drum_length = (EditText) findViewById(R.id.in_drum_length);
        project_spinner = (Spinner) findViewById(R.id.project_list);
        manager_spinner=(Spinner)findViewById(R.id.in_project_manger);
        locationTv=(TextView) findViewById(R.id.locationTv);
        signout=(Button) findViewById(R.id.btn_signout);
        work_yes=(CheckBox)findViewById(R.id.work_yes);
        work_no=(CheckBox)findViewById(R.id.work_no);
        dialog=new ProgressDialog(data_post.this);
        dialog.setMessage("Submitting...please wait");


        projectListdb= FirebaseDatabase.getInstance().getReference("projects");


        ActivityCompat.requestPermissions(data_post.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);
        geocoder=new Geocoder(this, Locale.getDefault());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (permissionsToRequest.size() > 0) {
                requestPermissions(permissionsToRequest.toArray(
                        new String[permissionsToRequest.size()]), ALL_PERMISSIONS_RESULT);
            }
        }
        work_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(work_yes.isChecked()){
                    work_no.setChecked(false);
                    txtIssueTime.setText("");
                    txtReturnTime.setText("");
                    txtReturnTime.setEnabled(true);
                    txtIssueTime.setEnabled(true);
                }
            }
        });
        work_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(work_no.isChecked()){
                    work_yes.setChecked(false);
                    txtIssueTime.setText("N/A");
                    txtReturnTime.setText("N/A");
                    txtReturnTime.setEnabled(false);
                    txtIssueTime.setEnabled(false);
                }
            }
        });
        // we build google api client
        googleApiClient = new GoogleApiClient.Builder(this).
                addApi(LocationServices.API).
                addConnectionCallbacks(this).
                addOnConnectionFailedListener(this).build();
        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(data_post.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        txtDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        String user_date=(String)txtDate.getText().toString();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Date d=null;
                        Date now=new Date();
                        if(user_date!=null){
                            try {
                                d=simpleDateFormat.parse(user_date);

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            if(d.after(now)){
                                txtDate.setTextColor(Color.RED);
                                txtDate.setError("Please Choose valid date");
                                submit.setEnabled(false);
                            }
                            else{
                                txtDate.setTextColor(Color.BLACK);
                                txtDate.setError(null);
                                submit.setEnabled(true);
                            }
                        }
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });
        txtIssueTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrenttime = Calendar.getInstance();
                int hour = mcurrenttime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrenttime.get(Calendar.MINUTE);
                TimePickerDialog mtimepicker;
                mtimepicker = new TimePickerDialog(data_post.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        Time time = new Time(hourOfDay, minute, 0);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm aa");
                        String s = simpleDateFormat.format(time);
                        txtIssueTime.setText(s);
                    }
                }, hour, minute, false);
                mtimepicker.setTitle("Select Time");
                mtimepicker.show();
            }
        });
        txtReturnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrenttime = Calendar.getInstance();
                int hour = mcurrenttime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrenttime.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog;
                timePickerDialog = new TimePickerDialog(data_post.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        Time time = new Time(hourOfDay, minute, 0);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm aa");
                        String s = simpleDateFormat.format(time);
                        txtReturnTime.setText(s);
                        try {
                            Date issue_t=simpleDateFormat.parse(txtIssueTime.getText().toString());
                            Date return_t=simpleDateFormat.parse(txtReturnTime.getText().toString());
                            if(issue_t.compareTo(return_t)>0){
                                txtReturnTime.setError("Please choose valid time");
                                Toast.makeText(data_post.this,"Return Time should be greater than issue time",Toast.LENGTH_SHORT).show();
                                txtReturnTime.setTextColor(Color.RED);
                            }
                            else {
                                txtReturnTime.setError(null);
                                txtReturnTime.setTextColor(Color.BLACK);
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, hour, minute, false);
                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();

            }
        });

       projects=new ArrayList<>();
       project_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, projects);
       project_spinner.setAdapter(project_adapter);
       retrive_projects();
        project_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        project_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                String project_select=project_spinner.getSelectedItem().toString().trim();
                channel_partner_listdb=FirebaseDatabase.getInstance().getReference(project_select).child("channel_partner");
                channel_partner=new ArrayList<>();
                channel_partner_adapter=new ArrayAdapter<String>(data_post.this,android.R.layout.simple_dropdown_item_1line,channel_partner);
                channel_partner_spinner.setAdapter(channel_partner_adapter);
                retrive_channel();
                channel_partner_adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                managerListdb=FirebaseDatabase.getInstance().getReference(project_select).child("projectManager");
                managers=new ArrayList<>();
                manager_adapter=new ArrayAdapter<String>(data_post.this,android.R.layout.simple_dropdown_item_1line,managers);
                manager_spinner.setAdapter(manager_adapter);
                retrive_managers();
                manager_adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                line_namesdb=FirebaseDatabase.getInstance().getReference(project_select).child("Line_name");
                line_name=new ArrayList<>();
                retrive_lines();
                spinnerDialog=new SpinnerDialog(data_post.this,line_name,"Select or Search line",R.style.DialogAnimations_SmileWindow,"Close");
                spinnerDialog.setCancellable(true); // for cancellable
                spinnerDialog.setShowKeyboard(false);// for open keyboard by default
                spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String item, int position) {
                        //Toast.makeText(data_post.this, item + "  " + position+"", Toast.LENGTH_SHORT).show();
                        lines_spinner.setText(" "+item);
                    }
                });
                lines_spinner.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        spinnerDialog.showSpinerDialog();
                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i=new Intent(data_post.this,login.class);
                startActivity(i);
                data_post.this.finish();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Project_Name = project_spinner.getSelectedItem().toString().trim();
                String Date = txtDate.getText().toString().trim();
                String Project_Manager = manager_spinner.getSelectedItem().toString().trim();
                String Site_Engineer = site_engineer.getText().toString().trim();
                String Channel_partner = channel_partner_spinner.getSelectedItem().toString().trim();
                String Line_Name = lines_spinner.getText().toString().trim();
                String Line_Length = line_length.getText().toString().trim();
                String Route_Length = route_length.getText().toString().trim();
                String Total_Completed=total_completed.getText().toString().trim();
                String Drum_Number = drum_number.getText().toString().trim();
                String Drum_Length=drum_length.getText().toString().trim();
                String Location_Number = location_number.getText().toString().trim();
                String PTW_Issue_Time = txtIssueTime.getText().toString().trim();
                String PTW_Return_Time = txtReturnTime.getText().toString().trim();
                String Work_Today = today_work.getText().toString().trim();
                String Plan_Tomorrow = plan_tomorrow.getText().toString().trim();
                String EHS = ehs.getText().toString().trim();
                String Remarks = remarks.getText().toString().trim();
                String l=locationTv.getText().toString();
                if (!(l.isEmpty()) && !(Date.isEmpty()) && !(Project_Manager.isEmpty()) && !(Site_Engineer.isEmpty()) && !(Channel_partner.isEmpty()) && !(Line_Name.isEmpty()) && !(Line_Length.isEmpty()) && !(Route_Length.isEmpty()) && !(Drum_Number.isEmpty()) && !(Location_Number.isEmpty()) && !(PTW_Issue_Time.isEmpty()) && !(PTW_Return_Time.isEmpty()) && !(Work_Today.isEmpty()) && !(Plan_Tomorrow.isEmpty()) && !(EHS.isEmpty()) && !(Remarks.isEmpty())) {
                    float line_len = Float.parseFloat(Line_Length);
                    float route_len = Float.parseFloat(Route_Length);
                    float total_com = Float.parseFloat(Total_Completed);
                    if (route_len > line_len) {
                        Toast.makeText(data_post.this, "Route Length Should not more than Line Length", Toast.LENGTH_SHORT).show();
                        //route_length.setTextColor(Color.RED);
                        route_length.setError("please enter valid value");
                    } else if (total_com > line_len) {
                        Toast.makeText(data_post.this, "Total Completed Should not more than Line Length", Toast.LENGTH_SHORT).show();
                        //total_completed.setTextColor(Color.RED);
                        total_completed.setError("please enter valid value");
                    }
                    else
                        {
                            Call<Void> completeQuestionnaireCall = post.completeQuestionnaire(Project_Name, Date, Project_Manager, Site_Engineer, Channel_partner
                                    , Line_Name, Line_Length, Route_Length, Total_Completed, Drum_Number, Drum_Length, Location_Number, PTW_Issue_Time, PTW_Return_Time, Work_Today, Plan_Tomorrow, EHS, Remarks, l);
                            completeQuestionnaireCall.enqueue(callCallback);
                            dialog.show();
                            route_length.setError(null);
                            //route_length.setTextColor(Color.BLACK);
                            total_completed.setError(null);
                            //total_completed.setTextColor(Color.BLACK);
                    }
                }
                else if(l.isEmpty()){
                    Toast.makeText(data_post.this,"Please Enable the GPS and wait for current address",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(data_post.this,"Please enter all the fields",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void retrive_lines() {
        lines_listener=line_namesdb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                line_name.clear();
                lines_spinner.setText(" ");
                for (DataSnapshot item:dataSnapshot.getChildren()){
                    line_name.add(item.getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void retrive_channel() {
        channel_partner_listner=channel_partner_listdb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                channel_partner.clear();
                for(DataSnapshot item:dataSnapshot.getChildren()){
                   channel_partner.add(item.getValue().toString());
                }
                channel_partner_adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void retrive_managers() {
        manager_listener=managerListdb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                managers.clear();
                for(DataSnapshot item:dataSnapshot.getChildren()){
                    managers.add(item.getValue().toString());
                }
                manager_adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void retrive_projects(){
        project_listener=projectListdb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                projects.clear();
                for(DataSnapshot item:dataSnapshot.getChildren()){
                    projects.add(item.getValue().toString());
                }
                project_adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private ArrayList<String> permissionsToRequest(ArrayList<String> wantedPermissions) {
        ArrayList<String> result = new ArrayList<>();

        for (String perm : wantedPermissions) {
            if (!hasPermission(perm))
            {
                result.add(perm);
            }
        }

        return result;
    }
    private boolean hasPermission(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
        }

        return true;
    }
    @Override
    protected void onStart() {
        super.onStart();

        if (googleApiClient != null)
        {
            googleApiClient.connect();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!checkPlayServices())
        {
            locationTv.setText("You need to install Google Play Services to use the App properly");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        // stop location updates
        if (googleApiClient != null  &&  googleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
            googleApiClient.disconnect();
        }
    }
    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);

        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(data_post.this,resultCode,PLAY_SERVICES_RESOLUTION_REQUEST);
            } else {
                finish();
            }

            return false;
        }

        return true;
    }

    private final Callback<Void> callCallback = new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            Log.d("XXX", "Submitted. " + response);
            dialog.dismiss();
            txtDate.setText("");
            txtIssueTime.setText("");
            txtReturnTime.setText("");
            site_engineer.getText().clear();
            line_length.getText().clear();
            route_length.getText().clear();
            total_completed.getText().clear();
            drum_number.getText().clear();
            drum_length.getText().clear();
            location_number.getText().clear();
            locationTv.setText("");
            today_work.getText().clear();
            plan_tomorrow.getText().clear();
            ehs.getText().clear();
            remarks.getText().clear();
            Toast.makeText(data_post.this,"Submitted",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(data_post.this,submit.class);
            startActivity(intent);
        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
            dialog.dismiss();
            Log.e("XXX", "Failed", t);
            Toast.makeText(data_post.this,"Failed to submit",Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                &&  ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        // Permissions ok, we get last location
        location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        if (location != null) {
            Geocoder geocoder;
            geocoder=new Geocoder(this,Locale.getDefault());
            try {
                addresses=geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String address = addresses.get(0).getAddressLine(0);
            locationTv.setText(address);
        }

        startLocationUpdates();

    }
    private void startLocationUpdates() {
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(UPDATE_INTERVAL);
        locationRequest.setFastestInterval(FASTEST_INTERVAL);

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                &&  ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "You need to enable permissions to display location !", Toast.LENGTH_SHORT).show();
        }

        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            Geocoder geocoder;
            geocoder=new Geocoder(this,Locale.getDefault());
            try {
                addresses=geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String address = addresses.get(0).getAddressLine(0);
            locationTv.setText(address);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case ALL_PERMISSIONS_RESULT:
                for (String perm : permissionsToRequest) {
                    if (!hasPermission(perm)) {
                        permissionsRejected.add(perm);
                    }
                }

                if (permissionsRejected.size() > 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(permissionsRejected.get(0))) {
                            new AlertDialog.Builder(data_post.this).
                                    setMessage("These permissions are mandatory to get your location. You need to allow them.").
                                    setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions(permissionsRejected.
                                                        toArray(new String[permissionsRejected.size()]), ALL_PERMISSIONS_RESULT);
                                            }
                                        }
                                    }).setNegativeButton("Cancel", null).create().show();

                            return;
                        }
                    }
                } else {
                    if (googleApiClient != null) {
                        googleApiClient.connect();
                    }
                }
                break;
        }
    }

}
