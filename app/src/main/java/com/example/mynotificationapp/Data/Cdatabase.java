package com.example.mynotificationapp.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.mynotificationapp.Data.Cmethods;
import com.example.mynotificationapp.Data.Cparams;


public class Cdatabase extends SQLiteOpenHelper {
    public Cdatabase(@Nullable Context context) {
        super(context, Cparams.DB_NAME,null,Cparams.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String create = "CREATE TABLE " + Cparams.TABLE_NAME + " ("
                + Cparams.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Cparams.KEY_CLICKED + " TEXT)";
        Log.d("cdbdipali", "Query being run is : " + create);

        db.execSQL(create);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }

    public void addData(Cmethods notify){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Cparams.KEY_CLICKED, notify.getTitle());

        db.insert(Cparams.TABLE_NAME, null, values);
        Log.d("dbdipali", "Successfully inserted");
        db.close();
    }

    public int getCount(){
        String query = "SELECT * FROM "+ Cparams.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query,null);
        return c.getCount();
    }

    public boolean isExist(String click){
        boolean x = false;
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT * FROM " + Cparams.TABLE_NAME;
        System.out.println("cursor");
        Cursor cursor = null;
        try{
            cursor = db.rawQuery(select, null);
        }catch (Exception e){
            Log.i("Ye  ", String.valueOf(e));
        }

        while(cursor.moveToNext()){
            System.out.println(cursor.getString(cursor.getColumnIndex("checked")));
            if(cursor.getString(cursor.getColumnIndex("checked")).equals(click)){
                x = true;
                break;
            }
        }
        db.close();
        return x;
    }

}
