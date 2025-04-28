package com.sagara.laundry.pelanggan

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase
import com.sagara.laundry.R
import com.sagara.laundry.modeldata.ModelPelanggan
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TambahPelanggan : AppCompatActivity() {

    private val database = FirebaseDatabase.getInstance()
    private val myRef = database.getReference("pelanggan")

    private lateinit var tv1_TambahPelanggan: TextView
    private lateinit var ET1_NamaLengkap: EditText
    private lateinit var ET2_Alamat: EditText
    private lateinit var ET3_NoHP: EditText
    private lateinit var ET4_NamaCabang: EditText
    private lateinit var btn_Simpan: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_pelanggan)
        initViews()

        btn_Simpan.setOnClickListener {
            cekValidasi()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initViews() {
        tv1_TambahPelanggan = findViewById(R.id.tv1_TambahPelanggan)
        ET1_NamaLengkap = findViewById(R.id.ET1_NamaLengkap)
        ET2_Alamat = findViewById(R.id.ET2_Alamat)
        ET3_NoHP = findViewById(R.id.ET3_NoHP)
        ET4_NamaCabang = findViewById(R.id.ET4_NamaCabang)
        btn_Simpan = findViewById(R.id.btn_Simpan)
    }

    private fun cekValidasi() {
        val nama = ET1_NamaLengkap.text.toString()
        val alamat = ET2_Alamat.text.toString()
        val noHP = ET3_NoHP.text.toString()
        val cabang = ET4_NamaCabang.text.toString()

        if (nama.isEmpty()) {
            ET1_NamaLengkap.error = getString(R.string.validasi_nama_pelanggan)
            Toast.makeText(this, getString(R.string.validasi_nama_pelanggan), Toast.LENGTH_SHORT).show()
            ET1_NamaLengkap.requestFocus()
            return
        }
        if (alamat.isEmpty()) {
            ET2_Alamat.error = getString(R.string.validasi_alamat_pelanggan)
            Toast.makeText(this, getString(R.string.validasi_alamat_pelanggan), Toast.LENGTH_SHORT).show()
            ET2_Alamat.requestFocus()
            return
        }
        if (noHP.isEmpty()) {
            ET3_NoHP.error = getString(R.string.validasi_noHP_pelanggan)
            Toast.makeText(this, getString(R.string.validasi_noHP_pelanggan), Toast.LENGTH_SHORT).show()
            ET3_NoHP.requestFocus()
            return
        }
        if (cabang.isEmpty()) {
            ET4_NamaCabang.error = getString(R.string.validasi_Cabang_pelanggan)
            Toast.makeText(this, getString(R.string.validasi_Cabang_pelanggan), Toast.LENGTH_SHORT).show()
            ET4_NamaCabang.requestFocus()
            return
        }
        simpan()
    }

    private fun simpan() {
        val pelangganBaru = myRef.push()
        val pelangganId = pelangganBaru.key ?: ""
        val currentTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val data = ModelPelanggan(
            pelangganId,
            ET1_NamaLengkap.text.toString(),
            ET2_Alamat.text.toString(),
            ET3_NoHP.text.toString(),
            ET4_NamaCabang.text.toString(),
            currentTime
        )
        pelangganBaru.setValue(data)
            .addOnSuccessListener {
                Toast.makeText(this, getString(R.string.sukses_simpan_pelanggan), Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, getString(R.string.gagal_simpan_pelanggan), Toast.LENGTH_SHORT).show()
            }
    }
}
