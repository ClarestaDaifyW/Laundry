package com.sagara.laundry.transaksi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sagara.laundry.R

class Transaksi : AppCompatActivity() {

    private lateinit var btnPilihPelanggan: Button
    private lateinit var btnPilihLayanan: Button

    private lateinit var tvIsi1: TextView
    private lateinit var tvIsi2: TextView
    private lateinit var tvIsi3: TextView
    private lateinit var tvIsi4: TextView

    companion object {
        const val REQUEST_PILIH_PELANGGAN = 1
        const val REQUEST_PILIH_LAYANAN = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_transaksi)

        // Inisialisasi view
        btnPilihPelanggan = findViewById(R.id.btnPilihPelanggan)
        btnPilihLayanan = findViewById(R.id.btnPilihLayanan)

        tvIsi1 = findViewById(R.id.TvIsi1)
        tvIsi2 = findViewById(R.id.TvIsi2)
        tvIsi3 = findViewById(R.id.TvIsi3)
        tvIsi4 = findViewById(R.id.TvIsi4)

        // Pilih pelanggan
        btnPilihPelanggan.setOnClickListener {
            val intent = Intent(this, activity_pilih_pelanggan::class.java)
            startActivityForResult(intent, REQUEST_PILIH_PELANGGAN)
        }

        // Pilih layanan
        btnPilihLayanan.setOnClickListener {
            val intent = Intent(this, activity_pilih_layanan::class.java)
            startActivityForResult(intent, REQUEST_PILIH_LAYANAN)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_PILIH_PELANGGAN -> {
                    val nama = data?.getStringExtra("NAMA")
                    val noHp = data?.getStringExtra("NOHP")
                    tvIsi1.text = "Nama Pelanggan: $nama"
                    tvIsi2.text = "No HP: $noHp"
                }
                REQUEST_PILIH_LAYANAN -> {
                    val namaLayanan = data?.getStringExtra("NAMA_LAYANAN")
                    val harga = data?.getStringExtra("HARGA")
                    tvIsi3.text = "Nama Layanan: $namaLayanan"
                    tvIsi4.text = "Harga: $harga"
                }
            }
        }
    }

}
