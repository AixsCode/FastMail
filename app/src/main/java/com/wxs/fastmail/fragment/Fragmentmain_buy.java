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
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.wxs.fastmail.Dialog.BuyDialog;
import com.wxs.fastmail.Events.OrderthreeEvent;
import com.wxs.fastmail.R;
import com.wxs.fastmail.activity.DispatchActivity;
import com.wxs.fastmail.activity.ProaddresserActivity;
import com.wxs.fastmail.adapter.RecyclerViewBuyAadpter;
import com.wxs.fastmail.bean.OrderUserThree;
import com.wxs.fastmail.tool.Tool_ParseJson;
import com.wxs.fastmail.tool.Tool_Url;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by wxs on 2017/7/10.
 */

public class Fragmentmain_buy extends Fragment implements PullLoadMoreRecyclerView.PullLoadMoreListener
{

    @Bind(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    @Bind(R.id.fab_publish)
    FloatingActionButton fabPublish;
    @Bind(R.id.fam)
    FloatingActionMenu fam;
    private RecyclerViewBuyAadpter mRecyclerViewAdapter=new RecyclerViewBuyAadpter(getActivity());
    private RecyclerView mRecyclerView;
    private  static  final  int dispatchtag=500;
    private static  final String TAG="Fragmentmain_buy";
    private static  final int dispatch=124;
    private  Handler mHandler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what)
            {
                case dispatch:
                    Intent  intent=new Intent(getActivity(), DispatchActivity.class);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                    break;
            }
            return false;
        }
    });
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(getActivity(), R.layout.fragmentmain_buy, null);
        ButterKnife.bind(this, view);
        initialize();
        return view;
    }
    private void initialize() {
        //获取Rcecyclerview对象
        mRecyclerView = pullLoadMoreRecyclerView.getRecyclerView();
        //设置下拉刷新
        pullLoadMoreRecyclerView.setRefreshing(true);
        //设置上拉刷新
        pullLoadMoreRecyclerView.setFooterViewText("努力加载中.. ");

        //设置上拉刷新文字颜色
        pullLoadMoreRecyclerView.setFooterViewTextColor(R.color.black);

        //设置线性布局
        pullLoadMoreRecyclerView.setLinearLayout();

        pullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);

        pullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);

        mRecyclerViewAdapter.setOnItemClickListener(new RecyclerViewBuyAadpter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position)
            {
                dialog();
            }
        });
        pullLoadMoreRecyclerView.setColorSchemeResources(R.color.colorPrimary);
        pullLoadMoreRecyclerView.scrollToTop();
        getData();
    }
    private void dialog()
    {
        final BuyDialog dlg = new BuyDialog(getContext(), R.style.PostDialog);
        dlg.show();
        Window window = dlg.getWindow();
        window.setContentView(R.layout.buy_dialog);
        Button btn = (Button)window.findViewById(R.id.btn_buy);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               // OkHttpUtils_Url_orderUserThree_sendAcceptOrder();
                // pay();
                dlg.dismiss();
                Intent intent =new Intent(getActivity(), ProaddresserActivity.class);
                startActivity(intent);
                OrderthreeEvent orderthreeEvent =new OrderthreeEvent("如影随行","");
                EventBus.getDefault().postSticky(orderthreeEvent);

                Toasty.success(getContext(),"接单成功", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private  void OkHttpUtils_Url_orderUserThree_getAllPublishedByGet()
    {
        OkHttpUtils
                .get()
                .url(Tool_Url.getUrl_orderUserThree_getAllPublishedByGet())
                .build()
                .execute(new MyStringCallback());

    }
//    private void  OkHttpUtils_Url_orderUserThree_sendAcceptOrder() {
//        OkHttpUtils
//                .post()
//                .url(Tool_Url.getUrl_orderUserThree_sendAcceptOrder())
//                .addParams("userSendId","7")
//                .addParams("orderId","3")
//                .build()
//                .execute(new MyStringCallback());
//    }
    private void getData()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpUtils_Url_orderUserThree_getAllPublishedByGet();

                        pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                    }
                });
            }
        }, 2100);
    }
    public void clearData()
    {
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
        setRefresh();
        getData();
    }
    private void setRefresh() {
        mRecyclerViewAdapter.clearData();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    @OnClick(R.id.fab_publish)
    public void onViewClicked() {

        if(dispatchtag>=500)
        {
            Toasty.success(getActivity(),"可以配送,刷新中···",Toast.LENGTH_LONG).show();
            Message msg=Message.obtain(mHandler);
            msg.what=dispatch;
            mHandler.sendMessageDelayed(msg,2200);
        }
        else
        {
            Toasty.normal(getActivity(),"请到个人主页先申请快递员哦",Toast.LENGTH_SHORT).show();
        }
    }
    private class MyStringCallback extends StringCallback {
        @Override
        public void onBefore(Request request, int id) {

            Log.i(TAG, "onBefore");
        }
        @Override
        public void onAfter(int id) {
            Log.i(TAG, "onAfter");
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            Log.i(TAG, "onError");
            e.printStackTrace();
        }

        @Override
        public void onResponse(String response, int id)
        {
            Log.i(TAG, "onResponse \t" + response);
            List<OrderUserThree> orderUserThreeList = Tool_ParseJson.getOrderUserList("orderUserThreeList", response);
            mRecyclerViewAdapter.addAllData(orderUserThreeList);
        }
    }
}
