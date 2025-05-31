package com.sagara.laundry.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sagara.laundry.R
import com.sagara.laundry.modeldata.ModelLayanan

class adapter_pilih_layanan(
    private var layananList: List<ModelLayanan>,
    private val onItemClick: (ModelLayanan) -> Unit
) : RecyclerView.Adapter<adapter_pilih_layanan.LayananViewHolder>() {

    inner class LayananViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvIdUrut: TextView = itemView.findViewById(R.id.TvCard_IdUrut)
        private val tvNama: TextView = itemView.findViewById(R.id.TvCard_NamaP_Layanan)
        private val tvHarga: TextView = itemView.findViewById(R.id.TvCard_Harga_PL)

        fun bind(layanan: ModelLayanan, urutan: Int) {
            tvIdUrut.text = "[$urutan]"
            tvNama.text = layanan.tvCard_NamaLayanan ?: "-"
            tvHarga.text = "Harga: Rp${layanan.tvCard_Harga ?: "0"}"

            itemView.setOnClickListener { onItemClick(layanan) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LayananViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_pilih_layanan, parent, false)
        return LayananViewHolder(view)
    }

    override fun onBindViewHolder(holder: LayananViewHolder, position: Int) {
        val urutan = itemCount - position // untuk urutan menurun (5, 4, 3,...)
        holder.bind(layananList[position], urutan)
    }

    override fun getItemCount(): Int = layananList.size

    fun updateList(newList: List<ModelLayanan>) {
        layananList = newList
        notifyDataSetChanged()
    }
}
