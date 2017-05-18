package schirmer.nicolas.aula09;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    FrameLayout frame;
    Camera camera;
    ImageSurfaceView imageSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        camera = checkDeviceCamera();

        requestPermission();

        frame = (FrameLayout) findViewById(R.id.frame);

        setView();
    }

    private Camera checkDeviceCamera(){
        Camera mCamera = null;
        try {
            mCamera = Camera.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mCamera;
    }

    private void requestPermission(){
        if ( ContextCompat.checkSelfPermission( this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ) {

            ActivityCompat.requestPermissions( this,
                    new String[] {Manifest.permission.CAMERA}, 100);
        }

        if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission( this,
                        Manifest.permission.CAMERA ) != PackageManager.PERMISSION_GRANTED) {
                setView();

        } else if(Build.VERSION.SDK_INT < 23){
           setView();
        }
    }

    private void setView(){
        imageSurfaceView = new ImageSurfaceView(MainActivity.this, camera);
        frame.addView(imageSurfaceView);
    }

    Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            if(bitmap==null){
                Toast.makeText(MainActivity.this, "Captured image is empty", Toast.LENGTH_LONG).show();
                return;
            }

            saveBitmap(bitmap);
            //capturedImageHolder.setImageBitmap(scaleDownBitmapImage(bitmap, 300, 200 ));

        }
    };

    private void saveBitmap(Bitmap bitmap){
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("bla");
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    // button click
    public void takePhoto(View view){
        camera.takePicture(null, null, pictureCallback);

        startActivity(new Intent(this, Main2Activity.class));
    }
}
