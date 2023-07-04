package com.hxt5gh.android.notesapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hxt5gh.android.notesapp.database.Notes
import com.hxt5gh.android.notesapp.database.newNotes

class Adapter(private val context: Context ,private val dataList: List<newNotes>)  : RecyclerView.Adapter<Adapter.viewholder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
         val  view = LayoutInflater.from(parent.context).inflate(R.layout.item_view , parent , false)
        return viewholder(view)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val data = dataList[position]
        holder.bind(data)
        holder.itemView.setOnClickListener(){
            val intent = Intent(context , UpdateActivity::class.java)
            intent.putExtra("title" , data.title )
            intent.putExtra("note" , data.content )
            intent.putExtra("priority" , data.priority )
            intent.putExtra("date" , data.date)
            intent.putExtra("id" , data.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class viewholder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var tiele = itemView.findViewById<TextView>(R.id.title)
        var note = itemView.findViewById<TextView>(R.id.note)
        var time = itemView.findViewById<TextView>(R.id.time)
        var priorit = itemView.findViewById<View>(R.id.priority)

        fun bind(data: newNotes) {

            tiele.text = data.title
            note.text = data.content
            time.text = data.date.toString()
            if (data.priority.equals("1")){
                priorit.setBackgroundResource(R.drawable.priority_green)
            }
            if (data.priority.equals("2")){
                priorit.setBackgroundResource(R.drawable.priority_yello)
            }
            if (data.priority.equals("3")){
                priorit.setBackgroundResource(R.drawable.priority_red)
            }
        }

    }
}