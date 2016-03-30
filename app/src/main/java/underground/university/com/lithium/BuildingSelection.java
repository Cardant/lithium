package underground.university.com.lithium;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.CountDownLatch;

public class BuildingSelection extends AppCompatActivity {

    private Button btnBatD;
    public static CalendarParser parser;
    public static boolean first = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_selection);

        btnBatD = (Button)findViewById(R.id.btnBatimentD);
        btnBatD.setOnClickListener(btnBatD_Click);
    }

    private View.OnClickListener btnBatD_Click = new View.OnClickListener() {
        public void onClick(View v) {
            startActivity(new Intent(BuildingSelection.this, Maps.class));
        }
    };

    @Override
    public void onStart() {
        super.onStart();

        if(first)
        {
            first = false;
            try
            {
                // Url date formatter
                SimpleDateFormat urlDateFormatter = new SimpleDateFormat("yyyy-MM-dd");

                CountDownLatch latch = new CountDownLatch(1);
                // New calendar parser
                parser = new CalendarParser(latch, "http://ade6-usmb-ro.grenet.fr/jsp/custom/modules/plannings/direct_cal.jsp?resources=6320,6319,6329,6328,6327,6326,6325,6324,6323,6322&projectId=3&calType=ical&login=iCalExport&password=73rosav&lastDate=" + urlDateFormatter.format(Calendar.getInstance().getTime()));
                // Download iCal file
                parser.Download_iCal();
                latch.await();
                // Extract events from downloaded file
                parser.Parse();

                if(parser.getEvents().size() > 0)
                {
                    // Values to put in database
                    ArrayList<ContentValues> values = new ArrayList<ContentValues>();

                    // Instanciate database object
                    DbHelper db = new DbHelper(getApplicationContext());
                    // Wipe the database and put newest events
                    db.wipe(Contract.Event.TABLE_NAME);

                    // Event date formatter
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    // Prepare list of values
                    for(CalendarEvent event : parser.getEvents())
                    {
                        ContentValues value = new ContentValues();
                        value.put(Contract.Event.COLUMN_CAPTION, event.getCaption());
                        value.put(Contract.Event.COLUMN_DESCRIPTION, event.getDescription());
                        value.put(Contract.Event.COLUMN_END, formatter.format(event.getEnd().getTime()));
                        value.put(Contract.Event.COLUMN_LOCATION, event.getLocation());
                        value.put(Contract.Event.COLUMN_START, formatter.format(event.getStart().getTime()));
                        values.add(value);
                        Log.i("huehuehue", event.toString());
                    }

                    // Put values in the database
                    db.insert(Contract.Event.TABLE_NAME, values);
                }
            }
            catch(Exception e)
            {
                Log.i("huehuehue3", e.getMessage());
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();

    }
}
