package villanova.freespace;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class LoginSignupActivity extends AppCompatActivity {

    EditText mEmail;
    EditText mUser;
    EditText mPass;
    EditText mPassAgain;
    TextView mLoginRedirect;
    TextView mSignup;

    UserDatabaseHelper helper = new UserDatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/LatoLight.ttf");
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/LatoRegular.ttf");

        mEmail = (EditText) findViewById(R.id.email);
        mEmail.setTypeface(custom_font);

        mUser = (EditText) findViewById(R.id.username);
        mUser.setTypeface(custom_font);

        mPass = (EditText) findViewById(R.id.pass);
        mPass.setTypeface(custom_font);

        mPassAgain = (EditText) findViewById(R.id.pass_again);
        mPassAgain.setTypeface(custom_font);

        mSignup = (TextView) findViewById(R.id.signup);
        mSignup.setTypeface(custom_font1);

        mLoginRedirect = (TextView) findViewById(R.id.loginRedirect);
        mLoginRedirect.setTypeface(custom_font);

        mLoginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(LoginSignupActivity.this, LoginActivity.class);
                startActivity(it);
            }
        });

        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mPass.getText().toString().equals(mPassAgain.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Passwords don't match!", Toast.LENGTH_SHORT).show();
                }

                else{
                    User freeSpaceUser = new User();
                    freeSpaceUser.setEmail(mEmail.getText().toString());
                    freeSpaceUser.setUserName(mUser.getText().toString());
                    freeSpaceUser.setPassword(mPass.getText().toString());

                    helper.insertUser(freeSpaceUser);

                    Intent it = new Intent(LoginSignupActivity.this, LoginActivity.class);
                    startActivity(it);
                }

            }
        });

    }
}
