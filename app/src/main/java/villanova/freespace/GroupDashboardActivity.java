package villanova.freespace;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class GroupDashboardActivity extends AppCompatActivity {

    Button viewReservations;
    Button findRooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_dashboard);

        viewReservations = (Button) findViewById(R.id.viewReservations);
        findRooms = (Button) findViewById(R.id.findRooms);

        viewReservations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(GroupDashboardActivity.this, CurrentReservationsActivity.class);
                startActivity(it);
                //return;
            }
        });

        findRooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(GroupDashboardActivity.this, FindRoomsActivity.class);
                startActivity(it);
                //return;
            }
        });
    }
}
