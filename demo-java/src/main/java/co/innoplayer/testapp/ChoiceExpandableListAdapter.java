package co.innoplayer.testapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import co.innoplayer.media.playlists.PlaylistItem;
import testapp.R;

public class ChoiceExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> listHeader;
    private HashMap<String, List<List<PlaylistItem>>> listDataChild;

    private LayoutInflater inflater;

    ChoiceExpandableListAdapter(Context context,
                                List<String> listHeader, HashMap<String, List<List<PlaylistItem>>> listDataChild) {
        this.context = context;
        this.listHeader = listHeader;
        this.listDataChild = listDataChild;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return listHeader.size();
    }

    @Override
    public int getChildrenCount(int i) {
        List<List<PlaylistItem>> data = listDataChild.get(listHeader.get(i));
        if(data == null){
            return 0;
        }
        return data.size();
    }

    @Override
    public Object getGroup(int i) {
        return listHeader.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        List<List<PlaylistItem>> data = listDataChild.get(listHeader.get(i));
        if(data == null) {
            return null;
        }
        return data.get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String headerTitle = (String) getGroup(i);
        View groupView = inflater.inflate(android.R.layout.simple_expandable_list_item_1, viewGroup, false);
        TextView tvHeader = (TextView) groupView;
        tvHeader.setText(headerTitle);
        return groupView;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        List<PlaylistItem> items = (List<PlaylistItem>) getChild(i,i1);
        View groupView = inflater.inflate(R.layout.adapter_choice_item, viewGroup, false);
        TextView tvHeader = groupView.findViewById(R.id.tvItem);
        StringBuilder itemChild = new StringBuilder();
        for (int j = 0; j < items.size(); j++) {
            itemChild.append(items.get(j).getTitle());
            if(j != items.size() - 1){
                itemChild.append(", ");
            }
        }
        tvHeader.setText(itemChild.toString());
        return groupView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
