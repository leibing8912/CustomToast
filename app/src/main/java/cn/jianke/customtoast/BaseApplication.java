package cn.jianke.customtoast;

import android.app.Application;

/**
 * @className: BaseApplication
 * @classDescription: 应用实例
 * @author: leibing
 * @createTime: 2016/12/6
 */
public class BaseApplication extends Application{
    // 单例
    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    /**
     * 获取单例
     * @author leibing
     * @createTime 2016/12/6
     * @lastModify 2016/12/6
     * @param
     * @return
     */
    public synchronized static BaseApplication getInstance(){
        if (instance == null){
            instance = new BaseApplication();
        }
        return instance;
    }
}
