package villanova.freespace;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class AvailableRoomsActivity extends AppCompatActivity {

    ArrayList<String> listItems = new ArrayList<>();
    ListView roomList;
    ArrayAdapter<String> roomAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase myFirebaseRef = new Firebase("https://sweltering-torch-6902.firebaseio.com/room");
        setContentView(R.layout.available_rooms);

        roomList = (ListView) findViewById(R.id.listOfRooms);
        myFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                int i = 0;
                for(DataSnapshot roomSnapshot:snapshot.getChildren()) {
                    Room room = roomSnapshot.getValue(Room.class);
                    roomAdapter.add(room.getBuilding() + " " + room.getRoom());
                    System.out.println(i + " " + listItems.get(i));
                    i++;
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Toast.makeText(getApplicationContext(), "Error: database request has failed"+firebaseError.getDetails(), Toast.LENGTH_SHORT).show();
            }
        });
        roomAdapter =
                new ArrayAdapter<>(this, R.layout.list_white_text, R.id.list_rooms, listItems);
        roomList.setAdapter(roomAdapter);
        roomList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "You have checked into " + listItems.get(position), Toast.LENGTH_LONG).show();
                //increment capacity

                Intent it = new Intent(AvailableRoomsActivity.this, LookingRoomActivity.class);
                startActivity(it);
            }
        });
    }
}
