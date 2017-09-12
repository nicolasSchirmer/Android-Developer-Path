package nicolas.schirmer.aula02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textEdit, textBio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textBio = (TextView) findViewById(R.id.text_bio);
        textEdit = (TextView) findViewById(R.id.text_edit);

        textEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Main2Activity.class);

                intent.putExtra("tag_bio", textBio.getText().toString());

                startActivityForResult(intent, 101);
            }
        });
    }

    // retorno de dado da outra activity
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        if(requestCode ==101){
            if(resultCode == RESULT_OK){
                String bio = intent.getStringExtra("new_bio");

                // debug
                //Log.d("tag", bio);

                textBio.setText(bio);
            }
        }
    }

}
