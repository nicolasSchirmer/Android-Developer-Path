package nschirmer.schirmer.aula9;


import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder surfaceHolder;
    private Camera camera;

    public CameraPreview(Context context, Camera camera){
        super(context);

        this.camera = camera;

        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder){
        try{
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();

        } catch (IOException e){e.printStackTrace();}
    }

    // se activity for destruida
    public void surfaceDestroyed(SurfaceHolder surfaceHolder){
        if(camera != null){
            camera.stopPreview();
            camera.release();
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int w, int h){
        if(this.surfaceHolder != null){
            try {
                camera.stopPreview();

                camera.setPreviewDisplay(surfaceHolder);
                camera.startPreview();

            } catch (Exception e){e.printStackTrace();}
        }
    }
}
