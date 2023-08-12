package id.izazdhiya.disasterapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import id.izazdhiya.disasterapp.databinding.ItemAreaBinding


class DisasterAreaAdapter(
    private val disasterAreas: ArrayList<id.izazdhiya.disasterapp.data.source.DisasterArea>,
    private val navController: NavController,
    private val onClickListener: (id: String, type: id.izazdhiya.disasterapp.data.source.DisasterArea) -> Unit) : RecyclerView.Adapter<DisasterAreaAdapter.DisasterAreaViewHolder>() {

    var listDisaster = disasterAreas

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisasterAreaViewHolder {
        val binding = ItemAreaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DisasterAreaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DisasterAreaViewHolder, position: Int) {
        holder.bind(listDisaster[position])
    }

    override fun getItemCount(): Int = listDisaster.size

    inner class DisasterAreaViewHolder(private val binding: ItemAreaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: id.izazdhiya.disasterapp.data.source.DisasterArea) {
            binding.apply {
                tvArea.text = item.name
                itemArea.setOnClickListener {
                    onClickListener.invoke(item.id, item)
                }
            }
        }
    }

    fun filter(searchText: String?) {
        val filteredDisasterAreas: ArrayList<id.izazdhiya.disasterapp.data.source.DisasterArea> = if (!searchText.isNullOrBlank()) {
            disasterAreas.filter { disasterArea ->
                disasterArea.name.contains(searchText, ignoreCase = true)
            }.toCollection(ArrayList())
        } else {
            disasterAreas
        }

        listDisaster.clear()
        listDisaster.addAll(filteredDisasterAreas)
        notifyDataSetChanged()
    }
}