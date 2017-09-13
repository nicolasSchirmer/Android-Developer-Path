package nicolas.schirmer.aula03;

import android.app.Activity;
import android.content.Intent;

public class SplashActivity extends Activity {

    @Override
    public void onStart(){
        super.onStart();

        // qualquer codigo pra inciar aqui

        // if (temCadastro)
        // if (inicia de uma cor)
        // if (tem conexao)

        startActivity(new Intent(this, MainActivity.class));
        finish(); // remove da memoria
    }

}
