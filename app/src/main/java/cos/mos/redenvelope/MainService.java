package cos.mos.redenvelope;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

import cos.mos.redenvelope.init.Code;
import cos.mos.redenvelope.utils.ULog;
import cos.mos.redenvelope.utils.UText;

/**
 * @Description: 辅助通道服务
 * @Author: Kosmos
 * @Date: 2019.02.01 13:18
 * @Email: KosmoSakura@gmail.com
 */
public class MainService extends AccessibilityService {
    private boolean isOpenRP;//红包时候已被打开
    private boolean isOpenDetail = false;
    private AccessibilityNodeInfo nodeInfo;
    private List<AccessibilityNodeInfo> infoList;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (!Code.status.get()) {
            return;
        }
        nodeInfo = event.getSource();
        if (nodeInfo == null) {
            return;
        }
        try {
            //监听窗口改变
            if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED
                || event.getEventType() == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) {

                List<AccessibilityNodeInfo> targetList = nodeInfo.findAccessibilityNodeInfosByText("红包");
                if (UText.isEmpty(targetList)) {
                    return;
                }
                ULog.commonD("发现红包 : " + targetList.size());
                for (int i = 0; i < targetList.size(); i++) {
                    AccessibilityNodeInfo innerInfo = targetList.get(i);
                    if (UText.isEmpty(innerInfo.findAccessibilityNodeInfosByText("已被领完"))) {
                        innerInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    }
                }
//                targetList.get(0).getParent().performAction(AccessibilityNodeInfo.ACTION_CLICK);



//                infoList = nodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/b5m");
//                //在首页
//                if (!UText.isEmpty(infoList)) {
//                    ULog.commonD("在首页 : ");
//                    targetList.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
////                    for (int i = 0; i < mainList.size(); i++) {
////                        AccessibilityNodeInfo info = mainList.get(i);
////                        if (info != null && !UText.isEmpty(info.findAccessibilityNodeInfosByText("微信红包"))) {
////                            //进入聊天页面
////                            ULog.commonD("进入聊天页面 : ");
////                            info.performAction(AccessibilityNodeInfo.ACTION_CLICK);
////                        }
////                    }
//                }
//                infoList = nodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/amh");
//                infoList.addAll(nodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/am_"));
//                if (!UText.isEmpty(infoList)) {
//                    //在聊天页面
//                    ULog.commonD("在聊天页面");
//                    targetList.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
//                }
////                targetList.get(0).getParent().performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
//        int eventType = event.getEventType();
//        switch (eventType) {
//            //监听窗口改变
//                String className = event.getClassName().toString();
//                //微信聊天界面
//                if (Code.LAUCHER.equals(className)) {
//                    findRedPacket(getRootInActiveWindow());//遍历红包
//                }
//
//                //判断是否是显示‘开’的那个红包界面
//                if (Code.LUCKEY_MONEY_RECEIVER.equals(className)) {
//                    openRedPacket(getRootInActiveWindow());//开抢
//                }
//
//                //判断是否是红包领取后的详情界面
//                if (isOpenDetail && Code.LUCKEY_MONEY_DETAIL.equals(className)) {
//                    isOpenDetail = false;
//                    UIntent.goHome();//桌面
//                }
//                break;
//        }
    }


    @Override
    public void onInterrupt() {
        ULog.commonD("我快被系统干死了...");
    }

    @Override
    protected void onServiceConnected() {
        ULog.commonD("抢红包服务就绪");
        super.onServiceConnected();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        ULog.commonD("服务已关闭");
        return super.onUnbind(intent);
    }
}
