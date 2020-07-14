package in.inator.greenscreenman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TeacherProfilePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_profile_page);

        String[] profile = {"Name","College Name","Gender","DOB","Identity No.","Address"};
        ListView profileList = (ListView)findViewById(R.id.profileList);
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this,R.layout.text_view2,profile);
        profileList.setAdapter(arrayAdapter);

    }
}