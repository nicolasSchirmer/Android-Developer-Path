package schirmer.nicolas.aula05;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Item> {

    Context context;
    List<Item> items;

    public CustomAdapter(Context context, List<Item> items){
        super(context, R.layout.item_contraint, items);
        this.context = context;
        this.items = items;
    }

    class ViewHolder{
        TextView name, local, likes;
        ImageView avatar, photo;
        Button button1, button2, button3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_contraint, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.textView);
            viewHolder.button1 = (Button) convertView.findViewById(R.id.button);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // faz qualquer coisa
            }
        });

        viewHolder.name.setText(// TODO);

        return convertView;
    }
}
