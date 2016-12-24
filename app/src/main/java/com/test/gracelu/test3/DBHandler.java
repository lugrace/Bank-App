package com.test.gracelu.test3;


/**
 * Created by grace on 12/22/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME = "locationsInfo";
    private static final String TABLE_LOCATIONS="locations";
    private static final String KEY_ID="id";
    private static final String KEY_NAME="name";
    private static final String KEY_STREET="street";
    private static final String KEY_CITY="city";
    private static final String KEY_ZIPCODE="zipcode";

//    public DBHandler(Context context) {
//        //super(context, DATABASE_NAME, TABLE_LOCATIONS, DATABASE_VERSION);
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        //SQLiteDatabase db = this.getWritableDatabase();
//        //onCreate(db);
//    }
    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_LOCATIONS + "(" + KEY_ID
                + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_STREET + " TEXT,"
                + KEY_CITY + " TEXT," + KEY_ZIPCODE + " INTEGER" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
        //Log.d("Hello: ", "Im creating a database");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATIONS);
        onCreate(db);
    }

    //Adding a new location
    public void addLocation(Location location){
        SQLiteDatabase db = this.getWritableDatabase();

        //check if its already in the database
        List<String> temp = getAllStreets();
        if (!temp.contains(location.getStreet())){
            ContentValues values = new ContentValues();
            values.put(KEY_NAME, location.getName());
            values.put(KEY_CITY, location.getCity());
            values.put(KEY_ZIPCODE, location.getZipcode());
            values.put(KEY_STREET, location.getStreet());
            //inserting row
            db.insert(TABLE_LOCATIONS, null, values);
        }
        db.close();
    }

    //getting a location
    public Location getLocation(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_LOCATIONS, new String[]{
                KEY_ID, KEY_NAME, KEY_STREET, KEY_CITY, KEY_ZIPCODE
        }, KEY_ID + "=?", new String[] {String.valueOf(id)}, null, null, null, null);
        if(cursor!=null)
            cursor.moveToFirst();
        Location contact = new Location(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), Integer.parseInt(cursor.getString(4)));

        //return location
        return contact;
    }

    //getting all locations
    public List<Location> getAllLocations(){
        List<Location> locationList = new ArrayList<Location>();
        String selectQuery = "SELECT * FROM " + TABLE_LOCATIONS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{
                Location location = new Location();
                location.setId(Integer.parseInt(cursor.getString(0)));
                location.setName(cursor.getString(1));
                location.setStreet(cursor.getString(2));
                location.setCity(cursor.getString(3));
                location.setZipcode(Integer.parseInt(cursor.getString(4)));

                locationList.add(location);
            } while (cursor.moveToNext());
        }
        return locationList;
    }

    //getting all locations
    public List<String> getAllStreets(){
        List<String> locationList = new ArrayList<String>();
        String selectQuery = "SELECT * FROM " + TABLE_LOCATIONS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{
                String st = cursor.getString(2); //street
                locationList.add(st);
            } while (cursor.moveToNext());
        }
        return locationList;
    }

    //getting how many locations there are
    public int getLocationsCount(){
        String countQuery = "SELECT * FROM " + TABLE_LOCATIONS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    public int updateLocation(Location location){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, location.getName());
        values.put(KEY_STREET, location.getStreet());
        values.put(KEY_CITY, location.getCity());
        values.put(KEY_ZIPCODE, location.getZipcode());

        //updating the row
        return db.update(TABLE_LOCATIONS, values, KEY_ID + " = ?", new String[]{String.valueOf(
                location.getId())});
    }

    public void deleteLocation(Location location){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LOCATIONS, KEY_ID + " = ?", new String[]{String.valueOf(location.getId())});
        db.close();
    }

    //Delete all locations
    public void removeAll()
    {
        SQLiteDatabase db = this.getWritableDatabase(); // helper is object extends SQLiteOpenHelper
        db.delete(this.TABLE_LOCATIONS, null, null);
    }

}
