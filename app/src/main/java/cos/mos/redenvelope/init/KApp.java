package cos.mos.redenvelope.init;

import android.app.Application;

/**
 * @Description: <p>
 * @Author: Kosmos
 * @Date: 2019.02.01 13:11
 * @Email: KosmoSakura@gmail.com
 */
public class KApp extends Application {
    private static KApp instances;

    public static KApp getInstance() {
        return instances;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instances = this;
    }
}
