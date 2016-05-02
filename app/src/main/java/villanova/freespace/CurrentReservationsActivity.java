package villanova.freespace;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class CurrentReservationsActivity extends AppCompatActivity {

    TextView first;
    TextView second;
    TextView third;
    TextView fourth;
    TextView fifth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_reservations);

        first = (TextView)findViewById(R.id.firstReservationName);
        second = (TextView)findViewById(R.id.secondReservationName);
        third = (TextView)findViewById(R.id.thirdReservationName);
        fourth = (TextView)findViewById(R.id.fourthReservationName);
        fifth = (TextView)findViewById(R.id.fifthReservationName);

        SharedPreferences settings = getSharedPreferences("reservationPref", MODE_PRIVATE);
        int reservationCount = settings.getInt("reservationCount", 0);
        String reservationName = settings.getString("reservationName", "None");

        if (reservationName.equals("None")) {

        }

        else {
            if (reservationCount == 1) {
                first.setText(reservationName);

                SharedPreferences.Editor editor = settings.edit();
                editor.putString("firstReservationName", reservationName);
                editor.apply();
            }

            if (reservationCount == 2) {
                first.setText(settings.getString("firstReservationName", ""));
                second.setText(reservationName);

                SharedPreferences.Editor editor = settings.edit();
                editor.putString("secondReservationName", reservationName);
                editor.apply();
            }

            if (reservationCount == 3) {
                first.setText(settings.getString("firstReservationName", ""));
                second.setText(settings.getString("secondReservationName", ""));
                third.setText(reservationName);

                SharedPreferences.Editor editor = settings.edit();
                editor.putString("thirdReservationName", reservationName);
                editor.apply();
            }

            if (reservationCount == 4) {
                first.setText(settings.getString("firstReservationName", ""));
                second.setText(settings.getString("secondReservationName", ""));
                third.setText(settings.getString("thirdReservationName", ""));
                fourth.setText(reservationName);

                SharedPreferences.Editor editor = settings.edit();
                editor.putString("fourthReservationName", reservationName);
                editor.apply();
            }

            if (reservationCount == 5) {
                first.setText(settings.getString("firstReservationName", ""));
                second.setText(settings.getString("secondReservationName", ""));
                third.setText(settings.getString("thirdReservationName", ""));
                fourth.setText(settings.getString("fourthReservationName", ""));
                fifth.setText(reservationName);

                SharedPreferences.Editor editor = settings.edit();
                editor.putString("fifthReservationName", reservationName);
                editor.apply();
            }
        }
    }
}
