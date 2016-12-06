package cn.jianke.customtoast;

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
                boolean isNotificationEnabled = ToastUtils.isNotificationEnabled(MainActivity.this);
                Toast.makeText(MainActivity.this, "消息通知权限测试", Toast.LENGTH_SHORT).show();
                System.out.println("dddddddddddddddddddddddd isNotificationEnabled  = " + isNotificationEnabled);
                CustomToast.getInstance().show("测试一下数据", MainActivity.this, CustomToast.LENGTH_SHORT);
            }
        });
    }

}
