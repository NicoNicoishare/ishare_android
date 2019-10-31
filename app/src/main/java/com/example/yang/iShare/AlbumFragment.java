package com.example.yang.iShare;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.yang.iShare.Utils.HelloHttp;
import com.example.yang.iShare.adapter.AlbumAdapter;
import com.example.yang.iShare.bean.Dynamic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;


public class AlbumFragment extends Fragment{
    private List<Dynamic> mDynamicList;
    private RecyclerView recyclerView;
    private AlbumAdapter adapter;
    protected View view;
    private static int Userid = -10;
    public AlbumFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            Userid = bundle.getInt("id");
        }
        if (Userid == -1) {
            MainApplication app = MainApplication.getInstance();
            Map<String, Integer> mapParam = app.mInfoMap;
            for(Map.Entry<String, Integer> item_map:mapParam.entrySet()) {
                if(item_map.getKey().equals("id")) {
                    Userid = item_map.getValue();
                }
            }
        }
        initView();
        initData();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_album);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        //recyclerView.addItemDecoration();
    }

    private void initData() {
        mDynamicList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        mDynamicList = new ArrayList<>();
        
    }
}
