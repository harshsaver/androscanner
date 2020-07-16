package in.inator.greenscreenman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class TeacherProfilePage extends AppCompatActivity {

    HashMap<String,String>map;
    int to[] = new int[2];
    String from[] = {"line1","line2"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_profile_page);

        //personal info listView
        String[] profile = {"Name","Gender","DOB","College Name","Position Title","Identity No.","Email Address"};
        ListView profileList = (ListView)findViewById(R.id.profileList);
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this,R.layout.text_view1,profile);
        profileList.setAdapter(arrayAdapter);



        //education listView
        ListView education = (ListView)findViewById(R.id.educationList);
        ArrayList<HashMap<String, String>> edu = new ArrayList<>();
        map = new HashMap<String, String>();
        map.put("line1", "Btech");
        map.put("line2", "University of Pune");
        edu.add(map);
        to[0] = R.id.item3;
        to[1] = R.id.item4;
        SimpleAdapter adap1 = new SimpleAdapter(this, edu, R.layout.two_item1,from, to);
        education.setAdapter(adap1);

    }
}