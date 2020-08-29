package in.inator.greenscreenman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

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


        final RecyclerView recyclerView = findViewById(R.id.recyle_view);
        final RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this,mUsername,mComments,mImages);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.scrollToPosition(mUsername.size()-1);
        recyclerView.setAdapter(recyclerViewAdapter);



        ImageButton send = findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImages.add(R.drawable.user);
                mUsername.add("NewUSer");
                EditText text = findViewById(R.id.et_chat);
                String msg = text.getText().toString();
                text.setText("");
                mComments.add(msg);
                recyclerView.scrollToPosition(mUsername.size()-1);
                recyclerView.setAdapter(recyclerViewAdapter);

            }
        });
    }
}