package cos.mos.redenvelope.init;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Description: <p>
 * @Author: Kosmos
 * @Date: 2019.02.01 13:53
 * @Email: KosmoSakura@gmail.com
 */
public interface Code {
    //原子状态
    AtomicBoolean status = new AtomicBoolean(false);
    String LAUCHER = "com.tencent.mm.ui.LauncherUI";//微信聊天界面
    String LUCKEY_MONEY_DETAIL = "com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyDetailUI";//点击红包弹出的界面
    String LUCKEY_MONEY_RECEIVER = "com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyReceiveUI";//红包领取后的详情界面

}
