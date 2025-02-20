package com.sagara.laundry.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sagara.laundry.R
import com.sagara.laundry.modeldata.ModelPegawai

class adapter_data_pegawai(private val listPegawain: ArrayList<ModelPegawai>) :
    RecyclerView.Adapter<adapter_data_pegawai.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_data_pegawai1, parent, false) // Ensure this is your item layout
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listPegawain[position]
        holder.tvID.text = item.tvCARD_PEGAWAI_ID
        holder.tvNama.text = item.tvCARD_PEGAWAI_Nama
        holder.tvDaftar.text = item.tvCARD_PEGAWAI_daftar
        holder.tvAlamat.text = item.tvCARD_PEGAWAI_Almt
        holder.tvNoHP.text = item.tvCARD_PEGAWAI_NoHP

        holder.btn_HUBUNGI_pegawai.setOnClickListener {
            // Handle button click (e.g., open phone dialer)
        }

        holder.btn_LIHAT_pegawai.setOnClickListener {
            // Handle view details action
        }
    }

    override fun getItemCount(): Int {
        return listPegawain.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvID: TextView = itemView.findViewById(R.id.tvCARD_PEGAWAI_ID)
        val tvDaftar: TextView = itemView.findViewById(R.id.tvCARD_PEGAWAI_daftar)
        val tvNama: TextView = itemView.findViewById(R.id.tvCARD_PEGAWAI_Nama)
        val tvAlamat: TextView = itemView.findViewById(R.id.tvCARD_PEGAWAI_Almt)
        val tvNoHP: TextView = itemView.findViewById(R.id.tvCARD_PEGAWAI_NoHP)
        val btn_HUBUNGI_pegawai: Button = itemView.findViewById(R.id.btn_HUBUNGI_pegawai)
        val btn_LIHAT_pegawai: Button = itemView.findViewById(R.id.btn_LIHAT_pegawai)


    }
}