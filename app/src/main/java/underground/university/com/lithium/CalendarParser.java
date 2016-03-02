package underground.university.com.lithium;

import android.os.AsyncTask;
import android.util.Log;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by vivancoe on 01/03/2016.
 */
public class CalendarParser extends AsyncTask {

    public String getUrl() {
        return url;
    }

    private String url = "";

    public String get_iCalContent() {
        return iCalContent;
    }

    private String iCalContent = "";

    public List<CalendarEvent> getEvents() {
        return events;
    }

    private List<CalendarEvent> events = new ArrayList<CalendarEvent>();

    private CountDownLatch latch;

    public CalendarParser(CountDownLatch latch, String url)
    {
        this.url = url;
        this.latch = latch;
    }

    public void Download_iCal()
    {
        execute();
    }

    private Calendar ParseDate(String input)
    {
        Calendar date = Calendar.getInstance();
        int year = Integer.parseInt(input.substring(0, 4));
        int month = Integer.parseInt(input.substring(4, 6));
        int day = Integer.parseInt(input.substring(6, 8));
        int hours = Integer.parseInt(input.substring(9, 11));
        int minutes = Integer.parseInt(input.substring(11, 13));
        int seconds = Integer.parseInt(input.substring(13, 15));
        date.set(year, month, day, hours, minutes, seconds);
        return date;
    }

    private String GetValue(String input, String property)
    {
        return input.replace(property, "");
    }

    public void Parse()
    {
        boolean readingEvent = false;
        CalendarEvent event = new CalendarEvent();
        events.clear();

        for(String line : get_iCalContent().split("\r\n"))
        {
            line = line.toUpperCase();

            if(readingEvent)
            {
                if(line.startsWith("DTSTART:"))
                    event.setStart(ParseDate(GetValue(line, "DTSTART:")));
                else if(line.startsWith("DTEND:"))
                    event.setEnd(ParseDate(GetValue(line, "DTEND:")));
                else if(line.startsWith("SUMMARY:"))
                    event.setCaption(GetValue(line, "SUMMARY:"));
                else if(line.startsWith("LOCATION:"))
                    event.setLocation(GetValue(line, "LOCATION:"));
                else if(line.startsWith("DESCRIPTION:"))
                    event.setDescription(GetValue(line, "DESCRIPTION:"));
                else if(line.startsWith("END:VEVENT"))
                {
                    readingEvent = false;
                    events.add(event);
                }
            }
            else
            {
                if(line.startsWith("BEGIN:VEVENT"))
                {
                    readingEvent = true;
                    event = new CalendarEvent();
                }
            }
        }
    }

    @Override
    protected Object doInBackground(Object[] params) {

        try
        {
            Log.i("lol", "début");

            URL url = new URL(this.url);
            URLConnection connection = url.openConnection();

            BufferedReader streamReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            iCalContent = "";
            int charCode;

            while((charCode = streamReader.read()) != -1)
                iCalContent += (char)charCode;

            streamReader.close();

            Log.i("lol", "fin");
        }
        catch(Exception e)
        {
            Log.i("error", e.getMessage());
        }

        latch.countDown();

        return null;
    }
}
