package nschirmer.schirmer.aula04_instagram;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Item> {

    private Activity activity;

    // View lookup cache
    private static class ViewHolder {
        TextView name;
        SimpleDraweeView photo, avatar;
        ImageView delete, gps;
    }

    public CustomAdapter(Context context, ArrayList<Item> items, Activity activity) {
        super(context, R.layout.item, items);

        this.activity = activity;
    }

    @Override @NonNull
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
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
            viewHolder.avatar = (SimpleDraweeView) convertView.findViewById(R.id.item_avatar);
            viewHolder.photo = (SimpleDraweeView) convertView.findViewById(R.id.item_photo);
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
        /*
        viewHolder.name.setText(item.getNome());
        viewHolder.avatar.setImageResource(item.getAvatarID());
        viewHolder.photo.setImageResource(item.getPhotoID());
        */

        viewHolder.name.setText(item.getNome());
        viewHolder.avatar.setImageURI(item.getAvatarUrl());
        viewHolder.photo.setImageURI(item.getPhotoUrl());


        // clicks
        viewHolder.gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("mLog", "gps click");
                Toast.makeText(getContext(), "gps click", Toast.LENGTH_LONG).show();


            }
        });

        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "delete click", Toast.LENGTH_LONG).show();

                // mostra dialog personalizado
                showCustomDialog(position);
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }

    private void showCustomDialog(final int position){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_delete);

        // se quiser adicionar texto
        /*
        TextView text = (TextView) dialog.findViewById(R.id.dialog_text);
        text.setText(msg);
        */

        // se quiser mandar icone
        /*
        ImageView imageView = (ImageView)  dialog.findViewById(R.id.dialog_icon);
        imageView.setImageResource(id);
        */

        ImageView buttonOk = (ImageView) dialog.findViewById(R.id.dialog_ok);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // deleta item da lista
                remove(getItem(position));
                dialog.dismiss();
                notifyDataSetChanged();
            }
        });

        ImageView buttonCancel = (ImageView) dialog.findViewById(R.id.dialog_cancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

}
