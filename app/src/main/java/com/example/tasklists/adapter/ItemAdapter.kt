package com.example.tasklists.adapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tasklists.R
import com.example.tasklists.model.ToDo

 class ItemAdapter(
    private val typeList: MutableList<ToDo>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

     // Provide a reference to the views for each data item
     // Complex data items may need more than one view per item, and
     // you provide access to all the views for a data item in a view holder.
     // Each data item is just an To-Do object.


     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
         // create a new view
         val context = parent.context
         val inflater = LayoutInflater.from(context)
         val adapterLayout = inflater.inflate(R.layout.display_view, parent, false)

         return ItemViewHolder(adapterLayout)
     }
     override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {  //onBindViewHolder used to piece together data items and view holders

         val items = typeList.get(position)//this VAL helps to find the right TO-DO object from the dataset based on position/order
         holder.textView.setText(items.myData) //must create a holder, which serves as a wrapper for the views and return back To-Do (in its class) item that I named myData
         Log.i("ItemAdapter", "Prints to do")

     }

     override fun getItemCount(): Int { return typeList.size //get back the actual count/size of the dataset.
     }

     inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

         val textView: TextView = view.findViewById(R.id.list_item)
         //   val displaysCheck: CheckBox = view.findViewById(R.id.check_mark)
         //val imageView: ImageView = view.findViewById(R.id.night_sky)


     }

     }


