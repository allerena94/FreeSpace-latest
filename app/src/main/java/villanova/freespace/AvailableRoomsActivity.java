package villanova.freespace;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class AvailableRoomsActivity extends AppCompatActivity {

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

    }

    private ArrayList<Room> generateData(){
        ArrayList<Room> items = new ArrayList<Room>();
        items.add(new Room("Bartley 1001", "0", "60"));
        items.add(new Room("Bartley 1010", "0", "10"));
        items.add(new Room("Bartley 1011", "0", "30"));
        items.add(new Room("Bartley 1047", "0", "50"));
        items.add(new Room("Bartley 1064", "0", "60"));

        return items;
    }
}
