package schirmer.nicolas.aula05;


import android.content.Context;
import android.content.res.Resources;

public class Helper {

    private static String photo = "photo_";

    public static int getPeripheralsLeftIconResourceId(Resources resources,
                                                       String drawableName,
                                                       Context context) {
        return resources.getIdentifier(photo + drawableName.toLowerCase(), "drawable",
                context.getPackageName());
    }
}
