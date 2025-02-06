package com.sagara.laundry.pelanggan

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

class DataPelanggan : AppCompatActivity() {

    private val database = FirebaseDatabase.getInstance()
    private val myRef = database.getReference("pelanggan")
    private lateinit var rvDataPelanggan: RecyclerView
    private lateinit var fabTambahPelanggan: FloatingActionButton
    private lateinit var pelangganList: ArrayList<ModelPelanggan>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_data_pelanggan)

        initViews()

        // Set layout manager untuk RecyclerView
        val layoutManager = LinearLayoutManager(this).apply {
            reverseLayout = true
            stackFromEnd = true
        }
        rvDataPelanggan.layoutManager = layoutManager
        rvDataPelanggan.setHasFixedSize(true)

        // Tombol FloatingActionButton untuk menambah data pelanggan
        fabTambahPelanggan.setOnClickListener {
            val intent = Intent(this@DataPelanggan, TambahPelanggan::class.java)
            startActivity(intent)
        }

        // Memanggil fungsi untuk mengambil data dari Firebase
        getData()

        // Mengatur padding untuk system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initViews() {
        rvDataPelanggan = findViewById(R.id.rvData_Pelanggan)
        fabTambahPelanggan = findViewById(R.id.fabData_Pengguna_Tambah)
        pelangganList = ArrayList()
    }

    private fun getData() {
        val query = myRef.orderByChild("IDPelanggan").limitToLast(100)
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    pelangganList.clear()
                    for (dataSnapshot in snapshot.children) {
                        val pelanggan = dataSnapshot.getValue(ModelPelanggan::class.java)
                        pelanggan?.let { pelangganList.add(it) }
                    }
                    val adapter = adapter_data_pelanggan(pelangganList)
                    rvDataPelanggan.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DataPelanggan, error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
