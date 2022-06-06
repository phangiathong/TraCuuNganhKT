package com.academy.tracuunganhkt.database;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.academy.tracuunganhkt.Economics;

public class Database extends SQLiteOpenHelper {

    //Database name
    private static String DATABASE_NAME = "tracuunganhkt";

    //Table KTQD
//    private static String TABLE_KTQD = "ktqd";
//    private static String ID_NGANH = "idnganh";
//    private static String CAP1 = "cap1";
//    private static String CAP2 = "cap2";
//    private static String CAP3 = "cap3";
//    private static String CAP4 = "cap4";
//    private static String CAP5 = "cap5";
//    private static String NAM_BH = "nambh";
//    private static String TEN_NGANH = "tennganh";
//    private static String CAP = "cap";
//    private static String MA = "ma";
//    private static int VERSION = 1;

    //Table GIAI_THICH
//    private static String TABLE_GIAI_THICH = "giaithich";
//    private static String ID_GIAITHICH = "idgiaithich";
//    private static String NAM = "nam";
//    private static String NOI_DUNG = "noidung";

    //Create table TKQD
//    private String SQLQuery = "CREATE TABLE " + TABLE_KTQD + " ( "
//            + ID_NGANH + " INTEGER PRIMARY KEY, "
//            + CAP1 + " TEXT, "
//            + CAP2 + " TEXT, "
//            + CAP3 + " TEXT, "
//            + CAP4 + " TEXT, "
//            + CAP5 + " TEXT, "
//            + NAM_BH + " TEXT, "
//            + TEN_NGANH + " TEXT, "
//            + CAP + " INTEGER, "
//            + MA + " TEXT) ";
//
//    //Create table GIAI_THICH
//    private String SQLQuery1 = " CREATE TABLE " + TABLE_GIAI_THICH + " ( "
//            + ID_GIAITHICH + " INTEGER PRIMARY KEY, "
//            + NAM + " TEXT, "
//            + NOI_DUNG + " TEXT, "
//            + ID_NGANH + " INTEGER , FOREIGN KEY ( " + ID_NGANH + " ) REFERENCES " +
//            TABLE_KTQD + "(" + ID_NGANH +"))";

    //Table KTQD GIAITHICH
    private static String TABLE_KTQD_GIAITHICH = "ktqdgiaithich";
    private static String MA_NGANH = "manganh";
    private static String CAP = "cap";
    private static String TEN = "ten";
    private static String NOI_DUNG = "noidung";
    private static int VERSION = 1;

    //Create table KTQD GIAITHICH
    private String SQLQuery = "CREATE TABLE " + TABLE_KTQD_GIAITHICH + " ( "
            + MA_NGANH + " TEXT, "
            + CAP + " TEXT, "
            + TEN + " TEXT, "
            + NOI_DUNG + " TEXT) ";

    public Database(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    //Add KTQD
    public void addEconomics(Economics economics){
        SQLiteDatabase db =getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MA_NGANH, economics.getId());
        values.put(CAP, economics.getLevel());
        values.put(TEN, economics.getName());
        values.put(NOI_DUNG, economics.getContent());

        db.insert(TABLE_KTQD_GIAITHICH, null, values);
        db.close();
    }

    //getData Economic
    public Cursor getDataEconomics() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_KTQD_GIAITHICH, null);
        return cursor;
    }

    //getData by name
    public Cursor getDataByName(String input) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_KTQD_GIAITHICH +" WHERE TEN COLLATE UTF8CI LIKE '%"+input+"%'",null);
        return cursor;
    }

    //getData by Code
    public Cursor getDataByCode(String input) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_KTQD_GIAITHICH +" WHERE "+MA_NGANH +" LIKE '%"+input+"%'",null);
        return cursor;
    }

    //getData by content
    public Cursor getDataByContent(String input) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_KTQD_GIAITHICH +" WHERE "+NOI_DUNG +" COLLATE UTF8CI LIKE '%"+input+"%'",null);
        return cursor;
    }
}
