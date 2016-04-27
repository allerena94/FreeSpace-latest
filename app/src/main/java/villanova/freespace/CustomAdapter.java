package villanova.freespace;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Room> {

    private final Context context;
    private final ArrayList<Room> itemsArrayList;

    public CustomAdapter(Context context, ArrayList<Room> itemsArrayList) {
        super(context, R.layout.list_white_text, itemsArrayList);
        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.list_white_text, parent, false);

        // 3. Get the two text view from the rowView
        TextView roomView = (TextView) rowView.findViewById(R.id.list_rooms);
        TextView statusView = (TextView) rowView.findViewById(R.id.room_count);

        // 4. Set the text for textView
        roomView.setText(itemsArrayList.get(position).getName());
        statusView.setText(itemsArrayList.get(position).getStatus());

        // 5. return rowView
        return rowView;
    }

    /*protected static class ViewHolder{
        protected TextView room_name;
        protected TextView room_status;
    }*/
}
