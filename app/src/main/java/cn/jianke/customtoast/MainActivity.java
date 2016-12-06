package cn.jianke.customtoast;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * @className:MainActivity
 * @classDescription:测试消息通知权限
 * @author: leibing
 * @createTime: 2016/12/6
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // onClick
        findViewById(R.id.btn_notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                // 处理消息通知权限是否可用
                dealNotificationEnabledForDialog();
            }
        });
    }

    /**
     * 处理消息通知权限是否可用(Toast处理)
     * @author leibing
     * @createTime 2016/12/6
     * @lastModify 2016/12/6
     * @param
     * @return
     */
    public void dealNotificationEnabledForToast(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT){
            // 4.4版本以上处理
            if (ToastUtils.isNotificationEnabled(MainActivity.this)){
                // 可用（正常弹窗即可）
                Toast.makeText(MainActivity.this, "正常Toast", Toast.LENGTH_SHORT).show();
            }else {
                // 采用自定义方式处理
                CustomToast.getInstance().show("自定义Toast", MainActivity.this,
                        CustomToast.LENGTH_SHORT);
            }
        }else {
            // 4.3版本以下处理
            try {
                // 正常弹窗处理
                Toast.makeText(MainActivity.this, "正常Toast", Toast.LENGTH_SHORT).show();
            }catch (Exception ex){
                // 捕捉异常处理
            }
        }
    }

    /**
     * 处理消息通知权限是否可用(Dialog处理)
     * @author leibing
     * @createTime 2016/12/6
     * @lastModify 2016/12/6
     * @param
     * @return
     */
    public void dealNotificationEnabledForDialog(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT){
            // 4.4版本以上处理
            if (ToastUtils.isNotificationEnabled(MainActivity.this)){
                // 可用（正常弹窗即可）

            }else {
                // 采用自定义方式处理
                CustomDialog.getInstance().show("标题", "内容", MainActivity.this,
                        new CustomDialog.CustomDialogListener() {
                            @Override
                            public void onClick(int target) {
                            }
                        });
            }
        }else {
            // 4.3版本以下处理
            try {
                // 正常弹窗处理

            }catch (Exception ex){
                // 捕捉异常处理
                // 采用自定义方式处理
                CustomDialog.getInstance().show("标题", "内容", MainActivity.this,
                        new CustomDialog.CustomDialogListener() {
                            @Override
                            public void onClick(int target) {
                            }
                        });
            }
        }
    }
}
