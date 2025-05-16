package com.sagara.laundry.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sagara.laundry.R
import com.sagara.laundry.modeldata.ModelLayanan

class adapter_pilih_layanan(
    private val layananList: List<ModelLayanan>,
    private val onItemClick: (ModelLayanan) -> Unit
) : RecyclerView.Adapter<adapter_pilih_layanan.LayananViewHolder>() {

    inner class LayananViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNama: TextView = itemView.findViewById(R.id.TvCard_NamaP_Layanan)
        val tvHarga: TextView = itemView.findViewById(R.id.TvCard_AlamatP_Layanan)

        init {
            itemView.setOnClickListener {
                val posisi = adapterPosition
                if (posisi != RecyclerView.NO_POSITION) {
                    onItemClick(layananList[posisi])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LayananViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_pilih_layanan, parent, false)
        return LayananViewHolder(view)
    }

    override fun onBindViewHolder(holder: LayananViewHolder, position: Int) {
        val item = layananList[position]
        holder.tvNama.text = item.tvCard_NamaLayanan
        holder.tvHarga.text = "Harga: Rp${item.tvCard_Harga}"
    }

    override fun getItemCount(): Int = layananList.size
}
