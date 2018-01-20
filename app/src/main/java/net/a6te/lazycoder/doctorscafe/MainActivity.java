package net.a6te.lazycoder.doctorscafe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText userName;
    private  EditText password;

    private UserPreference userPreference;

    private String user;
    private String pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        password= (EditText) findViewById(R.id.password_ET);
        userName= (EditText) findViewById(R.id.user_name_ET);

       /* user = getIntent().getStringExtra("username");
        pass = getIntent().getStringExtra("password");
*/

        userPreference = new UserPreference(this);
    }

    public void signup(View view) {
        Intent intent = new Intent(MainActivity.this,SignUp.class);
        startActivity(intent);
    }

    public void logIn(View view) {

        saveUserInfo();
                /*Toast.makeText(MainActivity.this, userName + "," + password, Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, userPreferance.getUserName() + "," + userPreferance.getUserPassword(), Toast.LENGTH_SHORT).show();*/
        if(user.equals(userPreference.getUserName()) && pass.equals(userPreference.getUserPassword())){
            Intent intent = new Intent(getApplicationContext(),DoctorsList.class);
            startActivity(intent);
            //startActivity(new Intent(MainActivity.this, DoctorsList.class));
        }
        else {
            Toast.makeText(MainActivity.this, "Wrong Input!!!", Toast.LENGTH_LONG).show();
        }

    }


    public void saveUserInfo()
    {
        user = userName.getText().toString();
        pass = password.getText().toString();

    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("no", null).show();
    }

}

