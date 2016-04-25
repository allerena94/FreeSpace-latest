package villanova.freespace;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class AvailableRoomsActivity extends AppCompatActivity {

    String[] listItems = {"what", "is", "this", "annoying", "nonsense"};
    ListView roomList;
    ArrayAdapter<String> roomAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase myFirebaseRef = new Firebase("https://sweltering-torch-6902.firebaseio.com/room");
        roomAdapter =
                new ArrayAdapter<String>(this, R.layout.list_white_text, R.id.list_content, listItems);
        setContentView(R.layout.available_rooms);

        roomList = (ListView) findViewById(R.id.listOfRooms);
        myFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                int i = 0;
                for(DataSnapshot roomSnapshot:snapshot.getChildren()) {
                    listItems = new String[(int)snapshot.getChildrenCount()];
                    Room room = roomSnapshot.getValue(Room.class);
                    listItems[i] = room.getBuilding() + " " + room.getRoom();
                    System.out.println(room.getRoom());
                    i++;
                }
                roomList.setAdapter(roomAdapter);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Toast.makeText(getApplicationContext(), "Error: database request has failed"+firebaseError.getDetails(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
