package nschirmer.schirmer.aula04_instagram;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Item> itemList = new ArrayList<>();
    private OkHttpClient okHttpClient = new OkHttpClient();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listview);

        try {
            downloadData("https://api.myjson.com/bins/l62wz");

        } catch (IOException e){e.printStackTrace();}

        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // click do item inteiro
            }
        });
        */
    }

    // baixa dados json em uma thread async
    private void downloadData(String URL) throws IOException{
        // faz request
        Request request = new Request.Builder().url(URL).build();

        // pega dados
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(!response.isSuccessful()) throw new IOException("deu ruim " + response.toString());

                String json = response.body().string();
                Log.d("mLog", "json: " + json);

                try{
                    // array de items
                    JSONArray jsonArray = new JSONObject(json).getJSONArray("items");
                    Log.d("mLog", "jsonArray items " +  jsonArray.toString());

                    for(int i = 0; i < jsonArray.getJSONObject(0).length(); i++){
                        Log.d("mLog", "item (pega do array) " + i + " " + jsonArray.getJSONObject(0).get(String.valueOf(i)).toString());

                        JSONObject jItem = jsonArray.getJSONObject(0).getJSONObject(String.valueOf(i));
                        Log.d("mLog", "jItem "  + i + " " +  jItem.toString());

                        Item item = new Item(jItem.getString("name"), jItem.getString("avatar"), jItem.getString("photo"));

                        itemList.add(item);
                    }
                }catch (JSONException e){e.printStackTrace();}

                setListViewItems();
            }
        });
    }

    // infla views na lista com main thread
    private void setListViewItems(){
        // tenta falar com a thread da mainactivity quando terminar de baixar
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // "mata" progress / para o loading
                (findViewById(R.id.progressBar)).setVisibility(View.GONE);
                // adiciona os items na listview
                listView.setAdapter(new CustomAdapter(getBaseContext(), itemList, MainActivity.this));
            }
        });
    }
}
