package in.inator.greenscreenman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;

import java.util.ArrayList;

public class Test extends AppCompatActivity {

    private ArrayList<String>mUsername = new ArrayList<>();
    private ArrayList<String>mComments = new ArrayList<>();
    private ArrayList<Integer>mImages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_room);

        mUsername.add("ABC");
        mUsername.add("AfC");
        mUsername.add("ABd");
        mUsername.add("ASD");
        mUsername.add("ASFAEG");
        mComments.add("cool");
        mComments.add("nice");
        mComments.add("goal!!");
        mComments.add("score!!");
        mComments.add("lol!");
        mImages.add(R.drawable.dance);
        mImages.add(R.drawable.plane);
        mImages.add(R.drawable.employee);
        mImages.add(R.drawable.user);
        mImages.add(R.drawable.tablet);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyle_view);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this,mUsername,mComments,mImages);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}