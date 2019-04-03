package com.example.eventsterapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.eventsterapp.models.Event;
import com.example.eventsterapp.models.Group;
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
    private static final String group_name = "GroupName";
    private static final String group_info = "Group_info";
    private static final String group_vis = "vis";

    //MEMBERS=======================================
    private static final String TABLE_members = "Members";
    private static final String group_id = "groupID";
    private static final String name_of_group = "groupName";
    private static final String user_id = "userID";
    private static final String user_name = "username";






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
                user_logged + " boolean, " +
                user_phone + " VARCHAR(8)" +
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

        String createGroupTable = "CREATE TABLE " + TABLE_Groups +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                group_name + " VARCHAR(128), " +
                group_info + " VARCHAR(128), " +
                group_vis + " INTEGER" +
                ")";
        db.execSQL(createGroupTable);

        String createMembersTable = "CREATE TABLE " + TABLE_members + "(" +
                group_id + " INTEGER NOT NULL REFERENCES " + TABLE_Groups + "(ID)," +
                user_id + " INTEGER NOT NULL REFERENCES " + TABLE_Users + "(ID)" + ")";
        db.execSQL(createMembersTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Users);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Events);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Groups);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_members);
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
        contentValues.put(user_phone,user.getPhone());
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

    public User getUserById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_Users +
                " WHERE ID = " + id;
        Cursor data = db.rawQuery(query, null);

        while (data.moveToNext()){

            String name = data.getString(1);
            String pass = data.getString(2);
            String email = data.getString(3);
            String bday = data.getString(4);
            String zodiac = data.getString(5);
            String phone = data.getString(7);
            return new User(id, name, pass, email, bday, phone, zodiac);
        }
        return new User(id,"ekkert fannst","ekkert fannst","ekkert fannst", "ekkert fannst", "ekkert fannst", "ekkert fannst");

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

    public Event getEventById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_Events +
                " WHERE ID = " + id ;
        Cursor data = db.rawQuery(query,null);

        while(data.moveToNext()){
            String eventName = data.getString(1);
            String eventInfo = data.getString(2);
            int groupID = data.getInt(3);
            String tag = data.getString(4);
            String startDate = data.getString(5);
            String endDate = data.getString(6);
            String location = data.getString(7);
            int eventSeats = data.getInt(8);
            int vis = data.getInt(9);

            return new Event(eventName, eventInfo, groupID,tag,startDate,endDate,location,eventSeats,vis);
        }
        return new Event("ekkert fannst","ekkert fannst",-1,"ekkert fannst","ekkert fannst","ekkert fannst","ekkert fannst",-1,1);

    }


    public boolean addGroup(Group group){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(group_name,group.getGroupName());
        contentValues.put(group_info, group.getGroupInfo());
        contentValues.put(group_vis,group.getVisable());


        Log.d(Tag, "addData: Adding " + group.getGroupName() + " to " + TABLE_Groups);

        long result = db.insert(TABLE_Groups, null, contentValues);

        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }


    public Cursor getAllGroups(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_Groups;
        Cursor data = db.rawQuery(query,null);
        return data;
    }


    public Group getGroupById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_Groups +
                " WHERE ID = " + id ;
        Cursor data = db.rawQuery(query,null);

        while(data.moveToNext()){
            String groupname = data.getString(1);
            String info = data.getString(2);
            int vis = data.getInt(3);

            return new Group( groupname, info ,vis);
        }
        return new Group("ekkert fannst","ekkert fannst",1);
    }


    public Boolean addUserToGroup(int userid, int groupid){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(group_id,groupid);
        contentValues.put(user_id, groupid);

        Log.d(Tag, "addData: Adding " + "user: "+ userid + " to group: " + groupid);

        long result = db.insert(TABLE_Groups, null, contentValues);

        if(result == -1){
            return false;
        }
        else{
            return true;
        }

    }

    public Boolean emailUsed(String email){
        String query = "SELECT * FROM " + TABLE_Users + " WHERE " + user_email + " = " + "\'" + email + "\'";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor data = db.rawQuery(query,null);

        if(data.moveToNext()){
            return true;
        }
        else{
            return false;
        }
    }

    public User findUserByEmail(String email){
        String query = "SELECT * FROM " + TABLE_Users + " WHERE " + user_email + " = " + "\'" + email + "\'";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor data = db.rawQuery(query,null);

        if( data.moveToNext()){
            int id = data.getInt(0);
            String name = data.getString(1);
            String pass = data.getString(2);
            String uemail = data.getString(3);
            String bday = data.getString(4);
            String zodiac = data.getString(5);
            String phone = data.getString(7);
            return new User(id,name, pass, uemail, bday,phone,zodiac);
        }
        else{
            return null;
        }
    }

    public void changeUsername(int id,String name){
        String query = "Update " + TABLE_Users +
                " SET " + user_name + " = " + "\'" + name + "\'" + " WHERE ID = " + id;


        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(query);
    }

    public void changeEmail(int id,String email){
        String query = "Update " + TABLE_Users +
                " SET " + user_email + " = " + "\'" + email + "\'" + " WHERE ID = " + id;


        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(query);
    }

    public void changePhone(int id,String phone){
        String query = "Update " + TABLE_Users +
                " SET " + user_phone + " = " + "\'" + phone + "\'" + " WHERE ID = " + id;

        System.out.println(query);
        System.out.println(phone.length());

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(query);
    }

    public void changeZodiac(int id,String zodiac){
        String query = "Update " + TABLE_Users +
                " SET " + user_zodiac + " = " + "\'" + zodiac + "\'" + " WHERE ID = " + id;


        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(query);
    }





    public Boolean usernameTaken(String name){
        String query = "SELECT * FROM " + TABLE_Users + " WHERE " + user_name + " = " + "\'" + name + "\'";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor data = db.rawQuery(query,null);

        if(data.moveToNext()){
            return true;
        }
        else{
            return false;
        }
    }

    public User validateLogin(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_Users + " WHERE " +
                user_name + " = " + "\'" + username + "\'" +
                " AND " + users_pass + " = " + "\'" + password + "\'";

        Cursor data = db.rawQuery(query,null);

        if(data.moveToNext()){
            String name = data.getString(1);
            String pass = data.getString(2);
            String email = data.getString(3);
            return new User(name, pass, email);
        }
        else{
            return null;
        }

    }



    //TODO Eyða aðferð "dropAllData"
    public void dropAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        onUpgrade(db,0,1);
    }




}






