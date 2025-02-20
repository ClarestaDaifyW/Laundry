package com.sagara.laundry.pegawai

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

class TambahPegawai : AppCompatActivity() {
    private val database = FirebaseDatabase.getInstance()
    private val myRef = database.getReference("pegawai")

    private lateinit var tvTambahPegawai: TextView
    private lateinit var etNamaLengkap: EditText
    private lateinit var etAlamat: EditText
    private lateinit var etNoHP: EditText
    private lateinit var etNamaCabang: EditText
    private lateinit var btnSimpan: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_pegawai)

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
        tvTambahPegawai = findViewById(R.id.tv1_TambahPegawai)
        etNamaLengkap = findViewById(R.id.ET1_NamaLengkap_Pegawai)
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
            etNamaLengkap.error = getString(R.string.validasi_nama_pegawai)
            Toast.makeText(this, getString(R.string.validasi_nama_pegawai), Toast.LENGTH_SHORT).show()
            etNamaLengkap.requestFocus()
            return
        }
        if (alamat.isEmpty()) {
            etAlamat.error = getString(R.string.validasi_alamat_pegawai)
            Toast.makeText(this, getString(R.string.validasi_alamat_pegawai), Toast.LENGTH_SHORT).show()
            etAlamat.requestFocus()
            return
        }
        if (noHP.isEmpty()) {
            etNoHP.error = getString(R.string.validasi_noHP_pegawai)
            Toast.makeText(this, getString(R.string.validasi_noHP_pegawai), Toast.LENGTH_SHORT).show()
            etNoHP.requestFocus()
            return
        }
        if (cabang.isEmpty()) {
            etNamaCabang.error = getString(R.string.validasi_Cabang_pegawai)
            Toast.makeText(this, getString(R.string.validasi_Cabang_pegawai), Toast.LENGTH_SHORT).show()
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
                Toast.makeText(this, getString(R.string.sukses_simpan_pegawai), Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, getString(R.string.gagal_simpan_pegawai), Toast.LENGTH_SHORT).show()
            }
    }
}