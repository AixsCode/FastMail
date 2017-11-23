package com.wxs.fastmail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.wxs.fastmail.Dialog.DispatchDialog;
import com.wxs.fastmail.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import io.rong.imkit.RongIM;

public class ProaddresserActivity extends AppCompatActivity
{
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_contact1)
    TextView tvContact1;
    @Bind(R.id.btn_dispatchmy)
    Button btnDispatchmy;
    @Bind(R.id.btn_dispatchother)
    Button btnDispatchother;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proaddresser);
        ButterKnife.bind(this);
    }
    private void RongIM()
    {
        if (RongIM.getInstance() != null)
        {
            RongIM.getInstance().startPrivateChat(ProaddresserActivity.this, "2015", "思想");
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
    }
    @OnClick({R.id.back, R.id.tv_contact1, R.id.btn_dispatchmy, R.id.btn_dispatchother})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.back:
                ProaddresserActivity.this.finish();
                break;
            case R.id.tv_contact1:
                RongIM();
                break;
            case R.id.btn_dispatchmy:
                Intent intent =new Intent(ProaddresserActivity.this,BaiduNaviActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_dispatchother:
                Toasty.normal(ProaddresserActivity.this,"他人配送", Toast.LENGTH_SHORT).show();
                dialog();
                break;
            default:
                break;
        }
    }
    private  void dialog()
    {
        final DispatchDialog dlg = new DispatchDialog(ProaddresserActivity.this, R.style.PostDialog);
        dlg.show();
        Window window = dlg.getWindow();
        window.setContentView(R.layout.dispatch_dialog);
        Button btn = (Button)window.findViewById(R.id.btn_dispatch);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // pay();
                dlg.dismiss();
                Toasty.success(ProaddresserActivity.this,"发单成功", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(ProaddresserActivity.this,ProaddressernextActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
            }
        });
    }
}
