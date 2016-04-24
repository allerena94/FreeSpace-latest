package villanova.freespace;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AvailableRoomsActivity extends AppCompatActivity {

    String[] listItems = { "Bartley 1011", "Bartley 1010", "Ceer 001", "Ceer 109" };

    ListView roomList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.available_rooms);

        roomList = (ListView) findViewById(R.id.listOfRooms);

        ArrayAdapter<String> roomAdapter = new ArrayAdapter<String>(this,R.layout.list_white_text, R.id.list_content, listItems);

        roomList.setAdapter(roomAdapter);
    }
}
