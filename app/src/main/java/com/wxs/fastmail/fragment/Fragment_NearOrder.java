package com.wxs.fastmail.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.tapadoo.alerter.Alerter;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.wxs.fastmail.Dialog.InsuranceDialog;
import com.wxs.fastmail.Events.OrderpostEvent;
import com.wxs.fastmail.Events.OrderthreeEvent;
import com.wxs.fastmail.Events.WayEvent;
import com.wxs.fastmail.R;
import com.wxs.fastmail.activity.DisguideActivity;
import com.wxs.fastmail.activity.DisguidepostActivity;
import com.wxs.fastmail.activity.InsuranceActivity;
import com.wxs.fastmail.adapter.RecyclerViewpachAdapter;
import com.wxs.fastmail.tool.Tool_GetData;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by wxs on 2017/7/28.
 */

public class Fragment_NearOrder extends Fragment implements PullLoadMoreRecyclerView.PullLoadMoreListener
{
    @Bind(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    private RecyclerView mRecyclerView;
    private RecyclerViewpachAdapter mRecyclerViewAdapter;
    private  static  final  String TAG="Fragment_NearOrder";
    private  static  final  int postguide=666;
    private  static  final  int postguide1=777;
    private  int flag=0;
    private  Handler mhandler=new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            switch (msg.what) {
                case  postguide:
                    Intent intent =new Intent(getContext(), DisguideActivity.class);
                    startActivity(intent);
                    break;
                case postguide1:
                    Intent intent1=new Intent(getContext(),DisguidepostActivity.class);
                    startActivity(intent1);
                    break;
                default:
                    break;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_nearorder, null);
        ButterKnife.bind(this, view);
        initialize();
        return view;
    }
    private void initialize()
    {
        mRecyclerView = pullLoadMoreRecyclerView.getRecyclerView();

        pullLoadMoreRecyclerView.setRefreshing(true);
        //设置上拉刷新
        pullLoadMoreRecyclerView.setFooterViewText("努力加载中.. ");

        //设置上拉刷新文字颜色
        pullLoadMoreRecyclerView.setFooterViewTextColor(R.color.black);

        //设置线性布局
        pullLoadMoreRecyclerView.setLinearLayout();

        pullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);

        mRecyclerViewAdapter = new RecyclerViewpachAdapter(getContext());

        pullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerViewAdapter.setOnItemClickListener(new RecyclerViewpachAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position)
            {
                if(position==0)
                {
                    if(flag==0) {
                        dialog();
                        flag++;
                    }
                    else
                    {
                        Alerter.create(getActivity())
                                .setTitle("乐货派")
                                .setBackgroundColorRes(R.color.colorAccent)
                                .setText("配送抢单成功")
                                .enableSwipeToDismiss()
                                .show();
                        OrderthreeEvent orderthreeEvent=new OrderthreeEvent("大西瓜","小可爱");
                        EventBus.getDefault().postSticky(orderthreeEvent);
                        WayEvent wayEvent =new WayEvent("滨湖区五爱人家正门","无锡市滨湖区太湖街道");
                        EventBus.getDefault().postSticky(wayEvent);
                        Message msg=Message.obtain(mhandler);
                        msg.what=postguide;
                        mhandler.sendMessageDelayed(msg,2000);
                    }
                }
                else if(position==1)
                {
                    Alerter.create(getActivity())
                            .setTitle("乐货派")
                            .setBackgroundColorRes(R.color.colorAccent)
                            .setText("寄送抢单成功")
                            .enableSwipeToDismiss()
                            .show();
                    OrderpostEvent orderpostEvent=new OrderpostEvent("史上最快");
                    EventBus.getDefault().postSticky(orderpostEvent);
                    Message msg=Message.obtain(mhandler);
                    msg.what=postguide1;
                    mhandler.sendMessageDelayed(msg,2000);
                }
                else if(position==3)
                {
                    Alerter.create(getActivity())
                            .setTitle("乐货派")
                            .setBackgroundColorRes(R.color.colorAccent)
                            .setText("商家订单抢单成功")
                            .enableSwipeToDismiss()
                            .show();
                    Message msg=Message.obtain(mhandler);
                    msg.what=postguide;
                    mhandler.sendMessageDelayed(msg,2000);
                }
                //
//                OrderOnlySend orderOnlySend=new OrderOnlySend();
//                User user=new User();
//                user.setId(4);
//                orderOnlySend.setId(7);
//                orderOnlySend.setUserDelivery(user);
//                OkHttpUtils
//                        .postString()
//                        .url(Tool_Url.getUrl_orderUserThree_sendAcceptOrder())
//                        .content(new Gson().toJson(orderOnlySend))
//                        .build()
//                        .execute(new MyStringCallback());

                // OrderUserThree orderOnlySend=new OrderUserThree();
            }
        });
        pullLoadMoreRecyclerView.setColorSchemeResources(R.color.colorPrimary);
        pullLoadMoreRecyclerView.scrollToTop();
        getData();
    }
    private void getData()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecyclerViewAdapter.addAllData(Tool_GetData.getPatchDataList());
                pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
             }
        }, 2200);
    }
    public void clearData() {
        mRecyclerViewAdapter.clearData();
        mRecyclerViewAdapter.notifyDataSetChanged();
    }
    @Override
    public void onRefresh() {
        setRefresh();
        getData();
    }
    @Override
    public void onLoadMore()
    {
    }
    private void setRefresh()
    {
        mRecyclerViewAdapter.clearData();
    }
    private class MyStringCallback extends StringCallback {
        @Override
        public void onBefore(Request request, int id) {

            Log.i(TAG, "onBefore");
        }
        @Override
        public void onAfter(int id)
        {
            Log.i(TAG, "onAfter");
        }
        @Override
        public void onError(Call call, Exception e, int id) {
            Log.i(TAG, "onError");
            e.printStackTrace();
        }
        @Override
        public void onResponse(String response, int id) {
            Log.i(TAG, "onResponse \t" + response);
        }
    }
    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    private void dialog()
    {
        final InsuranceDialog dlg = new InsuranceDialog(getActivity(), R.style.PostDialog);
        dlg.show();
        Window window = dlg.getWindow();
        window.setContentView(R.layout.insurance_dialog);
        Button btn = (Button) window.findViewById(R.id.btn_yes);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dlg.dismiss();
                Intent intent=new Intent(getActivity(), InsuranceActivity.class);
                startActivity(intent);
            }
        });
        Button btn1= (Button) window.findViewById(R.id.btn_deny);
        btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dlg.dismiss();
            }
        });
    }
}
