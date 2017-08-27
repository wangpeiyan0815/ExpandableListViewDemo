package com.wpy.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.wpy.com.adapter.ExPandableAdapter;

public class MainActivity extends AppCompatActivity {
    //Model：定义的数据
    private String[] groups = {"北京市", "河南省", "河北省"};
    //注意，字符数组不要写成{{"A1,A2,A3,A4"}, {"B1,B2,B3,B4，B5"}, {"C1,C2,C3,C4"}}*/
    private String[][] childs = {{"海淀区", "朝阳区", "房山区", "大兴区"},
            {"禹州市", "许昌市", "周口市", "新乡市"}, {"保定市", "邯郸市", "石家庄"}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intiView();
    }

    private void intiView() {
        ExpandableListView expandList = (ExpandableListView) findViewById(R.id.expand_list);
        //如果设置打开关闭的图片可以设置 背景选择器
        expandList.setGroupIndicator(this.getResources().getDrawable(R.drawable.ic_launcher));
        ExPandableAdapter exPandableAdapter = new ExPandableAdapter(groups, childs, this);
        expandList.setAdapter(exPandableAdapter);
        //点击事件
        expandList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
                                        int childPosition, long id) {
                Toast.makeText(MainActivity.this, childs[groupPosition][childPosition].toString(),
                        Toast.LENGTH_SHORT);
                Log.i("TAG", "onChildClick: " + childs[groupPosition][childPosition]);
                return true;
            }
        });
    }
}
