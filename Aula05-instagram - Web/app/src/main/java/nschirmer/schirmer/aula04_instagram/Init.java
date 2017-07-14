package nschirmer.schirmer.aula04_instagram;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;


public class Init extends Application {

    @Override
    public void onCreate(){
        super.onCreate();

        Fresco.initialize(this); // inicia Fresco (image buffer)
    }
}
