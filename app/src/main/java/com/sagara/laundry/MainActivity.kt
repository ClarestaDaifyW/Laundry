package com.android.laundry

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.sagara.laundry.R
import com.sagara.laundry.pegawai.DataPegawai
import com.sagara.laundry.pelanggan.DataPelanggan
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var pelanggan : ImageView
    lateinit var pegawai : CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Pastikan file XML sesuai

        pelanggan = findViewById(R.id.IV_Pelanggan)
        pegawai = findViewById(R.id.Cv5)

        pelanggan.setOnClickListener {
            val intent = Intent(this@MainActivity, DataPelanggan:: class.java)
            startActivity(intent)
        }

        pegawai.setOnClickListener {
            val intent = Intent( this, DataPegawai:: class.java)
            startActivity(intent)
        }


        // Inisialisasi TextView
        val sapaTextView: TextView = findViewById(R.id.sapa)
        val tanggalTextView: TextView = findViewById(R.id.tanggal)

        // Menampilkan pesan selamat berdasarkan waktu
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val greeting = when (hour) {
            in 5..10 -> "Selamat Pagi, Claresta"
            in 11..14 -> "Selamat Siang, Claresta"
            in 15..17 -> "Selamat Sore, Claresta"
            else -> "Selamat Malam, Claresta"
        }
        sapaTextView.text = greeting

        // Menampilkan tanggal saat ini
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        tanggalTextView.text = dateFormat.format(Date())

        // Inisialisasi CardView dan navigasi ke aktivitas lain
        findViewById<CardView>(R.id.Cv2).setOnClickListener {
            startActivity(Intent(this, DataPelanggan::class.java))

        }
    }
}
