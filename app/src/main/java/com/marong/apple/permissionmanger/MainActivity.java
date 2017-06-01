package com.marong.apple.permissionmanger;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 0; // 请求码
    // 所需的全部权限
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.MODIFY_AUDIO_SETTINGS
    };
    private PermissionsChecker mPermissionsChecker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPermissionsChecker=new PermissionsChecker(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mPermissionsChecker.lacksPermissions(PERMISSIONS)) {

            startPermissionsActivity();
        }
    }

    private void startPermissionsActivity() {
        PermissionsActivity.startActivityForResult(this, REQUEST_CODE, PERMISSIONS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 拒绝时, 关闭页面, 缺少主要权限, 无法运行
        if (requestCode == REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            finish();
        }

    }
}
