package villanova.freespace;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class PickRoomActivity extends AppCompatActivity {
    ArrayList<Room> listRooms = new ArrayList<Room>();
    ArrayList<Room> listRoomsGlobal = new ArrayList<Room>();
    ArrayList<Integer> roomsActualCount = new ArrayList<Integer>();
    ListView listView;
    CustomAdapter adapter;
    String roomTypeChoosenAbbreviation;
    String bC;
    String rTc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Firebase myFirebaseRef = new Firebase("https://vufreespace.firebaseio.com/");
        setContentView(R.layout.group_pick_room);

        Bundle bundle = getIntent().getExtras();
        bC = bundle.getString("Building Chosen");
        rTc = bundle.getString("Room Type Chosen");

        // 1. Pass context and data to the custom adapter
        adapter = new CustomAdapter(this, listRooms);

        // 2. Get ListView from activity_main.xml
        listView = (ListView) findViewById(R.id.listOfChosenRooms);

        // 3. setListAdapter
        listView.setAdapter(adapter);

        myFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                int i = 0;
                for(DataSnapshot roomSnapshot:snapshot.getChildren()) {
                    RoomSnapshot rS = roomSnapshot.getValue(RoomSnapshot.class);
                    if(rS.getGroupBooked() == 0) {
                        if(rS.getCount() == 0) {
                            if ((rS.getBuilding().equals(bC)) && (rS.getRoomType().equals(rTc))) {
                                listRooms.add(new Room(rS.getBuilding(), Integer.toString(rS.getRoomNumber()), rS.getRoomType(), Integer.toString(rS.getMaxCapacity()), rS.getCount()));
                                roomsActualCount.add(i);
                                i++;
                            } else {
                                i++;
                                continue;
                            }
                        }
                        else{
                            i++;
                        }
                    }
                    else {
                        i++;
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
                final String roomNameClicked = listRoomsGlobal.get((int) id).getName();
                final int rowID = (int) id;

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PickRoomActivity.this);

                alertDialogBuilder.setMessage("Book " + roomNameClicked + " as a Group?");

                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        final Firebase ref = new Firebase("https://vufreespace.firebaseio.com/");
                        Firebase roomRef = ref.child(roomsActualCount.get(rowID).toString());
                        roomRef.child("groupBooked").setValue(1);
                        //Toast.makeText(PickRoomActivity.this, roomsActualCount.get(rowID).toString(),Toast.LENGTH_LONG).show();
                        Toast.makeText(PickRoomActivity.this,"Booked " + roomNameClicked + "!",Toast.LENGTH_LONG).show();

                        SharedPreferences settings = getSharedPreferences("reservationPref", MODE_PRIVATE);
                        int reservationCount = settings.getInt("reservationCount", 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putInt("reservationCount", reservationCount+1);
                        editor.putString("reservationName", roomNameClicked);
                        editor.apply();

                        Intent it = new Intent(PickRoomActivity.this, CurrentReservationsActivity.class);
                        startActivity(it);
                    }
                });

                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }
}
