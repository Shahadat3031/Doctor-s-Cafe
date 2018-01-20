package net.a6te.lazycoder.doctorscafe;

/**
 * Created by Hannan Talukder on 4/9/2017.
 */

public class Prescription {
    private int id;
    private String doctorName;
    private String doctorDetails;
    private String prescriptionDate;
    private String prescriptionImage;

    public Prescription(String doctorName, String doctorDetails, String prescriptionDate, String prescriptionImage) {
        this.doctorName = doctorName;
        this.doctorDetails = doctorDetails;
        this.prescriptionDate = prescriptionDate;
        this.prescriptionImage = prescriptionImage;
    }

    public Prescription(int id, String doctorName, String doctorDetails, String prescriptionDate, String prescriptionImage) {
        this.id = id;
        this.doctorName = doctorName;
        this.doctorDetails = doctorDetails;
        this.prescriptionDate = prescriptionDate;
        this.prescriptionImage = prescriptionImage;
    }

    public Prescription() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorDetails() {
        return doctorDetails;
    }

    public void setDoctorDetails(String doctorDetails) {
        this.doctorDetails = doctorDetails;
    }

    public String getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(String prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    public String getPrescriptionImage() {
        return prescriptionImage;
    }

    public void setPrescriptionImage(String prescriptionImage) {
        this.prescriptionImage = prescriptionImage;
    }
}
