package com.sagara.laundry.transaksi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.sagara.laundry.R
import com.sagara.laundry.adapter.adapter_pilih_pelanggan
import com.sagara.laundry.modeldata.ModelPelanggan

class activity_pilih_pelanggan : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var databaseReference: DatabaseReference
    private lateinit var pelangganList: ArrayList<ModelPelanggan>
    private lateinit var adapter: adapter_pilih_pelanggan

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilih_pelanggan)

        recyclerView = findViewById(R.id.rv_data_PP)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        pelangganList = ArrayList()
        adapter = adapter_pilih_pelanggan(pelangganList) { pelanggan ->
            // Mengirimkan kembali data ke Transaksi.kt
            val resultIntent = Intent()
            resultIntent.putExtra("NAMA", pelanggan.tvCARD_PELANGGAN_Nama)
            resultIntent.putExtra("NOHP", pelanggan.tvCARD_PELANGGAN_NoHP)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
        recyclerView.adapter = adapter

        // Ambil data dari Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("Pelanggan")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                pelangganList.clear()
                for (data in snapshot.children) {
                    val pelanggan = data.getValue(ModelPelanggan::class.java)
                    if (pelanggan != null) {
                        pelangganList.add(pelanggan)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Error handling (log atau tampilkan pesan)
            }
        })
    }
}
