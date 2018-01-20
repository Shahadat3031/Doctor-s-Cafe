package net.a6te.lazycoder.doctorscafe;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

public class AddDoctor extends AppCompatActivity {

    private EditText doctorName;
    private EditText doctorDetails;
    private EditText doctorPhone;
    private TextView doctorAppoinment;
    private EditText doctorEmail;

    private int rowId;
    private String name;
    private String details;
    private String appointment;
    private String phone;
    private  String email;

    private Calendar calendar;
    private int day,month,year;
    private String date;

    private DoctorsDatabaseOperations doctorsDatabaseOperations;

    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        doctorName = (EditText) findViewById(R.id.doctorNameET);
        doctorDetails = (EditText) findViewById(R.id.doctorDetailsET);
        doctorAppoinment = (TextView) findViewById(R.id.doctorAppoinmentET);
        doctorPhone = (EditText) findViewById(R.id.doctorPhoneET);
        doctorEmail = (EditText) findViewById(R.id.doctorEmailET);

        doctorsDatabaseOperations = new DoctorsDatabaseOperations(this);

        calendar = Calendar.getInstance(Locale.getDefault());
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        date = day+"/"+month+"/"+year;
        doctorAppoinment.setText(date);

        rowId = getIntent().getIntExtra("id", 0);
        name = getIntent().getStringExtra("name");
        details = getIntent().getStringExtra("details");
        appointment = getIntent().getStringExtra("appointment");
        phone = getIntent().getStringExtra("phone");
        email = getIntent().getStringExtra("email");

        doctorName.setText(name);
        doctorDetails.setText(details);
        doctorAppoinment.setText(appointment);
        doctorPhone.setText(phone);
        doctorEmail.setText(email);

        saveBtn = (Button) findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = doctorName.getText().toString();
                String details = doctorDetails.getText().toString();
                String appointment = doctorAppoinment.getText().toString();
                String phone = doctorPhone.getText().toString();
                String email = doctorEmail.getText().toString();

                if(name.isEmpty()){
                    doctorName.setError(getString(R.string.empty_field_msg));
                }else if(details.isEmpty()){
                    doctorDetails.setError(getString(R.string.empty_field_msg));
                }
                else if(appointment.isEmpty()){
                    doctorAppoinment.setError(getString(R.string.empty_field_msg));
                }
                else if(phone.isEmpty()){
                    doctorPhone.setError(getString(R.string.empty_field_msg));
                }
                else if(email.isEmpty()){
                    doctorEmail.setError(getString(R.string.empty_field_msg));
                }
                else{
                    if(rowId > 0)
                    {
                        Doctors doctors = new Doctors(name, details, appointment, phone, email);
                        boolean status = doctorsDatabaseOperations.updateDoctor(doctors,rowId);
                        if (status)
                        {
                            Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AddDoctor.this, DoctorsList.class));
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Failed Update", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Doctors doctors = new Doctors(name,details,appointment, phone, email);
                        boolean status = doctorsDatabaseOperations.addDoctor(doctors);
                        if(status){
                            Toast.makeText(getApplicationContext(), "Successfull", Toast.LENGTH_SHORT).show();
                         /*   doctorName.setText("");
                            doctorDetails.setText("");
                            doctorAppoinment.setText("");
                            doctorPhone.setText("");
                            doctorEmail.setText("");*/
                        }else{
                            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                        }


                    }
                    startActivity(new Intent(AddDoctor.this, DoctorsList.class));
                }


            }
        });


        //Back arrow
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }*/

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.save_Menu:
                String name = doctorName.getText().toString();
                String details = doctorDetails.getText().toString();
                String appointment = doctorAppoinment.getText().toString();
                String phone = doctorPhone.getText().toString();
                String email = doctorEmail.getText().toString();

                if(name.isEmpty()){
                    doctorName.setError(getString(R.string.empty_field_msg));
                }else if(details.isEmpty()){
                    doctorDetails.setError(getString(R.string.empty_field_msg));
                }
                else if(appointment.isEmpty()){
                    doctorAppoinment.setError(getString(R.string.empty_field_msg));
                }
                else if(phone.isEmpty()){
                    doctorPhone.setError(getString(R.string.empty_field_msg));
                }
                else if(email.isEmpty()){
                    doctorEmail.setError(getString(R.string.empty_field_msg));
                }
                else{
                    if(rowId > 0)
                    {
                        Doctors doctors = new Doctors(name, details, appointment, phone, email);
                        boolean status = doctorsDatabaseOperations.updateDoctor(doctors,rowId);
                        if (status)
                        {
                            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AddDoctor.this, DoctorsList.class));
                        }
                        else {
                            Toast.makeText(this, "Failed Update", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Doctors doctors = new Doctors(name,details,appointment, phone, email);
                        boolean status = doctorsDatabaseOperations.addDoctor(doctors);
                        if(status){
                            Toast.makeText(this, "Successfull", Toast.LENGTH_SHORT).show();
                         *//*   doctorName.setText("");
                            doctorDetails.setText("");
                            doctorAppoinment.setText("");
                            doctorPhone.setText("");
                            doctorEmail.setText("");*//*
                        }else{
                            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
                        }


                    }
                    startActivity(new Intent(AddDoctor.this, DoctorsList.class));
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
*/
    public void selectDate(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, dateListener, year, month, day);
        datePickerDialog.show();
    }
    DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            date = dayOfMonth+"/"+month+"/"+year;
            doctorAppoinment.setText(date);
        }
    };
}



