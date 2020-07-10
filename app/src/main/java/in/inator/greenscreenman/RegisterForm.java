package in.inator.greenscreenman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RegisterForm extends AppCompatActivity {
    ImageView back;
    TextView loginPage;
    Button registerd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);

        back = (ImageView)findViewById(R.id.goBack);
        loginPage = (TextView)findViewById(R.id.registerText);
        registerd = (Button)findViewById(R.id.loginButton);

        //registration complete
        registerd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterForm.this,SelectionForm.class));
            }
        });

        //go back
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //go back when clicked on login
        loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}