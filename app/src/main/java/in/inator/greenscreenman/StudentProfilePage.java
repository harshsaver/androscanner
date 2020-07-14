package in.inator.greenscreenman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentProfilePage extends AppCompatActivity {

    HashMap<String,String>map;
    String from[] = {"line1","line2"};
    int to[] = new int[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile_page);

        String[] profile = {"Name","Standard","Div","Gender","DOB","School Name","Roll No.","Address"};
        ListView profileList = (ListView)findViewById(R.id.profileList);
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this,R.layout.text_view1,profile);
        profileList.setAdapter(arrayAdapter);

        //projects listView
        ListView project = (ListView)findViewById(R.id.projectList);
        ArrayList<HashMap<String, String>> pro = new ArrayList<>();
        map = new HashMap<String, String>();
        map.put("line1", "BubbleSort Visualizer");
        map.put("line2", "app to visualize bubbleSort");
        pro.add(map);
        to[0] = R.id.item1;
        to[1] = R.id.item2;
        SimpleAdapter adap = new SimpleAdapter(this, pro, R.layout.two_item,from, to);
        project.setAdapter(adap);

    }
}