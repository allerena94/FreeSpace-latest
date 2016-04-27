package villanova.freespace;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class AvailableRoomsActivity extends AppCompatActivity {
    ArrayList<Room> listRooms = new ArrayList<Room>();
    ArrayList<Room> temp = new ArrayList<Room>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.available_rooms);

        // 1. pass context and data to the custom adapter
        CustomAdapter adapter = new CustomAdapter(this, generateData());

        // 2. Get ListView from activity_main.xml
        ListView listView = (ListView) findViewById(R.id.listOfRooms);

        // 3. setListAdapter
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                /*if (id == 2) {
                    Toast.makeText(getApplicationContext(), "Checked into Bartley 1011! Count is now 1/117", Toast.LENGTH_LONG).show();
                    Intent it = new Intent(AvailableRoomsActivity.this, LookingRoomActivity.class);
                    startActivity(it);
                }*/
                if (id == 3) {
                    Toast.makeText(getApplicationContext(), "Checked into Bartley 1047! Count is now 1/36", Toast.LENGTH_LONG).show();
                    Intent it = new Intent(AvailableRoomsActivity.this, LookingRoomActivity.class);
                    startActivity(it);
                }
            }
        });
    }

    private ArrayList<Room> generateData(){
        listRooms.add(new Room("Bartley 1001", "LH", 0, "60"));
        listRooms.add(new Room("Bartley 1010", "LH", 0, "60"));
        listRooms.add(new Room("Bartley 1011", "A", 0, "117"));
        listRooms.add(new Room("Bartley 1047", "C", 0, "36"));
        listRooms.add(new Room("Bartley 1064", "C", 0, "38"));
        return listRooms;
    }
}
