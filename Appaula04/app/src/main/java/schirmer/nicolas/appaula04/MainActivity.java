package schirmer.nicolas.appaula04;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

public class MainActivity extends AppCompatActivity {

    Button textbuttonw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textbuttonw = (Button) findViewById(R.id.button2);
    }

    public void buttonClick(View view){
        switch (view.getId()){
            case R.id.button:

                dialogDefault();

                break;
            case R.id.button2:
                //materialDialog();
                listDialog();
                break;
            case R.id.button3:
                //listDialog();
                customDialog();
                break;
        }
    }

    private void dialogDefault(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.title)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                }).create();
        builder.show();

    }

    private void materialDialog(){
        new MaterialDialog.Builder(this)
                .positiveText(R.string.yes)
                .positiveColorRes(R.color.colorAccent)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog,
                                        @NonNull DialogAction which) {
                        finish();
                    }
                })
                .negativeText(R.string.no)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog,
                                        @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                }).show();
    }

    private void listDialog(){
        new MaterialDialog.Builder(this)
                .title(R.string.title)
                .items(R.array.mArray)
                .itemsColor(ContextCompat.getColor(this ,R.color.colorAccent))
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog,
                                            View view, int which, CharSequence text) {
                        String[] mArray = getResources().getStringArray(R.array.mArray);

                        textbuttonw.setText(mArray[which]);
                    }
                })
                .show();
    }

    private void customDialog(){
        new MaterialDialog.Builder(this)
                .title(R.string.title)
                .customView(R.layout.dialog, true)
                .positiveText(R.string.yes)
                .show();
    }

}
