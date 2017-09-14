package nicolas.schirmer.aula03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(new Pessoa("bla", R.drawable.ted));
        pessoas.add(new Pessoa("ble", R.drawable.ic_android_black_24dp));
        pessoas.add(new Pessoa("bli", R.drawable.ted));
        pessoas.add(new Pessoa("blo", R.drawable.ic_art_track_black_24dp));

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new CustomListAdapter(pessoas ,this));

        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getBaseContext(), "oi", Toast.LENGTH_SHORT).show();

                //startActivity();
            }
        });
        */

        // com grid

        GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new CustomListAdapter(pessoas ,this));



    }
}
