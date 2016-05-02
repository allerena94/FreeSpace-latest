package villanova.freespace;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FindRoomsActivity extends AppCompatActivity {

    Spinner buildings;
    Spinner roomTypes;
    TextView submit;

    String buildingChosen;
    String roomTypeChosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_find_rooms);

        buildings = (Spinner)findViewById(R.id.buildingOptions);
        roomTypes = (Spinner)findViewById(R.id.roomTypeOptions);
        submit = (TextView)findViewById(R.id.submit);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.villanova_buildings,R.layout.spinner_center_item);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                this, R.array.room_types,R.layout.spinner_center_item);
        // set whatever dropdown resource you want
        adapter.setDropDownViewResource(R.layout.spinner_center_item);

        buildings.setAdapter(adapter);
        roomTypes.setAdapter(adapter1);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildingChosen = buildings.getItemAtPosition(buildings.getSelectedItemPosition()).toString();
                roomTypeChosen = roomTypes.getItemAtPosition(roomTypes.getSelectedItemPosition()).toString();

                if(roomTypeChosen.equals("Auditorium")) {
                    roomTypeChosen = "A";
                }

                else if(roomTypeChosen.equals("Classroom")) {
                    roomTypeChosen = "C";
                }

                else if(roomTypeChosen.equals("Lecture Hall")) {
                    roomTypeChosen = "LH";
                }

                Bundle bundle = new Bundle();
                bundle.putString("Building Chosen", buildingChosen);
                bundle.putString("Room Type Chosen", roomTypeChosen);

                Intent it = new Intent(FindRoomsActivity.this, PickRoomActivity.class);
                it.putExtras(bundle);
                startActivity(it);
            }
        });
    }
}
