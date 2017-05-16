package schirmer.nicolas.aula6;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        DownloadImage downloadImage = new DownloadImage(this, progressBar,
                new DownloadListener() {
            @Override
            public void getImg(Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
            }
        });

        downloadImage.execute();



    }
}
