package nschirmer.schirmer.aula7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.FileNotFoundException;

public class CreateProfile extends AppCompatActivity {

    EditText name;
    SharedPreferences sharedPreferences;
    CircularImageView photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        sharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        name = (EditText) findViewById(R.id.profile_name);
        photo = (CircularImageView) findViewById(R.id.profile_photo);
    }

    public void photoClick(View view){
        // abre galeria
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 101);
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

                    // salva uri da imagem
                    sharedPreferences.edit().putString("profilePhoto", targetUri.toString()).apply();
                    // Uri imageUri = Uri.parse(imageUriString); <-- parse

                    photo.setImageBitmap(bitmap);

                } catch (FileNotFoundException e){e.printStackTrace();}
            }
        }
    }

    public void createClick(View view){
        if(validField()){
            // salva dados
            sharedPreferences.edit().putString("userName", name.getText().toString()).apply();
            sharedPreferences.edit().putBoolean("logged", true).apply();

            // inicia activity sem histoico de activities
            startActivity(new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
        }
    }

    private boolean validField(){
        if(name.getText() == null || name.getText().toString().isEmpty()){
            name.setError(getResources().getString(R.string.error_name_null));
            return false;

        } else {
            return true;
        }
    }

    // volta pra activity anterior
    public void backClick(View view){
        onBackPressed();
    }
}
