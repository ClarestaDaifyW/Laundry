package com.sagara.laundry.transaksi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.sagara.laundry.R
import com.sagara.laundry.adapter.adapter_pilih_layanan
import com.sagara.laundry.modeldata.ModelLayanan

class activity_pilih_layanan : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var layananList: ArrayList<ModelLayanan>
    private lateinit var adapter: adapter_pilih_layanan
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilih_layanan)

        recyclerView = findViewById(R.id.rv_data_PL)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        layananList = arrayListOf()
        adapter = adapter_pilih_layanan(layananList) { layanan ->
            val intent = Intent()
            intent.putExtra("NAMA_LAYANAN", layanan.tvCard_NamaLayanan)
            intent.putExtra("HARGA", layanan.tvCard_Harga)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
        recyclerView.adapter = adapter

        // Ambil data dari Firebase
        database = FirebaseDatabase.getInstance().getReference("Layanan")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                layananList.clear()
                if (snapshot.exists()) {
                    for (layananSnapshot in snapshot.children) {
                        val layanan = layananSnapshot.getValue(ModelLayanan::class.java)
                        layanan?.let { layananList.add(it) }
                    }
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@activity_pilih_layanan, "Gagal mengambil data", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
