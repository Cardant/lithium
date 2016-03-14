package underground.university.com.lithium;

import android.net.Uri;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

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
            CountDownLatch latch = new CountDownLatch(1);
            CalendarParser parser = new CalendarParser(latch, "http://ade6-usmb-ro.grenet.fr/jsp/custom/modules/plannings/direct_cal.jsp?resources=6320,6319,6329,6328,6327,6326,6325,6324,6323,6322&projectId=3&calType=ical&login=iCalExport&password=73rosav&lastDate=2016-03-10");
            parser.Download_iCal();
            latch.await();
            parser.Parse();



            for(CalendarEvent event : parser.getEvents())
            {
                Log.i("huehuehue", event.toString());
            }
        }
        catch(Exception e)
        {
            Log.i("huehuehue", e.getMessage());
        }
    }

    @Override
    public void onStop() {
        super.onStop();

    }
}
