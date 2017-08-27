package com.wpy.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.wpy.com.R;

/**
 * Created by dell on 2017/8/27.
 */

public class ExPandableAdapter extends BaseExpandableListAdapter {
    private String[] groups;
    private String[][] childs;
    private Context context;

    public ExPandableAdapter(String[] groups, String[][] childs, Context context) {
        this.groups = groups;
        this.childs = childs;
        this.context = context;
    }

    //返回一级列表的个数
    @Override
    public int getGroupCount() {
        return groups.length;
    }

    //返回每个二级列表的个数
    @Override
    public int getChildrenCount(int groupPosition) {
        return childs.length;
    }

    //返回一级列表的单个item（返回的是对象）
    @Override
    public Object getGroup(int groupPosition) {
        return groups[groupPosition];
    }

    //返回二级列表中的单个item（返回的是对象）
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childs[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //每个item的id是否是固定？一般为true
    @Override
    public boolean hasStableIds() {
        return true;
    }

    //【重要】填充一级列表
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupsViewHolder groupsViewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_group,null);
            groupsViewHolder = new GroupsViewHolder();
            groupsViewHolder.nameTv = (TextView) convertView.findViewById(R.id.name_tv);
            convertView.setTag(groupsViewHolder);
        }else {
            groupsViewHolder = (GroupsViewHolder) convertView.getTag();
        }
        groupsViewHolder.nameTv.setText(groups[groupPosition]);
        return convertView;
    }

    //【重要】填充二级列表
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildsViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_child,null);
            viewHolder = new ChildsViewHolder();
            viewHolder.titleTv = (TextView) convertView.findViewById(R.id.title_tv);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ChildsViewHolder) convertView.getTag();
        }
        viewHolder.titleTv.setText(childs[groupPosition][childPosition]);
        return convertView;
    }

    //二级列表中的item是否能够被选中？可以改为true
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    class GroupsViewHolder{
        TextView nameTv;
    }
    class ChildsViewHolder{
        TextView titleTv;
    }
}
