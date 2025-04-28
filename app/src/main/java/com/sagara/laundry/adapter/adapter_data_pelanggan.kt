package com.sagara.laundry.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sagara.laundry.R
import com.sagara.laundry.modeldata.ModelPelanggan


class adapter_data_pelanggan(private val listPelanggan: ArrayList<ModelPelanggan>) :
    RecyclerView.Adapter<adapter_data_pelanggan.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_pelanggan1, parent, false) // Ensure this is your item layout
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listPelanggan[position]
        holder.tvID.text = item.tvCARD_PELANGGAN_ID
        holder.tvNama.text = item.tvCARD_PELANGGAN_Nama
        holder.tvDaftar.text = item.tvCARD_PELANGGAN_daftar
        holder.tvAlamat.text = item.tvCARD_PELANGGAN_Almt
        holder.tvNoHP.text = item.tvCARD_PELANGGAN_NoHP
        holder.tvCabang.text = item.tvCARD_PELANGGAN_Cabang

        holder.btnHubungi.setOnClickListener {
            // Handle button click (e.g., open phone dialer)
        }

        holder.btnLihat.setOnClickListener {
            // Handle view details action
        }
    }

    override fun getItemCount(): Int {
        return listPelanggan.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvID: TextView = itemView.findViewById(R.id.tvCARD_PELANGGAN_ID)
        val tvDaftar: TextView = itemView.findViewById(R.id.tvCARD_PELANGGAN_daftar)
        val tvNama: TextView = itemView.findViewById(R.id.tvCARD_PELANGGAN_Nama)
        val tvAlamat: TextView = itemView.findViewById(R.id.tvCARD_PELANGGAN_Almt)
        val tvNoHP: TextView = itemView.findViewById(R.id.tvCARD_PELANGGAN_NoHP)
        val tvCabang: TextView = itemView.findViewById(R.id.tvCARD_PELANGGAN_cabang)
        val btnHubungi: Button = itemView.findViewById(R.id.btn_HUBUNGI)
        val btnLihat: Button = itemView.findViewById(R.id.btn_LIHAT)


    }
}