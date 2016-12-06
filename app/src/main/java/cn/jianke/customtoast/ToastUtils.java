package cn.jianke.customtoast;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @className: ToastUtils
 * @classDescription: Toast工具类
 * @author: leibing
 * @createTime: 2016/12/6
 */
public class ToastUtils {
    private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
    private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";

    /**
     * 是否拥有消息通知权限
     * @author leibing
     * @createTime 2016/12/6
     * @lastModify 2016/12/6
     * @param context
     * @return
     */
    public static boolean isNotificationEnabled(Context context) {

        AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);

        ApplicationInfo appInfo = context.getApplicationInfo();

        String pkg = context.getApplicationContext().getPackageName();

        int uid = appInfo.uid;

        Class appOpsClass = null; /* Context.APP_OPS_MANAGER */

        try {

            appOpsClass = Class.forName(AppOpsManager.class.getName());

            Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE, String.class);

            Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);
            int value = (int)opPostNotificationValue.get(Integer.class);

            return ((int)checkOpNoThrowMethod.invoke(mAppOps,value, uid, pkg) == AppOpsManager.MODE_ALLOWED);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }
}
