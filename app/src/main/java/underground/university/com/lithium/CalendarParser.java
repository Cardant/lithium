package underground.university.com.lithium;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Created by vivancoe on 01/03/2016.
 */
public class CalendarParser {

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url = "";
    private String iCalContent = "";
    private List<CalendarEvent> events = new ArrayList<CalendarEvent>();

    public CalendarParser(String url)
    {
        setUrl(url);
    }

    public void Download_iCal() throws Exception
    {
        URL url = new URL(getUrl());
        URLConnection connection = url.openConnection();
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        iCalContent = "";
        String line;

        while((line = streamReader.readLine()) != null)
            iCalContent += line;

        streamReader.close();
    }

    public void Parse()
    {

    }
}
