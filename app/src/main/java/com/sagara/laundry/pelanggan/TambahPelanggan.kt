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

class TambahPelanggan : AppCompatActivity() {

    private val database = FirebaseDatabase.getInstance()
    private val myRef = database.getReference("pelanggan")

    private lateinit var tvTambahPelanggan: TextView
    private lateinit var etNamaLengkap: EditText
    private lateinit var etAlamat: EditText
    private lateinit var etNoHP: EditText
    private lateinit var etNamaCabang: EditText
    private lateinit var btnSimpan: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_pelanggan)
        initViews()

        btnSimpan.setOnClickListener {
            cekValidasi()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initViews() {
        tvTambahPelanggan = findViewById(R.id.tv1_TambahPelanggan)
        etNamaLengkap = findViewById(R.id.ET1_NamaLengkap)
        etAlamat = findViewById(R.id.ET2_Alamat)
        etNoHP = findViewById(R.id.ET3_NoHP)
        etNamaCabang = findViewById(R.id.ET4_NamaCabang)
        btnSimpan = findViewById(R.id.btn_Simpan)
    }

    private fun cekValidasi() {
        val nama = etNamaLengkap.text.toString()
        val alamat = etAlamat.text.toString()
        val noHP = etNoHP.text.toString()
        val cabang = etNamaCabang.text.toString()

        if (nama.isEmpty()) {
            etNamaLengkap.error = getString(R.string.validasi_nama_pelanggan)
            Toast.makeText(this, getString(R.string.validasi_nama_pelanggan), Toast.LENGTH_SHORT).show()
            etNamaLengkap.requestFocus()
            return
        }
        if (alamat.isEmpty()) {
            etAlamat.error = getString(R.string.validasi_alamat_pelanggan)
            Toast.makeText(this, getString(R.string.validasi_alamat_pelanggan), Toast.LENGTH_SHORT).show()
            etAlamat.requestFocus()
            return
        }
        if (noHP.isEmpty()) {
            etNoHP.error = getString(R.string.validasi_noHP_pelanggan)
            Toast.makeText(this, getString(R.string.validasi_noHP_pelanggan), Toast.LENGTH_SHORT).show()
            etNoHP.requestFocus()
            return
        }
        if (cabang.isEmpty()) {
            etNamaCabang.error = getString(R.string.validasi_Cabang_pelanggan)
            Toast.makeText(this, getString(R.string.validasi_Cabang_pelanggan), Toast.LENGTH_SHORT).show()
            etNamaCabang.requestFocus()
            return
        }
        simpan()
    }

    private fun simpan() {
        val pelangganBaru = myRef.push()
        val pelangganId = pelangganBaru.key ?: ""
        val data = ModelPelanggan(
            pelangganId,
            etNamaLengkap.text.toString(),
            etAlamat.text.toString(),
            etNoHP.text.toString(),
            etNamaCabang.text.toString()
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
