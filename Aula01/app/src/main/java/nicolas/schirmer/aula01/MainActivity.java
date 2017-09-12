package nicolas.schirmer.aula01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.nome);

        //textView.setText("bla");

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //textView.setText(getText(R.string.app_name));
                //textView.setText(R.string.app_name);

                Intent intent = new Intent(getBaseContext(), Main2Activity.class);

                intent.putExtra("tag_nome", textView.getText().toString());

                startActivity(intent);
            }
        });


    }
}
