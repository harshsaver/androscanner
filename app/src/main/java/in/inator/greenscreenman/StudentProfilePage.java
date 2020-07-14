package in.inator.greenscreenman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class StudentProfilePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile_page);

        String[] profile = {"Name","Standard","Div","Gender","DOB","School Name","Roll No.","Address"};
        ListView profileList = (ListView)findViewById(R.id.profileList);
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this,R.layout.text_view1,profile);
        profileList.setAdapter(arrayAdapter);




    }
}