package nschirmer.schirmer.aula6;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    EditText email, senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);

        email = (EditText) findViewById(R.id.editText);
        senha = (EditText) findViewById(R.id.editText2);
    }

    // faz o login
    public void buttonClick(View view){
        boolean logado = sharedPreferences.getBoolean("logged", false);
        if(!logado){
            sharedPreferences.edit().putString("email" ,email.getText().toString()).apply();
            sharedPreferences.edit().putString("pass", senha.getText().toString()).apply();
            sharedPreferences.edit().putBoolean("logged", true).apply();

            // criar o perfil
            if(fisrtRun()){
                startActivity(new Intent(this, CriaPerfil.class));
                finish();
            }
        } else{
            Toast.makeText(this, "está logado", Toast.LENGTH_LONG).show();
        }
    }

    private boolean fisrtRun(){
        return sharedPreferences.getBoolean("firstrun", true);
    }
}
