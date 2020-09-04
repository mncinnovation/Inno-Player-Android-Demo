[app](../../index.md) / [id.innovationcenter.lidoplayerandroidsdk.listchoice](../index.md) / [ChoiceExpandableListAdapter](./index.md)

# ChoiceExpandableListAdapter

`class ChoiceExpandableListAdapter : `[`BaseExpandableListAdapter`](https://developer.android.com/reference/android/widget/BaseExpandableListAdapter.html)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ChoiceExpandableListAdapter(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`, listHeader: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>, listDataChild: `[`HashMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-hash-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<PlaylistItem>>>)` |

### Functions

| Name | Summary |
|---|---|
| [getChild](get-child.md) | `fun getChild(p0: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, p1: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?` |
| [getChildId](get-child-id.md) | `fun getChildId(p0: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, p1: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [getChildView](get-child-view.md) | `fun getChildView(p0: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, p1: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, p2: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, p3: `[`View`](https://developer.android.com/reference/android/view/View.html)`?, p4: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`?): `[`View`](https://developer.android.com/reference/android/view/View.html)`?` |
| [getChildrenCount](get-children-count.md) | `fun getChildrenCount(p0: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getGroup](get-group.md) | `fun getGroup(p0: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html) |
| [getGroupCount](get-group-count.md) | `fun getGroupCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getGroupId](get-group-id.md) | `fun getGroupId(p0: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [getGroupView](get-group-view.md) | `fun getGroupView(p0: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, p1: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, p2: `[`View`](https://developer.android.com/reference/android/view/View.html)`?, p3: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`?): `[`View`](https://developer.android.com/reference/android/view/View.html)`?` |
| [hasStableIds](has-stable-ids.md) | `fun hasStableIds(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isChildSelectable](is-child-selectable.md) | `fun isChildSelectable(p0: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, p1: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
