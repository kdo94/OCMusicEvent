package edu.orangecoastcollege.cs273.kdo94.ocmusicevent;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class EventDetailsActivity extends AppCompatActivity {

    public ImageView eventImageView;
    public TextView eventTitleTextView;
    public TextView eventDateDayTextView;
    public TextView eventTimeTextView;
    public TextView eventLocationTextView;
    public TextView eventAddress1TextView;
    public TextView eventAddress2TextView;

    private String title;
    private String date;
    private String day;
    private String time;
    private String location;
    private String address1;
    private String address2;
    private String imageFileName;

    private Context context = (Context) this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        eventImageView = (ImageView) findViewById(R.id.eventImageView);
        eventTitleTextView = (TextView) findViewById(R.id.eventTitleTextView);
        eventDateDayTextView = (TextView) findViewById(R.id.eventDateDayTextView);
        eventTimeTextView = (TextView) findViewById(R.id.eventTimeTextView);
        eventLocationTextView = (TextView) findViewById(R.id.eventLocationTextView);
        eventAddress1TextView = (TextView) findViewById(R.id.eventAddress1TestView);
        eventAddress2TextView = (TextView) findViewById(R.id.eventAddress2TextView);

        Intent intentFromList = getIntent();
        title = intentFromList.getStringExtra("Title");
        date = intentFromList.getStringExtra("Date");
        day = intentFromList.getStringExtra("Day");
        time = intentFromList.getStringExtra("Time");
        location = intentFromList.getStringExtra("Location");
        address1 = intentFromList.getStringExtra("Address1");
        address2 = intentFromList.getStringExtra("Address2");

        eventTitleTextView.setText(title);
        eventDateDayTextView.setText(date + " - " + day);
        eventTimeTextView.setText(time);
        eventLocationTextView.setText(location);
        eventAddress1TextView.setText(address1);
        eventAddress2TextView.setText(address2);


        imageFileName = title.replace(" ", "" ) + ".jpeg";

        AssetManager am = context.getAssets();

        //testing for branch
        try{
           InputStream stream = am.open(imageFileName);
            Drawable image = Drawable.createFromStream(stream, title);
            eventImageView.setImageDrawable(image);
        }
        catch(IOException ex){
            Log.e("OC Music Events", "Cannot load image" + imageFileName + ex);
        }
    }
}
