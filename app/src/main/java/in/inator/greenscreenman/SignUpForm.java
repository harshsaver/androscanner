package in.inator.greenscreenman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SignUpForm extends AppCompatActivity {
    Button loginButton;
    TextView registerText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_form);

        loginButton = (Button)findViewById(R.id.loginButton);
        registerText = (TextView)findViewById(R.id.registerText);
        //login
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpForm.this, Dashboard.class));
            }
        });

        //register
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpForm.this,RegisterForm.class));
            }
        });
    }
}