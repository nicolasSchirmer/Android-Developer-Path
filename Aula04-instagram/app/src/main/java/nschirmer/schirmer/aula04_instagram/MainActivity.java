package nschirmer.schirmer.aula04_instagram;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Item> itemList = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            Item item = new Item(
                    R.mipmap.ic_launcher,
                    R.mipmap.ic_launcher_round,
                    ("bug " + String.valueOf(i)));

            itemList.add(item);
        }

        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(new CustomAdapter(this, itemList));
        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // click do item inteiro
            }
        });
        */


    }
}
