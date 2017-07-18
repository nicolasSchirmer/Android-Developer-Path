package nschirmer.schirmer.aula6;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.FileNotFoundException;

public class CriaPerfil extends AppCompatActivity {

    ImageView foto;
    EditText nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criaperfil);

        nome = (EditText) findViewById(R.id.editText3);

        foto = (ImageView) findViewById(R.id.imageView);
        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 101);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == 101){
            if(resultCode == RESULT_OK){
                Uri targetUri = intent.getData();

                Bitmap bitmap;
                try{
                    bitmap = BitmapFactory.decodeStream(
                            getContentResolver().openInputStream(targetUri));

                    foto.setImageBitmap(bitmap);

                } catch (FileNotFoundException e){e.printStackTrace();}
            }
        }
    }

    public void perfilClick(View view){
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);

        sharedPreferences.edit().putString("nome", nome.getText().toString()).apply();
        

        startActivity(new Intent(this, Principal.class));
        finish();
    }
}
