package com.sagara.laundry.transaksi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sagara.laundry.R
import com.sagara.laundry.adapter.adapter_tambah_pilihan
import com.sagara.laundry.modeldata.ModelTambah
import java.text.NumberFormat
import java.util.*

class Transaksi : AppCompatActivity() {

    private lateinit var btnPilihPelanggan: Button
    private lateinit var btnPilihLayanan: Button
    private lateinit var btnTambahan: Button

    private lateinit var tvIsi1: TextView
    private lateinit var tvIsi2: TextView
    private lateinit var tvIsi3: TextView
    private lateinit var tvIsi4: TextView

    private lateinit var rvTambahan: RecyclerView
    private lateinit var adapterTambahan: adapter_tambah_pilihan
    private val listTambahan = mutableListOf<ModelTambah>()

    companion object {
        const val REQUEST_PILIH_PELANGGAN = 1
        const val REQUEST_PILIH_LAYANAN = 2
        const val REQUEST_PILIH_TAMBAHAN = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_transaksi)

        // Inisialisasi tombol dan teks
        btnPilihPelanggan = findViewById(R.id.btnPilihPelanggan)
        btnPilihLayanan = findViewById(R.id.btnPilihLayanan)
        btnTambahan = findViewById(R.id.btnTambahan)

        tvIsi1 = findViewById(R.id.TvIsi1)
        tvIsi2 = findViewById(R.id.TvIsi2)
        tvIsi3 = findViewById(R.id.TvIsi3)
        tvIsi4 = findViewById(R.id.TvIsi4)

        // Inisialisasi RecyclerView
        rvTambahan = findViewById(R.id.rvItem_Tambahan)
        rvTambahan.layoutManager = LinearLayoutManager(this)

        adapterTambahan = adapter_tambah_pilihan(listTambahan) { item ->
            Toast.makeText(this, "Dihapus: ${item.nama_tambahan}", Toast.LENGTH_SHORT).show()
        }
        rvTambahan.adapter = adapterTambahan

        // Tombol pilih
        btnPilihPelanggan.setOnClickListener {
            val intent = Intent(this, activity_pilih_pelanggan::class.java)
            startActivityForResult(intent, REQUEST_PILIH_PELANGGAN)
        }

        btnPilihLayanan.setOnClickListener {
            val intent = Intent(this, activity_pilih_layanan::class.java)
            startActivityForResult(intent, REQUEST_PILIH_LAYANAN)
        }

        btnTambahan.setOnClickListener {
            val intent = Intent(this, activity_pilih_tambahan::class.java)
            startActivityForResult(intent, REQUEST_PILIH_TAMBAHAN)
        }

        // Padding sistem
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && data != null) {
            when (requestCode) {
                REQUEST_PILIH_PELANGGAN -> {
                    val nama = data.getStringExtra("NAMA") ?: "-"
                    val noHp = data.getStringExtra("NOHP") ?: "-"
                    tvIsi1.text = "Nama Pelanggan: $nama"
                    tvIsi2.text = "No HP: $noHp"
                }

                REQUEST_PILIH_LAYANAN -> {
                    val namaLayanan = data.getStringExtra("NAMA_LAYANAN") ?: "-"
                    val harga = data.getStringExtra("HARGA") ?: "-"
                    val hargaFormatted = formatHarga(harga)
                    tvIsi3.text = "Nama Layanan: $namaLayanan"
                    tvIsi4.text = "Harga: $hargaFormatted"
                }

                REQUEST_PILIH_TAMBAHAN -> {
                    val id = data.getStringExtra("idTambahan") ?: "-"
                    val nama = data.getStringExtra("nama") ?: "-"
                    val harga = data.getStringExtra("harga") ?: "-"

                    // Cek duplikasi berdasarkan ID
                    val sudahAda = listTambahan.any { it.id_tambahan == id }
                    if (sudahAda) {
                        Toast.makeText(this, "Layanan tambahan sudah dipilih", Toast.LENGTH_SHORT).show()
                        return
                    }

                    val tambahan = ModelTambah(id, nama, harga, "")
                    adapterTambahan.addItem(tambahan)
                }
            }
        }
    }

    private fun formatHarga(harga: String): String {
        return try {
            val angkaBersih = harga.replace(Regex("[^\\d]"), "")
            val hargaInt = angkaBersih.toInt()
            NumberFormat.getCurrencyInstance(Locale("in", "ID")).format(hargaInt)
        } catch (e: Exception) {
            e.printStackTrace()
            harga
        }
    }
}
