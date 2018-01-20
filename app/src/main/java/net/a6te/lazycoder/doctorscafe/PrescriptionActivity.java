package net.a6te.lazycoder.doctorscafe;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PrescriptionActivity extends AppCompatActivity {

    private ImageView pImage;
    private EditText doctorName;
    private EditText doctorDetails;
    private TextView prescriptionDate;
    /*private PrescriptionDatabaseHandler pdh;*/
    /*private PrescriptionAdapter adapter;
    private Prescription prescription;*/
    private Bitmap bp;
    private String photoPath;
    
    private String dName;
    private String dDetails;
    private String pDate;
    private byte[] photo;

    //private PrescriptionDatabase prescriptionDatabase;

    private Calendar calendar;
    private int day,month,year;
    private String date;
    
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private Button btnSelect;
    private String userChoosenTask;
    private File mediafile, photoDirectory;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);

        doctorName = (EditText) findViewById(R.id.doctorNameET);
        doctorDetails = (EditText) findViewById(R.id.doctorDetails);
        //prescriptionDate = (TextView) findViewById(R.id.prescriptionDate);
        pImage = (ImageView) findViewById(R.id.prescriptionImage);

        //prescriptionDatabase = new PrescriptionDatabase(this);
        photoDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        calendar = Calendar.getInstance(Locale.getDefault());
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        date = day+"/"+month+"/"+year;
        prescriptionDate.setText(date);
        //Back arrow
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    public void buttonClicked(View v){
        int id=v.getId();
        switch(id){
            case R.id.prescriptionImage:
                //selectImage();
                break;
        }
    }


    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }

    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = null;
        file = createImageFile();
        if(file != null){
            Uri imageUri = Uri.fromFile(file);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
            Log.e("doctor", "cameraIntent: "+photoPath );
            startActivityForResult(intent, REQUEST_CAMERA);
        }

    }

    private File createImageFile() {
        String timestamp = new SimpleDateFormat("yyyyMMdd HHmmss").format(new Date());
        String image = "JPEG_"+timestamp+".jpg";
        mediafile = new File(photoDirectory,image);
        photoPath = mediafile.getAbsolutePath();
        return mediafile;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }

        /*switch(requestCode) {
            case 2:
                if(resultCode == RESULT_OK){
                    Uri choosenImage = data.getData();

                    if(choosenImage !=null){

                        bp=decodeUri(choosenImage, 200);
                        pic.setImageBitmap(bp);
                    }
                }
        }*/
    }


    private void onCaptureImageResult(Intent data) {
        /*bp = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bp.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                System.currentTimeMillis() + ".jpg");
        photoPath = destination.getAbsolutePath();

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        Bitmap bitmap = BitmapFactory.decodeFile(photoPath);
        pImage.setImageBitmap(bitmap);

    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Uri choosenImage = data.getData();

        if(choosenImage !=null){

            bp=decodeUri(choosenImage, 200);
           /* pic.setImageBitmap(bp);*/
            pImage.setImageBitmap(bp);
        }

    }

    //COnvert and resize our image to 400dp for faster uploading our images to DB
    protected Bitmap decodeUri(Uri selectedImage, int REQUIRED_SIZE) {

        try {

            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o);

            // The new size we want to scale to
            // final int REQUIRED_SIZE =  size;

            // Find the correct scale value. It should be the power of 2.
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
                if (width_tmp / 2 < REQUIRED_SIZE
                        || height_tmp / 2 < REQUIRED_SIZE) {
                    break;
                }
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o2);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // function to get values from the Edittext and image
    private void getValues(){
        dName = doctorName.getText().toString();
        dDetails = doctorDetails.getText().toString();
        pDate = prescriptionDate.getText().toString();
        //photo = profileImage(bp);
    }

    private byte[] profileImage(Bitmap bp) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bp.compress(Bitmap.CompressFormat.PNG, 0, bos);
        return bos.toByteArray();
    }

    private void addContact() {
        getValues();

        //prescriptionDatabase.addPrescription(new Prescription(dName, dDetails, pDate, photoPath));
        Toast.makeText(this, "Successfull", Toast.LENGTH_SHORT).show();

    }

    public void setPrescriptionDate(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, dateListener, year, month, day);
        datePickerDialog.show();
    }
    DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            date = dayOfMonth+"/"+month+"/"+year;
            prescriptionDate.setText(date);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_pres_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.save_prescription:

                if(doctorName.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"Name edit text is empty, Enter name", Toast.LENGTH_LONG).show();
                }
                else if(doctorDetails.getText().toString().trim().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Name edit text is empty, Enter name", Toast.LENGTH_LONG).show();
                }
                else{
                    addContact();
                    //startActivity(new Intent(PrescriptionActivity.this, PrescriptionListActivity.class));
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
