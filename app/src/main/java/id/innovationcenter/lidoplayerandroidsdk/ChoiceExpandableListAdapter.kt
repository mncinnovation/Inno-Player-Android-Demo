package id.innovationcenter.lidoplayerandroidsdk

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import co.innoplayer.media.playlists.PlaylistItem

class ChoiceExpandableListAdapter(
    context: Context,
    private val listHeader: List<String>,
    private val listDataChild: HashMap<String, List<List<PlaylistItem>>>
) : BaseExpandableListAdapter() {
    private val inflater = LayoutInflater.from(context)

    override fun getGroup(p0: Int): Any {
        return listHeader[p0]
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(p0: Int, p1: Boolean, p2: View?, p3: ViewGroup?): View? {
        val headerTitle = getGroup(p0) as String
        var viewGroup = inflater.inflate(android.R.layout.simple_expandable_list_item_1, p3, false)
        val tvHeader = viewGroup as TextView?
        tvHeader?.text = headerTitle
        return viewGroup
    }

    override fun getChildrenCount(p0: Int): Int {
        return listDataChild[listHeader[p0]]?.size ?: 0
    }

    override fun getChild(p0: Int, p1: Int): Any? {
        return listDataChild[listHeader[p0]]?.get(p1)
    }

    override fun getGroupId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getChildView(p0: Int, p1: Int, p2: Boolean, p3: View?, p4: ViewGroup?): View? {
        val items = getChild(p0, p1) as List<PlaylistItem>
        val viewGroup = inflater.inflate(R.layout.adapter_choice_item, p4, false)
        val tvHeader = viewGroup.findViewById(R.id.tvItem) as TextView?
        var itemChild = ""
        for ((i, item) in items.withIndex()) {
            itemChild += item.title
            if (i != items.size - 1) {
                itemChild += ", "
            }

        }

        tvHeader?.text = itemChild
        return viewGroup
    }

    override fun getChildId(p0: Int, p1: Int): Long {
        return p1.toLong()
    }

    override fun getGroupCount(): Int {
        return listHeader.size
    }
}