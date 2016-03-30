package underground.university.com.lithium;

import android.app.Application;
import android.content.ContentValues;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class BuildingSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_selection);
    }

    @Override
    public void onStart() {
        super.onStart();

        try
        {
            SimpleDateFormat urlDateFormatter = new SimpleDateFormat("yyyy-MM-dd");

            CountDownLatch latch = new CountDownLatch(1);
            CalendarParser parser = new CalendarParser(latch, "http://ade6-usmb-ro.grenet.fr/jsp/custom/modules/plannings/direct_cal.jsp?resources=6320,6319,6329,6328,6327,6326,6325,6324,6323,6322&projectId=3&calType=ical&login=iCalExport&password=73rosav&lastDate=" + urlDateFormatter.format(Calendar.getInstance().getTime()));
            parser.Download_iCal();
            latch.await();
            parser.Parse();

            ArrayList<ContentValues> values = new ArrayList<ContentValues>();

            DbHelper db = new DbHelper(getApplicationContext());
            db.wipe(Contract.Event.TABLE_NAME);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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

            db.insert(Contract.Event.TABLE_NAME, values);

            List<HashMap<String, String>> rows = db.select(Contract.Event.TABLE_NAME, Contract.Event.COLUMN_LOCATION + "='D250'");

            for(HashMap<String, String> row : rows)
            {
                Log.i("huehuehue2", row.get("start"));
            }
        }
        catch(Exception e)
        {
            Log.i("huehuehue3", e.getMessage());
        }
    }

    @Override
    public void onStop() {
        super.onStop();

    }
}
