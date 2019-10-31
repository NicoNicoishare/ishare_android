package com.example.yang.iShare;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ajguan.library.EasyRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.yang.iShare.Utils.HelloHttp;
import com.example.yang.iShare.adapter.FollowPersonAdapter;
import com.example.yang.iShare.bean.Person;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Response;

public class SearchFragment extends Fragment {
    private View view;
    private Button btn_search;
    private EditText et_search;
    private List<Person> list;
    private RecyclerView recyclerView;
    private EasyRefreshLayout easyRefreshLayout;
    private FollowPersonAdapter adapter;
    private int last_user_id = 0;
    private int myId = -10;
    private String last_string = null;
    public static SearchFragment newInstance(String param1) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public SearchFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);
        MainApplication app = MainApplication.getInstance();
        Map<String, Integer> mapParam = app.mInfoMap;
        for(Map.Entry<String, Integer> item_map:mapParam.entrySet()) {
            if(item_map.getKey().equals("id")) {
                myId = item_map.getValue();
            }
        }
        if(myId == -10) {
            Toast.makeText(getContext(), "全局内存中保存的信息为空", Toast.LENGTH_SHORT).show();
        }
        list = new ArrayList<>();
        btn_search = (Button) view.findViewById(R.id.btn_search);
        et_search = (EditText) view.findViewById(R.id.et_search);
        et_search.setText("");
        Drawable db_nickname=getResources().getDrawable(R.drawable.search2);
        db_nickname.setBounds(0,0,75,75);
        et_search.setCompoundDrawables(db_nickname,null,null,null);
        initView();
        return view;
    }

    private void showToast(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        
    }

    private void initData(String s) {
        list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("searchType", "user");
        map.put("keyword", s);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("page", 1);
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken()
                    ,InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        et_search.setText("");
    }
}