package com.example.informationmanagementsystem;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private database db = new database(this);



    private EditText NameEdit,DeptEdit,PhoneNum,StuID;
    private Button RegisterButton, ViewButton, UpDateButton, DeleteButton;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);


        NameEdit= findViewById(R.id.nameInput);
        DeptEdit = findViewById(R.id.deptInput);
        PhoneNum= findViewById(R.id.NumberInput);
        StuID= findViewById(R.id.ID_input);


        RegisterButton = findViewById(R.id.reGister);
        ViewButton = findViewById(R.id.viewAll);
        UpDateButton = findViewById(R.id.UpdateBut);
        DeleteButton = findViewById(R.id.deleteBut);










        RegisterButton.setOnClickListener(v -> {

            String name = NameEdit.getText().toString().toUpperCase().trim();
            String dept = DeptEdit.getText().toString().toUpperCase().trim();
            String phoneText = PhoneNum.getText().toString().trim();
            String idText = StuID.getText().toString().trim();

            if(name.isEmpty() || dept.isEmpty() || phoneText.isEmpty() || idText.isEmpty()){

                Toast.makeText(this,"Please enter all student details!",Toast.LENGTH_SHORT).show();
                return;
            }

            int phone_number = Integer.parseInt(phoneText);
            int studentID = Integer.parseInt(idText);

            db.insertData(name, dept, phone_number, studentID);

            Toast.makeText(this,"Student Added Successfully.",Toast.LENGTH_SHORT).show();
            NameEdit.setText("");
            DeptEdit.setText("");
            PhoneNum.setText("");
            StuID.setText("");

        });

        ViewButton.setOnClickListener(v -> {
            viewStudents();

        });

        UpDateButton.setOnClickListener(v -> {

            String name = NameEdit.getText().toString().toUpperCase().trim();
            String dept = DeptEdit.getText().toString().toUpperCase().trim();
            String phoneText = PhoneNum.getText().toString().trim();
            String idText = StuID.getText().toString().trim();

            if(idText.isEmpty()){
                Toast.makeText(this,"Enter the student ID to update.",Toast.LENGTH_SHORT).show();
                return;
            }

            int phone_number = Integer.parseInt(phoneText);
            int studentID = Integer.parseInt(idText);

            db.UpdateDate(name, dept, phone_number, studentID);

            Toast.makeText(this,"Student Updated Successfully.",Toast.LENGTH_SHORT).show();

            NameEdit.setText("");
            DeptEdit.setText("");
            PhoneNum.setText("");
            StuID.setText("");

        });




        DeleteButton.setOnClickListener(v -> {

            String idText = StuID.getText().toString().trim();

            if(idText.isEmpty()){
                Toast.makeText(this,"Enter the ID of the student to delete.",Toast.LENGTH_SHORT).show();
                return;
            }

            int studentID = Integer.parseInt(idText);

            db.Delete(studentID);

            Toast.makeText(this,"Student Deleted Successfully.",Toast.LENGTH_SHORT).show();
            NameEdit.setText("");
            DeptEdit.setText("");
            PhoneNum.setText("");
            StuID.setText("");

        });







    }

    public void viewStudents() {

        Cursor cursor = db.readData();

        if(cursor.getCount() == 0){
            showMessage("Error","No students found");
            return;
        }

        StringBuffer buffer = new StringBuffer();

        while(cursor.moveToNext()){

            buffer.append("NAME: " + cursor.getString(0) + "\n");
            buffer.append("DEPT: " + cursor.getString(1) + "\n");
            buffer.append("Phone: " + cursor.getString(2) + "\n");
            buffer.append("ID: " + cursor.getString(3) + "\n\n");

        }

        showMessage("Students", buffer.toString());
    }

    public void showMessage(String title, String message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);

        builder.show();
    }
}
