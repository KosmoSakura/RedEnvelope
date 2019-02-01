package cos.mos.redenvelope;

import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import cos.mos.redenvelope.init.Code;
import cos.mos.redenvelope.init.KApp;
import cos.mos.redenvelope.utils.UDialog;
import cos.mos.redenvelope.utils.UIntent;

public class MainActivity extends AppCompatActivity {
    private TextView tStatus, tBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tStatus = findViewById(R.id.main_status);
        tBtn = findViewById(R.id.main_btn);
//        TextView tAbout = findViewById(R.id.main_txt);
//        tAbout.setText(Html.fromHtml(getString(R.string.description)));
//        tAbout.setMovementMethod(LinkMovementMethod.getInstance());
        refresh();
        //        if (!UPermissions.isAssistOn()) {
//            UDialog.getInstance(this, true, true)
//                .showNoticeWithOnebtn(getString(R.string.u_need_set_assist),
//                    (result, dia) -> {
//                        UIntent.goAssist();
//                        dia.dismiss();
//                    });
//            return;
//        }
    }

    public void mainClick(View view) {
        if (Code.status.get()) {
            Code.status.set(false);
        } else {
            if (isAssistOn()) {
                UDialog.getInstance(this, false, false)
                    .showNoticeWithTwobtn("", (result, dia) -> {
                        UIntent.goAssist();
                        dia.dismiss();
                    });
            }
        }
    }

    /**
     * 刷新按钮状态
     */
    private void refresh() {
        if (Code.status.get()) {
            tStatus.setText("当前状态：启动");
            tBtn.setText("点击我可以关闭程序");
            tStatus.setTextColor(ContextCompat.getColor(this, R.color.green_txt));
        } else {
            tStatus.setText("当前状态：关闭");
            tBtn.setText("这是个按钮\n它木的感情");
            tStatus.setTextColor(ContextCompat.getColor(this, R.color.red_dark));
        }
    }

    /**
     * 辅助功能服务/[无障碍通道]是否开启
     */
    private boolean isAssistOn() {
        int accessibilityEnabled;//默认为0
        try {
            accessibilityEnabled = Settings.Secure.getInt(KApp.getInstance().getContentResolver(),
                Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (Settings.SettingNotFoundException e) {
            //找不到这个页面
            accessibilityEnabled = 0;
        }

        if (accessibilityEnabled == 1) {
            String services = Settings.Secure.getString(KApp.getInstance().getContentResolver(),
                Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (services != null) {
                return services.toLowerCase().contains(KApp.getInstance().getPackageName().toLowerCase());
            }
        }
        return false;
    }


}
