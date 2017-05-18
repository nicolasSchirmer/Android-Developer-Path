package schirmer.nicolas.appaula02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;

public class Main2Activity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView = (TextView) findViewById(R.id.textView);
        String mString = getIntent().getStringExtra("mTag");
        User userGson = new Gson().fromJson(mString, User.class);

        textView.setText(userGson.getName());


    }
}
