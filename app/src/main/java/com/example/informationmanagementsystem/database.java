package com.example.informationmanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {


    public database(Context context) {
        super(context, "students.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS student_Table(Name TEXT, Dept TEXT, Phone INTEGER, ID INTEGER PRIMARY KEY)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertData(String Name, String Department , int Phone, int ID_Num){
        SQLiteDatabase bd = getWritableDatabase();
        ContentValues newValue = new ContentValues();
        newValue.put("Name", Name);
        newValue.put("Dept", Department);
        newValue.put("Phone", Phone);
        newValue.put("ID", ID_Num );
        long newRowId  =bd.insert ("student_Table", null,newValue);
        bd.close();
    }

    public Cursor readData(){
        SQLiteDatabase bd = getReadableDatabase();

        return bd.query("student_Table",null,null,null,null,null,null);

    }

    public void UpdateDate(String Name, String Department , int Phone, int ID_Num){
        SQLiteDatabase bd = getWritableDatabase();
        ContentValues newValue = new ContentValues();
        newValue.put("Name", Name);
        newValue.put("Dept", Department);
        newValue.put("Phone", Phone);
        newValue.put("ID", ID_Num );
        long newRowId  =bd.update ("student_Table", newValue,"ID_Num=?",new String[]{String.valueOf(ID_Num)});
        bd.close();
    }

    public void Delete(int ID_Num){
        SQLiteDatabase bd = getWritableDatabase();
        bd.delete("student_Table","ID=?",new String[]{String.valueOf(ID_Num)});
    }
}
