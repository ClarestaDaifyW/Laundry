package com.sagara.laundry.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sagara.laundry.R
import com.sagara.laundry.modeldata.ModelPelanggan

class adapter_pilih_pelanggan(
    private val pelangganList: ArrayList<ModelPelanggan>,
    private val onItemClick: (ModelPelanggan) -> Unit
) : RecyclerView.Adapter<adapter_pilih_pelanggan.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_pilih_pelanggan, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pelanggan = pelangganList[position]

        // Menampilkan data ke TextView
        holder.tvID.text = pelanggan.tvCARD_PELANGGAN_ID ?: ""
        holder.tvNama.text = pelanggan.tvCARD_PELANGGAN_Nama ?: ""
        holder.tvAlamat.text = "Alamat: ${pelanggan.tvCARD_PELANGGAN_Almt ?: "-"}"
        holder.tvNoHP.text = "No HP: ${pelanggan.tvCARD_PELANGGAN_NoHP ?: "-"}"

        // Saat item diklik, panggil lambda
        holder.cvCard.setOnClickListener {
            onItemClick(pelanggan)
        }
    }

    override fun getItemCount(): Int = pelangganList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvID: TextView = itemView.findViewById(R.id.TvCard_ID_PP)
        val tvNama: TextView = itemView.findViewById(R.id.TvCard_NamaP_Pelanggan)
        val tvAlamat: TextView = itemView.findViewById(R.id.TvCard_AlamatP_Pelanggan)
        val tvNoHP: TextView = itemView.findViewById(R.id.TvCard_NoHP_PP)
        val cvCard: View = itemView.findViewById(R.id.Cv_PP)
    }
}
