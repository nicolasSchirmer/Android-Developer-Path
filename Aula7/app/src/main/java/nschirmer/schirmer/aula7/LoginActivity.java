package nschirmer.schirmer.aula7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // habilita a leitura do shared preference
        sharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);

        // entra direto se já estiver logado
        if(isLogged()){
            startMain();
        }

        email = (EditText) findViewById(R.id.login_editemail);
        password = (EditText) findViewById(R.id.login_editpass);

        if(hasUser()){
            email.setText(sharedPreferences.getString("email", null));
            email.setText(sharedPreferences.getString("pass", null));
        }
    }

    public void loginClick(View view){
        if(!isLogged()){
            if(validFields()){
                // não está logado
                if(!hasUser()){
                    // não existe usurário
                    sharedPreferences.edit().putString("email" ,email.getText().toString()).apply();
                    sharedPreferences.edit().putString("pass", password.getText().toString()).apply();

                    // vai para activity de criar perfil
                    startActivity(new Intent(this, CreateProfile.class));
                } else {
                    startMain();
                }
            }
        } else {
            startMain();
        }
    }

    private boolean isLogged(){
        return sharedPreferences.getBoolean("logged", false);
    }

    private boolean hasUser(){
        return sharedPreferences.getString("userName", null) != null;
    }

    private void startMain(){
        // inicia activities sem historico
        startActivity(new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    private boolean validFields(){
        // verifica se email está preenchido
        if(email.getText() == null || email.getText().toString().isEmpty()){
            email.setError(getResources().getString(R.string.error_email_null));
            return false;
        }

        // verifica se tipo de email é valido
        if(! android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
            email.setError(getResources().getString(R.string.error_email_valid));
            return false;
        }

        // verifica se senha está preenchida
        if (password.getText() == null || password.getText().toString().isEmpty()){
            password.setError(getResources().getString(R.string.error_pass_null));
            return false;
        }

        return true;
    }
}
