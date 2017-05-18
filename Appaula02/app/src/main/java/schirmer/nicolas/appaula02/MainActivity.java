package schirmer.nicolas.appaula02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAnother();
            }
        });
    }

    private void goToAnother(){
        Intent intent = new Intent(this, Main2Activity.class);
        //intent.putExtra("mTag", editText.getText().toString());
        String desc = getResources().getString(R.string.lorem);
                               // nome                      // descrição
        User user = new User(editText.getText().toString(), desc);

        intent.putExtra("mTag", new Gson().toJson(user));

        startActivity(intent);
    }
}
