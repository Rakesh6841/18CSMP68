package com.computerlabspace.parsexmlandjson_program6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.JsonReader;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ViewDataActivity extends AppCompatActivity {

    TextView xmlContentTextView, jsonContentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        xmlContentTextView = (TextView) findViewById(R.id.view_xml_content_id);
        jsonContentTextView = (TextView) findViewById(R.id.view_json_content_id);
        Intent intent = getIntent();
        String dataType = intent.getStringExtra("dataType");
        if(dataType.equals("xml")) {
            xmlContentTextView.setText("Test xml parsed content");
            parseXML();
        } else if(dataType.equals("json")) {
            jsonContentTextView.setText("Test json parsed content");
            parseJSON();
        }
    }

    public void parseXML() {
        InputStream inputStream = null;
        try {
            inputStream = getAssets().open("weather.xml");
            JsonReader reader = new JsonReader(new InputStreamReader(inputStream));
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(inputStream);
            doc.normalize();
            NodeList nodeList = doc.getElementsByTagName("weather");
            for(int i=0; i<nodeList.getLength();i++) {
                Node node = nodeList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    xmlContentTextView.setText("City_Name: "+element.getElementsByTagName("city_name").item(0).getTextContent() + "\n");
                    xmlContentTextView.append("Latitude: "+element.getElementsByTagName("latitude").item(0).getTextContent() + "\n");
                    xmlContentTextView.append("Longitude: "+element.getElementsByTagName("longitude").item(0).getTextContent() + "\n");
                    xmlContentTextView.append("Temperature: "+element.getElementsByTagName("temperature").item(0).getTextContent() + "\n");
                    xmlContentTextView.append("Humidity: "+element.getElementsByTagName("humidity").item(0).getTextContent());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void parseJSON() {
        try (InputStream inputStream = getAssets().open("weather.json")) {
            byte[] data = new byte[inputStream.available()];
            inputStream.read(data);
            String jsonString = new String(data);
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject weather = jsonObject.getJSONObject("weather");
            jsonContentTextView.setText("City_Name: "+weather.getString("city_name") + "\n");
            jsonContentTextView.append("Latitude: "+weather.getString("latitude") + "\n");
            jsonContentTextView.append("Longitude: "+weather.getString("longitude") + "\n");
            jsonContentTextView.append("Temperature: "+weather.getString("temperature") + "\n");
            jsonContentTextView.append("Humidity: "+weather.getString("humidity") + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}