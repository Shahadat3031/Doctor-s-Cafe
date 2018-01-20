package net.a6te.lazycoder.doctorscafe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    private EditText enter_name_ET;
    private EditText enter_password_ET;
    private EditText enter_email_ET;
    private  EditText comfirm_pass_ET;

    private SharedPreferences userPreference;
    private SharedPreferences.Editor editor;
    private UserPreference userPreference1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        enter_name_ET= (EditText) findViewById(R.id.enter_name_ET);
        enter_password_ET= (EditText) findViewById(R.id.enter_password_ET);
        enter_email_ET= (EditText) findViewById(R.id.enter_email_ET);
        comfirm_pass_ET= (EditText) findViewById(R.id.confirm_pass_ET);

        userPreference1 = new UserPreference(this);


        //Back arrow
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void saveUser(View view) {
        String email =enter_email_ET.getText().toString();
        String pass = enter_password_ET.getText().toString();
        String userName=enter_name_ET.getText().toString();
        String comfirmPass=comfirm_pass_ET.getText().toString();
        if(pass.toString().equals(comfirmPass)){

            userPreference1.saveUser(email,pass,userName,comfirmPass);

            Intent intent = new Intent(SignUp.this, MainActivity.class);
            intent.putExtra("username", userName);
            intent.putExtra("password",pass);
            startActivity(intent);

        }
        else {
            Toast.makeText(this, "password not matched", Toast.LENGTH_SHORT).show();

        }

    }


    public void clearUserData(View view) {

        userPreference1.clearUserData();
    }
}