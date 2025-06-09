package com.sagara.laundry.transaksi

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sagara.laundry.R
import com.sagara.laundry.adapter.adapter_Konfirmasi_data
import com.sagara.laundry.modeldata.ModelTambah
import java.text.NumberFormat
import java.util.Locale

class KonfirmasiData : AppCompatActivity() {

    private lateinit var tvNama: TextView
    private lateinit var tvNoHp: TextView
    private lateinit var tvNamaLayanan: TextView
    private lateinit var tvHarga: TextView
    private lateinit var tvTotalBayar: TextView
    private lateinit var rvTambahan: RecyclerView
    private lateinit var adapterKonfirmasi: adapter_Konfirmasi_data
    private lateinit var btnProses: Button
    private lateinit var btnBatal: Button

    private val listTambahan = mutableListOf<ModelTambah>()
    private var hargaLayanan = 0
    private var totalTambahan = 0
    private var totalBayar = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konfirmasi_data)

        // Binding komponen
        tvNama = findViewById(R.id.TvNamaPelanggan)
        tvNoHp = findViewById(R.id.TvNomor)
        tvNamaLayanan = findViewById(R.id.TvNamaLayanan)
        tvHarga = findViewById(R.id.TvHarga)
        tvTotalBayar = findViewById(R.id.TvTotalBayar)
        rvTambahan = findViewById(R.id.rv_data_Tambahan)
        btnProses = findViewById(R.id.prosestransaksi)
        btnBatal = findViewById(R.id.btPilihBatal) // ← Tambahkan binding tombol Batal

        // Ambil data dari intent
        val nama = intent.getStringExtra("NAMA_PELANGGAN") ?: "-"
        val noHp = intent.getStringExtra("NOHP") ?: "-"
        val layanan = intent.getStringExtra("NAMA_LAYANAN") ?: "-"
        val hargaStr = intent.getStringExtra("HARGA_LAYANAN") ?: "0"
        val tambahanList = intent.getParcelableArrayListExtra<ModelTambah>("TAMBAHAN_LIST") ?: arrayListOf()

        // Tampilkan data pelanggan & layanan
        tvNama.text = nama
        tvNoHp.text = noHp
        tvNamaLayanan.text = layanan

        // Format harga layanan ke dalam Rupiah
        hargaLayanan = hargaStr.replace("[^\\d]".toRegex(), "").toIntOrNull() ?: 0
        tvHarga.text = formatRupiah(hargaLayanan)

        // Simpan data tambahan
        listTambahan.addAll(tambahanList)

        // Setup RecyclerView tambahan
        adapterKonfirmasi = adapter_Konfirmasi_data(listTambahan)
        rvTambahan.layoutManager = LinearLayoutManager(this)
        rvTambahan.adapter = adapterKonfirmasi

        // Hitung total bayar
        updateTotalBayar()

        // Aksi tombol proses transaksi
        btnProses.setOnClickListener {
            showMetodePembayaranDialog(nama, layanan)
        }

        // Aksi tombol batal (kembali ke halaman sebelumnya)
        btnBatal.setOnClickListener {
            finish() // ← Ini akan menutup KonfirmasiData dan kembali ke Transaksi.kt
        }
    }

    private fun updateTotalBayar() {
        totalTambahan = listTambahan.sumOf {
            it.harga_tambahan?.replace("[^\\d]".toRegex(), "")?.toIntOrNull() ?: 0
        }
        totalBayar = hargaLayanan + totalTambahan
        tvTotalBayar.text = formatRupiah(totalBayar)
    }

    private fun showMetodePembayaranDialog(namaPelanggan: String?, namaLayanan: String?) {
        val inflater = LayoutInflater.from(this)
        val view = inflater.inflate(R.layout.dialog_metode_bayar, null)

        val dialog = AlertDialog.Builder(this)
            .setView(view)
            .setCancelable(true)
            .create()

        view.findViewById<Button>(R.id.btnBayarNanti).setOnClickListener {
            val intent = Intent(this, activity_bayar_nanti::class.java)
            intent.putExtra("idTransaksi", "TRX-${System.currentTimeMillis()}")
            intent.putExtra("tanggal", getTodayDate())
            intent.putExtra("namaPelanggan", namaPelanggan)
            intent.putExtra("namaPegawai", "Admin")
            intent.putExtra("hargaLayanan", hargaLayanan)
            intent.putExtra("namaLayanan", namaLayanan)
            intent.putParcelableArrayListExtra("listTambahan", ArrayList(listTambahan))
            startActivity(intent)
            dialog.dismiss()
        }

        view.findViewById<Button>(R.id.btnTunai).setOnClickListener {
            // TODO: Implementasi pembayaran tunai
        }

        view.findViewById<Button>(R.id.btnQRis).setOnClickListener {
            // TODO: Implementasi QRIS
        }

        view.findViewById<Button>(R.id.btnDana).setOnClickListener {
            // TODO: Implementasi Dana
        }

        view.findViewById<Button>(R.id.btnGoPay).setOnClickListener {
            // TODO: Implementasi GoPay
        }

        view.findViewById<Button>(R.id.btnOvo).setOnClickListener {
            // TODO: Implementasi OVO
        }

        view.findViewById<TextView>(R.id.Batal).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun getTodayDate(): String {
        val formatter = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return formatter.format(java.util.Date())
    }

    private fun formatRupiah(amount: Int): String {
        return NumberFormat.getCurrencyInstance(Locale("in", "ID")).format(amount)
    }
}
