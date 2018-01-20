package net.a6te.lazycoder.doctorscafe;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DoctorDetails extends AppCompatActivity {

    private int rowId;
    private String name;
    private String details;
    private String appointment;
    private String phone;
    private String email;

    private TextView nameTV;
    private TextView detailsTV;
    private TextView dateTV;
    private TextView phoneTV;
    private TextView emailTV;

    private DoctorsDatabaseOperations doctorsDatabaseOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        doctorsDatabaseOperations = new DoctorsDatabaseOperations(this);

        nameTV = (TextView) findViewById(R.id.nameTV);
        detailsTV = (TextView) findViewById(R.id.detailsTV);
        dateTV = (TextView) findViewById(R.id.dateTV);
        phoneTV = (TextView) findViewById(R.id.phoneTV);
        emailTV = (TextView) findViewById(R.id.emailTV);

        rowId = getIntent().getIntExtra("id", 0);
        name = getIntent().getStringExtra("name");
        details = getIntent().getStringExtra("details");
        appointment = getIntent().getStringExtra("appointment");
        phone = getIntent().getStringExtra("phone");
        email = getIntent().getStringExtra("email");

        nameTV.setText(name);
        detailsTV.setText(details);
        dateTV.setText(appointment);
        phoneTV.setText(phone);
        emailTV.setText(email);


        //Back arrow
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_details,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.update_Menu:
                Intent intent = new Intent(DoctorDetails.this, AddDoctor.class);
                intent.putExtra("id",rowId);
                intent.putExtra("name", name);
                intent.putExtra("details", details);
                intent.putExtra("appointment", appointment);
                intent.putExtra("phone", phone);
                intent.putExtra("email", email);
                startActivity(intent);

                break;
            case R.id.delete_Menu:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Delete Doctor");
                alert.setMessage("Are you sure to delete this doctor info?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean status = doctorsDatabaseOperations.deleteDoctor(rowId);
                        if(status)
                        {
                            Toast.makeText(DoctorDetails.this, "Deleted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(DoctorDetails.this, DoctorsList.class));
                        }
                        else
                        {
                            Toast.makeText(DoctorDetails.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                alert.setNegativeButton("No",null);
                alert.show();

                break;
            case R.id.home_Menu:
                startActivity(new Intent(DoctorDetails.this, DoctorsList.class));

                break;
            case R.id.logout_Menu:
                startActivity(new Intent(DoctorDetails.this, MainActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /*public void addPrescription(View view) {
        startActivity(new Intent(DoctorDetails.this, PrescriptionListActivity.class));
    }*/
}
