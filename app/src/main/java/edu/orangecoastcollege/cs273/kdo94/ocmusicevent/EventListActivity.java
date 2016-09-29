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
            allMusicEvents = JSONLoader.loadJSONFromAsset();
        }
        catch(IOException ex){
            Log.e("OC Music Events","Error loading JSON data.");
        }

        setListAdapter(new ArrayAdapter<MusicEvent>(context, R.layout.music_event_list_item_layout, ));

        // setContentView(R.layout.activity_event_list);
    }

    protected void onListItemClick(ListView l, View v, int pos, long id){
        String title = MusicEvent.titles[pos];
        String details = MusicEvent.details[pos];

        Intent detailsIntent = new Intent(this, EventDetailsActivity.class);
        detailsIntent.putExtra("Title", title);
        detailsIntent.putExtra("Details", details);
        startActivity(detailsIntent);
    }
}
