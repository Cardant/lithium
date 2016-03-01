package underground.university.com.lithium;

import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class BuildingSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_selection);

        Log.i("huehuehue", "lol");
        CalendarParser parser = new CalendarParser("http://ade6-usmb-ro.grenet.fr/jsp/custom/modules/plannings/direct_cal.jsp?resources=6320,6319,6329,6328,6327,6326,6325,6324,6323,6322&projectId=3&calType=ical&login=iCalExport&password=73rosav&lastDate=2016-03-07");

        try
        {
            parser.Download_iCal();
            //parser.Parse();
            //Log.i("huehuehue", parser.getEvents().get(0).toString());
        }
        catch(Exception e)
        {
            Log.i("huehuehue", "error");
        }
    }
}
