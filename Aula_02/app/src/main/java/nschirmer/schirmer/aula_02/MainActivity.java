package nschirmer.schirmer.aula_02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Pessoa pessoa = new Pessoa();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textViewBio);

        //pessoa.setBio(textView.getText().toString());
        //pessoa.setBio(getResources().getString(R.string.lorem));
    }

    // manda texto e recebe texto
    public void editClick(View view){
        // não manda texto
        //startActivity(new Intent(this, Main2Activity.class));

        // popula a bio com o texto
        pessoa.setBio(textView.getText().toString());

        Intent intent = new Intent(this, Main2Activity.class);
        //intent.putExtra("bio", textView.getText());

        // intent passa apenas tipos primitivos
        //intent.putExtra("bio", pessoa);

        intent.putExtra("bio", new Gson().toJson(pessoa));

        startActivityForResult(intent, 101); // qualuqer requert code number
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        if(requestCode == 101){
            if(resultCode == 201){
                //textView.setText(intent.getStringExtra("newBio"));

                pessoa = new Gson().fromJson(
                        intent.getStringExtra("newBio"), Pessoa.class);

                textView.setText(pessoa.getBio());
            }
        }
    }


}
