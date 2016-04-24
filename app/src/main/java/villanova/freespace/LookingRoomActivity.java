package villanova.freespace;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class LookingRoomActivity extends AppCompatActivity {

    Button individual;
    Button group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lookingroom);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayUseLogoEnabled(true);

        individual = (Button) findViewById(R.id.individual);
        group = (Button) findViewById(R.id.group);

        //individual.setTypeface(custom_font);
        //group.setTypeface(custom_font);

        individual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent it = new Intent(LookingRoomActivity.this, AvailableRoomsActivity.class);
                    startActivity(it);
                    //return;
            }
        });

        group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(LookingRoomActivity.this, GroupDashboardActivity.class);
                startActivity(it);
                //return;
            }
        });
    }

}
