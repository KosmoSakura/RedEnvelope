package cos.mos.redenvelope.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @Description: 拖拽
 * @Author: Kosmos
 * @Date: 2018.09.23 18:26
 * @Email: KosmoSakura@gmail.com
 */
public class DragTextView extends ScaleTextView {
    private float sx, sy;//二维坐标
    private MovingListener listener;//四向坐标监听器

    public interface MovingListener {
        void moving();
    }

    public DragTextView(Context context) {
        super(context);
    }

    public DragTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void setOnMovingListener(MovingListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //手指坐标
                sx = event.getRawX();
                sy = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                //手指移动坐标
                float x = event.getRawX();
                float y = event.getRawY();
                // 获取手指移动的距离
                int dx = (int) (x - sx);
                int dy = (int) (y - sy);
                // 得到imageView最开始的各顶点的坐标
                layout(getLeft() + dx, getTop() + dy, getRight() + dx, getBottom() + dy);
                sx = event.getRawX();
                sy = event.getRawY();
                if (listener != null) {
                    listener.moving();
                }
                break;
            case MotionEvent.ACTION_UP:
                performClick();
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }
}
