package in.inator.greenscreenman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SelectionForm extends AppCompatActivity {

    ImageView student,teacher,staff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_form);

        student = (ImageView)findViewById(R.id.student);
        teacher = (ImageView)findViewById(R.id.teacher);
        staff = (ImageView)findViewById(R.id.staff);

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectionForm.this, Dashboard.class));
            }
        });

        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectionForm.this, Dashboard.class));
            }
        });

        staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectionForm.this, Dashboard.class));
            }
        });
    }
}