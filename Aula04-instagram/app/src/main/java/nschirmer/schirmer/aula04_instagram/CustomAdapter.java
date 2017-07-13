package nschirmer.schirmer.aula04_instagram;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Item> {

    // View lookup cache
    private static class ViewHolder {
        TextView name;
        ImageView photo, avatar, delete, gps;
    }

    public CustomAdapter(Context context, ArrayList<Item> items) {
        super(context, R.layout.item, items);
    }

    @Override @NonNull
    public View getView(int position, View convertView,@NonNull ViewGroup parent) {
        // pega objeto Item do array
        Item item = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item, parent, false);

            viewHolder.name = (TextView) convertView.findViewById(R.id.item_name);
            viewHolder.avatar = (ImageView) convertView.findViewById(R.id.item_avatar);
            viewHolder.photo = (ImageView) convertView.findViewById(R.id.item_photo);
            viewHolder.gps = (ImageView) convertView.findViewById(R.id.item_gps);
            viewHolder.delete = (ImageView) convertView.findViewById(R.id.item_delete);

            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data from the data object via the viewHolder object
        // into the template view.

        // checar se não é null
        //if(item.getPhotoID() != null)
        //(item.getPhotoID() != null ? item.getPhotoID() : bla)

        // popula view holder
        viewHolder.name.setText(item.getNome());
        viewHolder.avatar.setImageResource(item.getAvatarID());
        viewHolder.photo.setImageResource(item.getPhotoID());

        // clicks
        viewHolder.gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("mLog", "gps click");
            }
        });

        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "delete click", Toast.LENGTH_LONG).show();
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }
}
