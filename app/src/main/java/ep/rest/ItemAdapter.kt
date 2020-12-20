package ep.rest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import java.util.*


class ItemAdapter(context: Context) : ArrayAdapter<Item>(context, 0, ArrayList()) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Check if an existing view is being reused, otherwise inflate the view
        val view = if (convertView == null)
            LayoutInflater.from(context).inflate(R.layout.itemlist_element, parent, false)
        else
            convertView

        val tvTitle = view.findViewById<TextView>(R.id.tvName)
        val tvPrice = view.findViewById<TextView>(R.id.tvPrice)

        val item = getItem(position)

        tvTitle.text = item?.NAZIV_ARTIKEL
        tvPrice.text = String.format(Locale.ENGLISH, "%.2f EUR", item?.CENA)

        return view
    }
}
