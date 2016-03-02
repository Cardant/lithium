package underground.university.com.lithium;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by vivancoe on 01/03/2016.
 */
public class CalendarEvent {

    public Calendar getStart() {
        return start;
    }

    public void setStart(Calendar start) {
        this.start = start;
    }

    public Calendar getEnd() {
        return end;
    }

    public void setEnd(Calendar end) {
        this.end = end;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private Calendar start;
    private Calendar end;
    private String caption;
    private String location;
    private String description;

    public CalendarEvent()
    {

    }

    @Override
    public String toString()
    {
        String text = "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");

        text += "Start : " + formatter.format(start.getTime()) + "\n";
        text += "End : " + formatter.format(end.getTime()) + "\n";
        text += "Caption : " + caption.toString() + "\n";
        text += "Location : " + location.toString() + "\n";
        text += "Description : " + description.toString();
        return text;
    }
}
