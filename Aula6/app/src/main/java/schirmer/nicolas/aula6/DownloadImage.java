package schirmer.nicolas.aula6;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadImage extends AsyncTask<String, Integer, Bitmap> {

    private Context context;
    private DownloadListener listener;

    ProgressBar progressBar;

    public DownloadImage(Context context, ProgressBar progressBar, DownloadListener listener){
        this.context = context;
        this.listener = listener;
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();

        progressBar.setIndeterminate(true);

    }

    @Override
    protected Bitmap doInBackground(String... params) {

        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://i.stack.imgur.com/fRxIQ.png")
                .build();

        Response response = null;

        try {
          response = okHttpClient.newCall(request).execute();

        } catch (Exception e){e.printStackTrace();}

        if (response != null) {
            InputStream inputStream = response.body().byteStream();

            return BitmapFactory.decodeStream(inputStream);

        } else return null;


    }

    @Override
    protected void onPostExecute(Bitmap bitmap){
        progressBar.setVisibility(View.GONE);

        if(bitmap != null){
            listener.getImg(bitmap);
        }
    }

}
