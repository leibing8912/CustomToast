package cn.jianke.customtoast;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * @className: CustomToast
 * @classDescription: 自定义Toast
 * @author: leibing
 * @createTime: 2016/12/6
 */
public class CustomToast {
    // 短显示时间
    public final static int LENGTH_SHORT = 1000;
    // 长显示时间
    public final static int LENGTH_LONG = 2000;
    // 窗体管理者
    private WindowManager wm;
    private WindowManager.LayoutParams mParams;
    private View mView;
    // 掩藏Toast时间
    private int hideTime = LENGTH_SHORT;
    // 单例
    private static CustomToast instance;

    /**
     * 单例（双重锁）
     * @author leibing
     * @createTime 2016/12/6
     * @lastModify 2016/12/6
     * @param
     * @return
     */
    public static CustomToast getInstance(){
        if (instance == null){
            synchronized (CustomToast.class){
                if (instance == null)
                    instance = new CustomToast();
            }
        }
        return instance;
    }

    /**
     * 显示自定义Toast
     * @author leibing
     * @createTime 2016/12/6
     * @lastModify 2016/12/6
     * @param message 消息内容
     * @param context 上下文
     * @return
     */
    public void show(String message, Context context, int delayTime) {
        try {
            wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            mView = LayoutInflater.from(context).inflate(R.layout.layout_custom_toast, null);
            mParams = new WindowManager.LayoutParams();
            TextView showTv = (TextView) mView.findViewById(R.id.mbMessage);
            if (message != null && !message.equals(""))
                showTv.setText(message.trim());
            WindowManager.LayoutParams params = mParams;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            params.width = WindowManager.LayoutParams.WRAP_CONTENT;
            wm.addView(mView ,params);
            if (delayTime == LENGTH_SHORT){
                hideTime = LENGTH_SHORT;
            }else {
                hideTime = LENGTH_LONG;
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    hide();
                }
            }, hideTime);
        }catch (Exception ex){
        }
    }

    /**
     * 隐藏自定义Toast
     * @author leibing
     * @createTime 2016/12/6
     * @lastModify 2016/12/6
     * @param
     * @return
     */
    private void hide() {
        if (wm != null) {
            if (mView != null) {
                wm.removeView(mView);
                mView = null;
            }
            if (mView == null) {
                wm = null;
            }
        }
    }
}
