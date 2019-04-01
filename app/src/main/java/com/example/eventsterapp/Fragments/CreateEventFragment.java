package com.example.eventsterapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.example.eventsterapp.R;
import com.example.eventsterapp.database.DatabaseHelper;
import com.example.eventsterapp.models.Event;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.fragment.app.Fragment;

public class CreateEventFragment extends Fragment {

    private DatabaseHelper mDatabasehelper;

    private EditText new_event_name;
    private EditText new_event_info;
    private CalendarView new_event_start_date;
    private EditText new_event_start_time;
    private CalendarView new_event_end_date;
    private EditText new_event_end_time;
    private EditText new_event_loc;
    private Button create_event;
    private EditText new_event_seats;
    private RadioGroup new_event_vis;

    private int sday;
    private int smonth;
    private int syear;
    private int eday;
    private int emonth;
    private int eyear;
    private int vis =0;




    private View.OnClickListener mCreateButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {




            String eventName = new_event_name.getText().toString();
            String info = new_event_info.getText().toString();
            String sdate = sday + "/" + smonth +"/"+ syear +" at " + new_event_start_time.getText().toString();
            String edate = eday + "/" + emonth +"/"+ eyear + " at " + new_event_end_time.getText().toString();
            String loc = new_event_loc.getText().toString();
            int seats = Integer.parseInt(new_event_seats.getText().toString());


            switch (new_event_vis.getCheckedRadioButtonId()){
                case R.id.event_public_create:
                    vis = 1;
                    break;
                case R.id.event_private_create:
                    vis = 0;
                    break;
            }

            System.out.println("Name: " + eventName);
            System.out.println("Info: " + info );
            System.out.println("Starts: "+ sdate);
            System.out.println("Ends: " + edate);
            System.out.println("Location: " + loc );
            System.out.println("Seats: " + seats);
            System.out.println("visable: " + vis);


            Event newEvent = new Event(new Long(13),eventName,info,1,"tag",sdate,edate,loc,seats,vis);

            mDatabasehelper.addEvent(newEvent);

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Context context = getContext();
        mDatabasehelper = new DatabaseHelper(context);

        final View v = inflater.inflate(R.layout.fragment_create_event, container, false);
        new_event_name = (EditText) v.findViewById(R.id.name_create);
        new_event_info = (EditText) v.findViewById(R.id.Describtion_create_event);
        new_event_start_date = (CalendarView) v.findViewById(R.id.event_startDate);
        new_event_end_date = (CalendarView) v.findViewById(R.id.event_end_date);
        new_event_start_time = (EditText) v.findViewById(R.id.event_start_time);
        new_event_end_time = (EditText) v.findViewById(R.id.event_end_time);
        new_event_loc = (EditText) v.findViewById(R.id.loc_create);
        new_event_seats = (EditText) v.findViewById(R.id.seats_create);
        new_event_vis = (RadioGroup) v.findViewById(R.id.event_vis_create);
        create_event = (Button) v.findViewById(R.id.create_event_button);

        new_event_start_date.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                sday = dayOfMonth;
                smonth = month;
                syear =  year;
            }
        });

        new_event_end_date.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                eday = dayOfMonth;
                emonth = month;
                eyear =  year;
            }
        });

        create_event.setOnClickListener(mCreateButton);

        return v;
    }


}
