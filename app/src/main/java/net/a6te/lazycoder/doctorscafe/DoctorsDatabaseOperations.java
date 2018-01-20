package net.a6te.lazycoder.doctorscafe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Mobile App Develop on 4/5/2017.
 */

public class DoctorsDatabaseOperations {
    private DoctorDatabaseHelper doctorDatabaseHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Doctors doctors;

    public DoctorsDatabaseOperations(Context context) {
        doctorDatabaseHelper = new DoctorDatabaseHelper(context);
    }
    public void open()
    {
        sqLiteDatabase = doctorDatabaseHelper.getWritableDatabase();
    }
    public void close()
    {
        sqLiteDatabase.close();
    }

    public boolean addDoctor(Doctors doctors)
    {
        this.open();
        ContentValues values = new ContentValues();
        values.put(DoctorDatabaseHelper.COL_DOCTORNAME,doctors.getDoctorName());
        values.put(DoctorDatabaseHelper.COL_DOCTORDETAILS,doctors.getDoctorDetails());
        values.put(DoctorDatabaseHelper.COL_APPOINTMENT,doctors.getAppoinment());
        values.put(DoctorDatabaseHelper.COL_DOCTORPHONE,doctors.getDoctorPhone());
        values.put(DoctorDatabaseHelper.COL_DOCTOREMAIL,doctors.getDoctorEmail());
       long id =  sqLiteDatabase.insert(DoctorDatabaseHelper.DOCTOR_TABLE, null, values);
        this.close();
        if(id > 0)
        {
            return true;
        }
        else {
            return false;
        }
    }

    public ArrayList<Doctors> getAllDoctors()
    {
        ArrayList<Doctors>doctorses = new ArrayList<>();
        this.open();
        Cursor cursor = sqLiteDatabase.query(DoctorDatabaseHelper.DOCTOR_TABLE,null,null, null, null, null,null);
        cursor.moveToFirst();
        if(cursor != null && cursor.getCount() > 0)
        {
            for(int i = 0; i < cursor.getCount(); i++)
            {
                int id = cursor.getInt(cursor.getColumnIndex(DoctorDatabaseHelper.COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(DoctorDatabaseHelper.COL_DOCTORNAME));
                String details = cursor.getString(cursor.getColumnIndex(DoctorDatabaseHelper.COL_DOCTORDETAILS));
                String appointment = cursor.getString(cursor.getColumnIndex(DoctorDatabaseHelper.COL_APPOINTMENT));
                String phone = cursor.getString(cursor.getColumnIndex(DoctorDatabaseHelper.COL_DOCTORPHONE));
                String email = cursor.getString(cursor.getColumnIndex(DoctorDatabaseHelper.COL_DOCTOREMAIL));

                doctorses.add(new Doctors(name, details,appointment, phone, email, id));
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.close();
        return doctorses;
    }

    public boolean deleteDoctor(int rowId)
    {
        this.open();
        int deletedItemId = sqLiteDatabase.delete(DoctorDatabaseHelper.DOCTOR_TABLE,
                DoctorDatabaseHelper.COL_ID+" = ?",
                new String[]{Integer.toString(rowId)});
        this.close();
        if(deletedItemId > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean updateDoctor(Doctors doctors, int rowId)
    {
        this.open();
        ContentValues values = new ContentValues();
        values.put(DoctorDatabaseHelper.COL_DOCTORNAME,doctors.getDoctorName());
        values.put(DoctorDatabaseHelper.COL_DOCTORDETAILS,doctors.getDoctorDetails());
        values.put(DoctorDatabaseHelper.COL_APPOINTMENT,doctors.getAppoinment());
        values.put(DoctorDatabaseHelper.COL_DOCTORPHONE,doctors.getDoctorPhone());
        values.put(DoctorDatabaseHelper.COL_DOCTOREMAIL,doctors.getDoctorEmail());
        int updateId = sqLiteDatabase.update(DoctorDatabaseHelper.DOCTOR_TABLE, values,
                DoctorDatabaseHelper.COL_ID+" = ?",
                new String[]{Integer.toString(rowId)});
        this.close();
        if(updateId > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
