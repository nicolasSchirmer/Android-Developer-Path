package nschirmer.schirmer.aula_02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

public class Main2Activity extends AppCompatActivity {

    EditText editText;
    Pessoa pessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editText = (EditText) findViewById(R.id.editText);

        String bio = getIntent().getStringExtra("bio");

        pessoa = new Gson().fromJson(bio, Pessoa.class);

        //editText.setText(bio);

        editText.setText(pessoa.getBio());
    }

    // botão de voltar
    public void backClick(View view){
        onBackPressed();
    }

    // retorna string para activity anteiror
    public void buttonClick(View view){
        if(editText.getText() != null){
            editText.clearFocus();
            Intent intent = new Intent(this, Main2Activity.class);
            //intent.putExtra("newBio", editText.getText().toString());

            intent.putExtra("newBio", new Gson().toJson(pessoa));

            setResult(201, intent);

            finish();  // fecha a instancia da activity (ela para de existir na memoria)
        }
    }
}
