package schirmer.nicolas.aula08;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class ServiceAfterBoot extends Service {

    // binding de serviços
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // criação do serviço
    @Override
    public void onCreate() {
        super.onCreate();
        // faz algo algumas vezes
        repeat();

        PendingIntent pintent =
                PendingIntent.getService(this, 0, new Intent(this, ServiceAfterBoot.class), 0);
        AlarmManager alarm = (AlarmManager)
                getSystemService(Context.ALARM_SERVICE);

        alarm.setRepeating(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() , 60*1000, pintent);
    }

    @Override
    public int onStartCommand(Intent intent, int flag, int id){
        repeat();
        return START_STICKY;
    }

    private void repeat(){
        Toast.makeText(
                getApplicationContext()
                , "fui chamado"
                , Toast.LENGTH_LONG)
                .show();

        Log.d("mLog", "bla");
    }

    // quando o serviço for morto
    @Override
    public void onDestroy(){
        super.onDestroy();
    }

}
