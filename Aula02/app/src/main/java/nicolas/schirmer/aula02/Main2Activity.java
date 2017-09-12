package nicolas.schirmer.aula02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class Main2Activity extends AppCompatActivity {

    private ImageView checkButton;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editText = (EditText) findViewById(R.id.editText);

        String bio = getIntent().getStringExtra("tag_bio");

        editText.setText(bio);
    }

    // assim que o image for clicado esse método é chamado
    public void checkClick(View view){
        // de quem pra quem
        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra("new_bio", editText.getText().toString());

        setResult(RESULT_OK, intent);

        finish();
    }
}
