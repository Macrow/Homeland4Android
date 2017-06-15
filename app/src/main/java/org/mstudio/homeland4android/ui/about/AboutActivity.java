package org.mstudio.homeland4android.ui.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import org.mstudio.homeland4android.R;
import org.mstudio.homeland4android.ui.base.BaseActivity;
import org.mstudio.homeland4android.util.AppConstant;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnBack)
    public void goBack() {
        finish();
    }

    @OnClick(R.id.tvGithubLink)
    public void openGithubLink() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri uri = Uri.parse(AppConstant.GITHUB_LINK);
        intent.setData(uri);
        startActivity(intent);
    }
}
