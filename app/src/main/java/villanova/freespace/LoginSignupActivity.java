package villanova.freespace;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class LoginSignupActivity extends AppCompatActivity {

    EditText pass;
    EditText email;
    TextView login;
    TextView signup;
    Firebase myFirebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myFirebaseRef = new Firebase("https://sweltering-torch-6902.firebaseio.com//");
        setContentView(R.layout.activity_login_signup);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/LatoLight.ttf");

        pass = (EditText) findViewById(R.id.pass);
        pass.setTypeface(custom_font);
        email = (EditText) findViewById(R.id.email);
        email.setTypeface(custom_font);

        Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/LatoRegular.ttf");

        signup = (TextView) findViewById(R.id.signup);
        signup.setTypeface(custom_font1);
        login = (TextView) findViewById(R.id.login);
        login.setTypeface(custom_font);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(LoginSignupActivity.this, LoginActivity.class);
                startActivity(it);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myFirebaseRef.createUser(email.getText().toString(), pass.getText().toString(), new Firebase.ValueResultHandler<Map<String, Object>>() {
                    @Override
                    public void onSuccess(Map<String, Object> result) {
                        Toast.makeText(getApplicationContext(), "Successfully created user account with uid: " + result.get("uid"), Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onError(FirebaseError firebaseError) {
                        Toast.makeText(getApplicationContext(), "Error: sign up has failed"+firebaseError.getDetails(), Toast.LENGTH_SHORT).show();
                    }
                });

                Intent it = new Intent(LoginSignupActivity.this, LoginActivity.class);
                startActivity(it);

            }
        });

    }
}
