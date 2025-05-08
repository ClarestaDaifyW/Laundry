package com.sagara.laundry.layanan


import android.content.Intent
import android.widget.Toast
import android.os.Bundle
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
import com.sagara.laundry.adapter.adapter_data_Layanan
import com.sagara.laundry.modeldata.ModelLayanan

class datalayanan : AppCompatActivity() {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("layanan")
    lateinit var rv_data_layanan: RecyclerView
    lateinit var fabData_layanan_tambah: FloatingActionButton
    lateinit var layananList: ArrayList<ModelLayanan>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_datalayanan)
        init()
        val layoutManager = LinearLayoutManager(this)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        rv_data_layanan.layoutManager=layoutManager
        rv_data_layanan.setHasFixedSize(true)
        layananList = arrayListOf<ModelLayanan>()
        tekan()
        getdata()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun getdata() {
        val query = myRef.orderByChild("idLayanan").limitToLast(100)
        query.addValueEventListener(object : ValueEventListener {
            override fun  onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    layananList.clear()
                    for (dataSnapshot in snapshot.children){
                        val layanan = dataSnapshot.getValue(ModelLayanan::class.java)
                        layananList.add(layanan!!)
                    }
                    val adapter = adapter_data_Layanan(layananList)
                    rv_data_layanan.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }
            override fun  onCancelled(error : DatabaseError) {
                Toast.makeText(this@datalayanan, error.message,Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun init() {
        rv_data_layanan = findViewById(R.id.rv_data_layanan)
        fabData_layanan_tambah = findViewById(R.id.fabData_layanan_tambah)

    }
    fun tekan() {
        fabData_layanan_tambah.setOnClickListener {
            val intent = Intent(this, TambahLayanan::class.java)
            startActivity(intent)
        }
    }
}