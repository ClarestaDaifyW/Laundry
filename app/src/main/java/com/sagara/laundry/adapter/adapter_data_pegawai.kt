package com.sagara.laundry.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.sagara.laundry.R
import com.sagara.laundry.modeldata.ModelPegawai
import com.sagara.laundry.pegawai.TambahPegawai

class adapter_data_pegawai(
    private val appContext: Context,
    private val listPegawai: ArrayList<ModelPegawai>
) : RecyclerView.Adapter<adapter_data_pegawai.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_data_pegawai1, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listPegawai[position]
        holder.tvID.text = item.idPegawai
        holder.tvNama.text = item.namaPegawai
        holder.tvDaftar.text = item.terdaftar
        holder.tvAlamat.text = item.alamatPegawai
        holder.tvNoHP.text = item.noHpPegawai
        holder.tvCabang.text = item.idCabangPegawai

        holder.btn_HUBUNGI_pegawai.setOnClickListener {
            // TODO: Tambahkan fungsi dial nomor hp di sini
        }

        holder.btn_LIHAT_pegawai.setOnClickListener {
            // TODO: Tambahkan fungsi untuk lihat detail pegawai di sini
        }

        holder.cvCard.setOnClickListener {
            val intent = Intent(appContext, TambahPegawai::class.java)
            intent.putExtra("judul", "Edit Pegawai")
            intent.putExtra("idPegawai", item.idPegawai)
            intent.putExtra("namaPegawai", item.namaPegawai)
            intent.putExtra("noHpPegawai", item.noHpPegawai)
            intent.putExtra("alamatPegawai", item.alamatPegawai)
            intent.putExtra("idCabangPegawai", item.idCabangPegawai)
            appContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listPegawai.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvCard: CardView = itemView.findViewById(R.id.CardPegawai)
        val tvID: TextView = itemView.findViewById(R.id.tvCARD_PEGAWAI_ID)
        val tvDaftar: TextView = itemView.findViewById(R.id.tvCARD_PEGAWAI_daftar)
        val tvNama: TextView = itemView.findViewById(R.id.tvCARD_PEGAWAI_Nama)
        val tvAlamat: TextView = itemView.findViewById(R.id.tvCARD_PEGAWAI_Almt)
        val tvNoHP: TextView = itemView.findViewById(R.id.tvCARD_PEGAWAI_NoHP)
        val tvCabang: TextView = itemView.findViewById(R.id.tvCARD_PEGAWAI_cabang)
        val btn_HUBUNGI_pegawai: Button = itemView.findViewById(R.id.btn_HUBUNGI_pegawai)
        val btn_LIHAT_pegawai: Button = itemView.findViewById(R.id.btn_LIHAT_pegawai)
    }
}
