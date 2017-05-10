package schirmer.nicolas.appaula03;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editText = (EditText) findViewById(R.id.editText);
    }


    public void saveClick(View view){
        Intent intent = new Intent();
        intent.putExtra("RESULT_STRING", editText.getText().toString());
        Log.d("mLog", editText.getText().toString());
        setResult(101, intent);
        finish();
    }
}
