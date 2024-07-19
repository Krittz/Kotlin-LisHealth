package com.cran.lis_health.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.cran.lis_health.R
import com.cran.lis_health.model.Register

class RegisterAdapter(private var mList: List<Register>) :
    RecyclerView.Adapter<RegisterAdapter.RegisterViewHolder>() {


    inner class RegisterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: AppCompatTextView = itemView.findViewById(R.id.registerTitle)
        val description: AppCompatTextView = itemView.findViewById(R.id.registerDescription)
        val constraintLayout: ConstraintLayout = itemView.findViewById(R.id.constraintLayout)
        val btnExpand: AppCompatButton = itemView.findViewById(R.id.btnExpand)

        fun collapseExpandedView() {
            description.visibility = View.GONE
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegisterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.register_item, parent, false)
        return RegisterViewHolder(view)
    }

    override fun onBindViewHolder(holder: RegisterViewHolder, position: Int) {
        val registerData = mList[position]
        holder.title.text = registerData.title.toString()
        holder.description.text = registerData.description.toString()

        val isExpandable: Boolean = registerData.isExpandable
        holder.description.visibility = if (isExpandable) View.VISIBLE else View.GONE
        holder.btnExpand.setBackgroundResource(if (isExpandable) R.drawable.circle_up else R.drawable.circle_down)
        holder.btnExpand.setOnClickListener {
            isAnyItemExpanded(position)
            registerData.isExpandable = !registerData.isExpandable
            notifyItemChanged(position, Unit)
        }
    }

    private fun isAnyItemExpanded(position: Int) {
        val temp = mList.indexOfFirst {
            it.isExpandable
        }
        if (temp >= 0 && temp != position) {
            mList[temp].isExpandable = false
            notifyItemChanged(temp, 0)
        }
    }

    override fun onBindViewHolder(
        holder: RegisterViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNotEmpty() && payloads[0] == 0) {
            holder.collapseExpandedView()
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}