package com.example.milos.msattackczm.utils;

import android.util.Log;

import com.example.milos.msattackczm.model.RSCentra;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by milos on 24.3.2015.
 */
public class XMLParserRScentra {

    List<RSCentra> rsCentras;
    private RSCentra rsCentra;
    private String text;

    public XMLParserRScentra() {   rsCentras = new ArrayList<RSCentra>();    }

    public List<RSCentra> getRSCentra() {        return rsCentras;    }

    public List<RSCentra> parse(InputStream is) {
        XmlPullParserFactory factory = null;
        XmlPullParser parser = null;
        try {
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            parser = factory.newPullParser();

            parser.setInput(is, null);

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase("ROW")) {
                            // create a new instance of employee
                            rsCentra = new RSCentra();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase("ROW")) {
                            // add employee object to list
                           rsCentras.add(rsCentra);
                        } else if (tagname.equalsIgnoreCase("NAZEV")) {
                            rsCentra.setNazev(text);
                        } else if (tagname.equalsIgnoreCase("ADRESA")) {
                            rsCentra.setAdresa(text);
                        } else if (tagname.equalsIgnoreCase("URL")) {
                        rsCentra.setUrl(text);
                    } else if (tagname.equalsIgnoreCase("EMAIL")) {
                        rsCentra.setEmail(text); }
                        break;

                    default:
                        break;
                }
                eventType = parser.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return rsCentras;
    }
}




