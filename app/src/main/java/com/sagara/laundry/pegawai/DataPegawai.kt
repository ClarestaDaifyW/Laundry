package com.sagara.laundry.pegawai

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.sagara.laundry.R
import com.sagara.laundry.adapter.adapter_data_pelanggan
import com.sagara.laundry.modeldata.ModelPelanggan
import com.sagara.laundry.pelanggan.TambahPelanggan

class DataPegawai : AppCompatActivity() {
    private val database = FirebaseDatabase.getInstance()
    private val myRef = database.getReference("pegawai")
    private lateinit var rvData_Pegawai: RecyclerView
    private lateinit var fabData_Pegawai_Tambah: FloatingActionButton
    private lateinit var pegawaiList: ArrayList<ModelPelanggan>

    lateinit var tambah : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_data_pegawai)

        initViews()

        // Set layout manager untuk RecyclerView
        val layoutManager = LinearLayoutManager(this).apply {
            reverseLayout = true
            stackFromEnd = true
        }
        rvData_Pegawai.layoutManager = layoutManager
        rvData_Pegawai.setHasFixedSize(true)

        // Tombol FloatingActionButton untuk menambah data pelanggan
        fabData_Pegawai_Tambah.setOnClickListener {
            val intent = Intent(this@DataPegawai, TambahPelanggan::class.java)
            startActivity(intent)
        }

        // Memanggil fungsi untuk mengambil data dari Firebase
        getData()

        tambah = findViewById(R.id.fabData_Pegawai_Tambah)

        tambah.setOnClickListener{
            val intent = Intent(this, TambahPegawai:: class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun initViews() {
        rvData_Pegawai = findViewById(R.id.rvData_Pegawai)
        fabData_Pegawai_Tambah= findViewById(R.id.fabData_Pegawai_Tambah)
        pegawaiList = ArrayList()
    }
    private fun getData() {
        val query = myRef.orderByChild("IDPelanggan").limitToLast(100)
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    pegawaiList.clear()
                    for (dataSnapshot in snapshot.children) {
                        val pelanggan = dataSnapshot.getValue(ModelPelanggan::class.java)
                        pelanggan?.let { pegawaiList.add(it) }
                    }
                    val adapter = adapter_data_pelanggan(pegawaiList)
                    rvData_Pegawai.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DataPegawai, error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}