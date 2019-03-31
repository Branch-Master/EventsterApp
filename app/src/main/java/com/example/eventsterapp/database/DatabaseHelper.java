package com.example.eventsterapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.eventsterapp.models.Event;
import com.example.eventsterapp.models.User;

public class DatabaseHelper extends SQLiteOpenHelper{

    //USER==========================================
    private static final String Tag = "DatabaseHelper";
    private static final String TABLE_Users = "Users";
    //private static final String users_id= "id";
    private static final String users_name = "username";
    private static final String users_pass = "pw";
    private static final String user_email = "email";
    private static final String user_bday = "bday";
    private static final String user_zodiac = "zodiac";
    private static final String user_logged = "loggedin";
    private static final String user_phone = "phone";

    //EVENTS=======================================
    private static final String TABLE_Events = "Events";
    private static final String event_name = "EventName";
    private static final String event_info = "EventInfo";
    private static final String event_groupId = "GroupID";
    private static final String event_tag = "EventTag";
    private static final String event_start = "StartDate";
    private static final String event_end = "EndDate";
    private static final String event_loc = "location";
    private static final String event_seats = "seats";
    private static final String event_vis = "visable";

    //GROUPS=======================================
    private static final String TABLE_Groups = "Groups";



    //Event( int eventSeats,Boolean vis)




    public DatabaseHelper(Context context){

        super(context, Tag, null,1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE "+ TABLE_Users +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                users_name + " VARCHAR(128)," +
                users_pass + " VARCHAR(128)," +
                user_email + " VARCHAR(128)," +
                user_bday + " VARCHAR(128)," +
                user_zodiac + " VARCHAR(128)," +
                user_logged + " boolean" +
                user_phone + "VARCHAR(8)" +
                ")";
        db.execSQL(createUserTable);
        System.out.println("user TABLE CREATED ==================================");

        String createEventTable = "CREATE TABLE " + TABLE_Events +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                event_name + " VARCHAR(128), " +
                event_info + " TEXT, " +
                event_groupId + " INTEGER, " +
                event_tag + " VARCHAR(128), " +
                event_start + " VARCHAR(128), " +
                event_end + " VARCHAR(128), " +
                event_loc + " VARCHAR(128), " +
                event_seats + " INTEGER, " +
                event_vis + " INTEGER" +
                ")";

        db.execSQL(createEventTable);
        System.out.println("event TABLE CREATED ==================================");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Users);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Events);
        onCreate(db);
    }

    public boolean addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues contentValues = new ContentValues();
        contentValues.put(users_name,user.getUsername());
        contentValues.put(users_pass,user.getPassword());
        contentValues.put(user_email, user.getEmail());
        contentValues.put(user_zodiac,user.getZodiac());
        contentValues.put(user_bday,user.getBirthday());
        contentValues.put(user_logged,false);


        Log.d(Tag, "addData: Adding " + user.getUsername() + " to " + TABLE_Users);

        long result = db.insert(TABLE_Users, null, contentValues);

        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getAllUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_Users;
        Cursor data = db.rawQuery(query,null);
        return data;

    }


    public boolean addEvent(Event event){
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues contentValues = new ContentValues();
        contentValues.put(event_name,event.getEventName());
        contentValues.put(event_info,event.getEventInfo());
        contentValues.put(event_groupId,event.getGroupID());
        contentValues.put(event_tag, event.getTag());
        contentValues.put(event_start, event.getStartDate());
        contentValues.put(event_end, event.getEndDate());
        contentValues.put(event_end, event.getEndDate());
        contentValues.put(event_loc, event.getLocation());
        contentValues.put(event_seats, event.getEventSeats());
        contentValues.put(event_vis, event.getVisable());

        System.out.println(event_groupId + " " + event.getGroupID() );



        Log.d(Tag, "addData: Adding " + event.getEventName() + " to " + TABLE_Events);

        long result = db.insert(TABLE_Events, null, contentValues);

        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getAllEvents(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_Events;
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    public void dropAllTables(){
        SQLiteDatabase db = this.getWritableDatabase();
        onUpgrade(db,0,1);
    }


}






