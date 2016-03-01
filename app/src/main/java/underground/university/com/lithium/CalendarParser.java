package underground.university.com.lithium;

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

    public CalendarParser(String url)
    {
        setUrl(url);
    }

    public void Download_iCal()
    {

    }
}
