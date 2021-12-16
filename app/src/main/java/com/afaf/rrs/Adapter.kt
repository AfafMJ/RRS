package com.afaf.rrs


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afaf.rrs.databinding.QuestionsRowBinding

class Adapter(private var questions: ArrayList<Question>):
    RecyclerView.Adapter<Adapter.ItemViewHolder>() {
    class ItemViewHolder(val binding: QuestionsRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(QuestionsRowBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val question = questions[position]
        holder.binding.apply {
            titleTv.text = question.title
            authorTv.text = question.author
            categoriesTv.text = question.categories
        }
    }

    override fun getItemCount() = questions.size

    fun update(questions :ArrayList<Question>){
        this.questions = questions
        notifyDataSetChanged()
    }
}