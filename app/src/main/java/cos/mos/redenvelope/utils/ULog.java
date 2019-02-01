package cos.mos.redenvelope.utils;

import android.util.Log;

/**
 * @Description: <p>
 * @Author: Kosmos
 * @Date: 2019.02.01 13:12
 * @Email: KosmoSakura@gmail.com
 */
public class ULog {
    public static void commonD(String str) {
        Log.d("Kosmos", str);
    }

    public static void commonV(String str) {
        Log.v("Kosmos", str);
    }

    public static void commonE(String str) {
        Log.e("Kosmos", str);
    }

    public static void commonW(String str) {
        Log.w("Kosmos", str);
    }

    public static void commonI(String str) {
        Log.i("Kosmos", str);
    }
}
