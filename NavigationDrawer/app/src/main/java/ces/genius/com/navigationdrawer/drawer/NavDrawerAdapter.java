package ces.genius.com.navigationdrawer.drawer;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;


import java.util.List;
import java.util.Map;

import ces.genius.com.navigationdrawer.R;

public class NavDrawerAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {
    private LayoutInflater inflater;
    private List<GroupItem> items;
    private Context mContext;

    public NavDrawerAdapter(Context context) {
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);
    }

    public void setData(List<GroupItem> items) {
        this.items = items;
    }

    public String getChild(int groupPosition, int childPosition) {
        return (String) ((GroupItem) this.items.get(groupPosition)).items.get(childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return (long) childPosition;
    }

    public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        TextViewHolder holder;
        String item = getChild(groupPosition, childPosition);
        if (convertView == null) {
            holder = new TextViewHolder();
            convertView = this.inflater.inflate(R.layout.nav_drawer_list_item, parent, false);
            holder.title = (TextView) convertView.findViewById(R.id.textTitle);
            convertView.setTag(holder);
        } else {
            holder = (TextViewHolder) convertView.getTag();
        }
        holder.title.setText(item);
        return convertView;
    }

    public int getRealChildrenCount(int groupPosition) {
        return ((GroupItem) this.items.get(groupPosition)).items.size();
    }

    public GroupItem getGroup(int groupPosition) {
        return (GroupItem) this.items.get(groupPosition);
    }

    public int getGroupCount() {
        return this.items.size();
    }

    public long getGroupId(int groupPosition) {
        return (long) groupPosition;
    }

    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        TextViewHolder holder;
        GroupItem item = getGroup(groupPosition);
        if (convertView == null) {
            holder = new TextViewHolder();
            convertView = this.inflater.inflate(R.layout.nav_drawer_list_group, parent, false);
            holder.title = (TextView) convertView.findViewById(R.id.textTitle);
            convertView.setTag(holder);
        } else {
            holder = (TextViewHolder) convertView.getTag();
        }
        holder.title.setText(item.title);
        return convertView;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int arg0, int arg1) {
        return true;
    }
}