package com.cahayaaya.laundry.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.cahayaaya.laundry.modeldata.ModelPegawai
import com.cahayaaya.laundry.R
import com.cahayaaya.laundry.modeldata.ModelLayanan

class DataLayananAdapter(
    private val listlayanan: ArrayList<ModelLayanan>
) : RecyclerView.Adapter<DataLayananAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_data_layanan, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listlayanan[position]
        holder.tvID.text = item.tvCard_Layanan_Id
        holder.tvNama.text = item.tvCard_Pelanggan_Layanan
        holder.tvHarga.text = item.tvCard_Pelanggan_Harga
        holder.tvcabang.text = item.tvCard_Pelanggan_NamaCabang


    }

    override fun getItemCount(): Int {
        return listlayanan.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvID: TextView = itemView.findViewById(R.id.tvCard_Layanan_Id)
        val tvNama: TextView = itemView.findViewById(R.id.tvCard_Layanan_Nama)
        val tvHarga: TextView = itemView.findViewById(R.id.tvCard_Layanan_Harga)
        val tvcabang: TextView = itemView.findViewById(R.id.tvCard_Layanan_NamaCabang)

    }
}