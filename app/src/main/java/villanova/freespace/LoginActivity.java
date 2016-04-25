package villanova.freespace;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText user;
    EditText pass;
    TextView login;
    TextView signup;

    String sUser;
    String sPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/LatoLight.ttf");
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/LatoRegular.ttf");

        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.pass);
        signup = (TextView) findViewById(R.id.signup);
        login = (TextView) findViewById(R.id.login);

        user.setTypeface(custom_font);
        pass.setTypeface(custom_font);

        login.setTypeface(custom_font1);
        signup.setTypeface(custom_font);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(LoginActivity.this, LoginSignupActivity.class);
                startActivity(it);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sUser = user.getText().toString();
                sPass = pass.getText().toString();

                if (sUser.matches("") && sPass.matches("")) {
                    Intent it = new Intent(LoginActivity.this, LookingRoomActivity.class);
                    startActivity(it);
                    //return;
                } else {
                    Toast.makeText(getApplicationContext(), "Incorrect Login!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}