package net.a6te.lazycoder.doctorscafe;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static net.a6te.lazycoder.doctorscafe.R.id.addfabBtn;

public class DoctorsList extends AppCompatActivity {

    private ListView doctorList;
    private DoctorAdapter doctorAdapter;
    private ArrayList<Doctors>doctorses;
    private DoctorsDatabaseOperations doctorsDatabaseOperations;

    private FloatingActionButton fabBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_view);

        fabBtn = (FloatingActionButton) findViewById(addfabBtn);
        doctorList = (ListView) findViewById(R.id.doctorList);
        doctorses = new ArrayList<>();
        doctorsDatabaseOperations = new DoctorsDatabaseOperations(this);
        doctorses = doctorsDatabaseOperations.getAllDoctors();
        doctorAdapter = new DoctorAdapter(this, doctorses);
        doctorList.setAdapter(doctorAdapter);

        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(DoctorsList.this, AddDoctor.class));
            }
        });




        doctorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                int rowId = doctorses.get(position).getDoctorId();
                String doctorName = doctorses.get(position).getDoctorName();
                String doctorDetails = doctorses.get(position).getDoctorDetails();
                String doctorAppointment = doctorses.get(position).getAppoinment();
                String doctorPhone = doctorses.get(position).getDoctorPhone();
                String doctorEmail = doctorses.get(position).getDoctorEmail();

                Intent intent = new Intent(DoctorsList.this, DoctorDetails.class);
                intent.putExtra("id",rowId);
                intent.putExtra("name", doctorName);
                intent.putExtra("details", doctorDetails);
                intent.putExtra("appointment", doctorAppointment);
                intent.putExtra("phone", doctorPhone);
                intent.putExtra("email", doctorEmail);
                startActivity(intent);

            }
        });

        //Back arrow
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout_menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
