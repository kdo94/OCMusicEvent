package edu.orangecoastcollege.cs273.kdo94.ocmusicevent;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;


public class EventListActivity extends ListActivity {

    private ListView eventsListView;
    private Context context = this;
    private ArrayList<MusicEvent> allMusicEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        eventsListView = (ListView) findViewById(R.id.eventsListView);
        // setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, MusicEvent.titles));

        try{
            allMusicEvents = JSONLoader.loadJSONFromAsset(context);
        }
        catch(IOException ex){
            Log.e("OC Music Events","Error loading JSON data.");
        }

        setListAdapter(new ArrayAdapter<MusicEvent>(context, R.layout.music_event_list_item_layout, allMusicEvents));

        // setContentView(R.layout.activity_event_list);
    }

    protected void onListItemClick(ListView l, View v, int pos, long id){
        Intent detailsIntent = new Intent(this, EventDetailsActivity.class);
        MusicEvent clickedEvent = allMusicEvents.get(pos);
        String title = clickedEvent.getTitle();
        String date = clickedEvent.getDate();
        String day = clickedEvent.getDay();
        String location = clickedEvent.getLocation();
        String address1 = clickedEvent.getAddress1();
        String address2 = clickedEvent.getAddress2();
        detailsIntent.putExtra("Title", title);
        detailsIntent.putExtra("Date", date);
        detailsIntent.putExtra("Day", day);
        detailsIntent.putExtra("Location", location);
        detailsIntent.putExtra("Address1", address1);
        detailsIntent.putExtra("Address2", address2);
        startActivity(detailsIntent);
    }
}
