package sreimerdevelopment.fragmentedpodcastdownloader;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by sreim on 7/7/2016.
 */
public class ParseApplication {
    private String xmlData;
    private ArrayList<Application> applications;

    public ParseApplication(String xmlData) {
        this.xmlData = xmlData;
        applications = new ArrayList<>();
    }

    public ArrayList<Application> getApplications() {
        return applications;
    }
    public boolean process() {
        boolean status = true;
        Application currentRecord = null;
        boolean inEntry = false;
        String textValue = "";

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(this.xmlData));
            int eventType = xpp.getEventType();

            while(eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = xpp.getName();
                switch(eventType) {
                    case XmlPullParser.START_TAG:
//                        Log.d("Parse Applications", "Starting tag for" + tagName);
                        if(tagName.equalsIgnoreCase("item")) {
                            inEntry = true;
                            currentRecord = new Application();
                        }
                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;
                    case XmlPullParser.END_TAG:
//                        Log.d("Parse Applications", "Ending tag for" + tagName);
                        if (inEntry){
                            if (tagName.equalsIgnoreCase("item")) {
                                applications.add(currentRecord);
                                inEntry = false;
                            } else if (tagName.equalsIgnoreCase("title")) {
                                currentRecord.setTitle(textValue);
                            } else if (tagName.equalsIgnoreCase("link")) {
                                currentRecord.setLink(textValue);
                            }
                        }
                        break;
                    default:
                        // Nothing else to do
                }
                eventType = xpp.next();
            }

            return true;

        } catch(Exception e) {
            status = false;
            e.printStackTrace();
        }
        for (Application app :applications) {
            Log.d("ParseApplications", "***********");
            Log.d("ParseApplications", "Title: " + app.getTitle());
            Log.d("ParseApplications", "Link: " + app.getLink());
        }
        return true;
    }

}
