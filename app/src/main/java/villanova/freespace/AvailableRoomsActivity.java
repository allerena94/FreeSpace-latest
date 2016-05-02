package villanova.freespace;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class AvailableRoomsActivity extends AppCompatActivity {
    ArrayList<Room> listRooms = new ArrayList<Room>();
    ArrayList<Room> listRoomsGlobal = new ArrayList<Room>();
    ListView listView;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Firebase myFirebaseRef = new Firebase("https://vufreespace.firebaseio.com/");

        setContentView(R.layout.available_rooms);

        // 1. Pass context and data to the custom adapter
        adapter = new CustomAdapter(this, listRooms);

        // 2. Get ListView from activity_main.xml
        listView = (ListView) findViewById(R.id.listOfRooms);

        // 3. setListAdapter
        listView.setAdapter(adapter);

        myFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for(DataSnapshot roomSnapshot:snapshot.getChildren()) {
                    RoomSnapshot rS = roomSnapshot.getValue(RoomSnapshot.class);

                    if (rS.getGroupBooked() == 1) {
                        continue;
                    } else {
                        listRooms.add(new Room(rS.getBuilding(), Integer.toString(rS.getRoomNumber()), rS.getRoomType(), Integer.toString(rS.getMaxCapacity()), rS.getCount()));
                    }
                }

                listRoomsGlobal = listRooms;
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Toast.makeText(getApplicationContext(), "Error: Database Request has Failed" + firebaseError.getDetails(), Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                SharedPreferences settings = getSharedPreferences("myPref", MODE_PRIVATE);
                int numCheckIns = settings.getInt("numCheckIns", 0);

                 if (numCheckIns == 1){
                    Toast.makeText(getApplicationContext(), "You can only check in to one room at a time!", Toast.LENGTH_LONG).show();
                }

                else {
                    final Firebase ref = new Firebase("https://vufreespace.firebaseio.com/");
                    Firebase roomRef = ref.child(Long.toString(id));
                    roomRef.child("count").setValue(listRooms.get((int) id).getCount() + 1);

                    Toast.makeText(getApplicationContext(), "Checked in to " + listRoomsGlobal.get((int) id).getName() + "!", Toast.LENGTH_SHORT).show();
                     numCheckIns++;
                }

                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("numCheckIns", numCheckIns);
                editor.apply();

                Intent it = new Intent(AvailableRoomsActivity.this, LookingRoomActivity.class);
                startActivity(it);


                /*if(id == 0) {

                }

                if(id == 1) {

                }

                if (id == 2) {
                    Toast.makeText(getApplicationContext(), "Checked into Bartley 1011! Count is now 1/117", Toast.LENGTH_LONG).show();
                    Intent it = new Intent(AvailableRoomsActivity.this, LookingRoomActivity.class);
                    startActivity(it);
                }
                if (id == 3) {
                    Toast.makeText(getApplicationContext(), "Checked into Bartley 1047! Count is now 1/36", Toast.LENGTH_LONG).show();
                    Intent it = new Intent(AvailableRoomsActivity.this, LookingRoomActivity.class);
                    startActivity(it);
                }

                if(id == 4) {

                }*/
            }
        });
    }
}
