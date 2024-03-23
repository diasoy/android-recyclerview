package com.example.recyclerview
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvHandphone: RecyclerView
    private val list = ArrayList<Handphone>()
    private lateinit var layoutManager: RecyclerView.LayoutManager // Deklarasi layoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvHandphone = findViewById(R.id.rv_handphone)
        rvHandphone.setHasFixedSize(true)
        list.addAll(getListHandphone())
        showRecyclerList()
    }

    private fun getListHandphone(): ArrayList<Handphone> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription =
            resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listHandphone = ArrayList<Handphone>()
        for (i in dataName.indices) {
            val handphone = Handphone(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listHandphone.add(handphone)
        }
        return listHandphone
    }

    private fun showRecyclerList() {
        layoutManager = LinearLayoutManager(this)
        rvHandphone.layoutManager = layoutManager
        val listHeroAdapter = ListHandphoneAdapter(list)
        rvHandphone.adapter = listHeroAdapter
        listHeroAdapter.setOnItemClickCallback(object : ListHandphoneAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Handphone) {
                showSelectedHandphone(data)
            }
        })
    }

    private fun showSelectedHandphone(handphone: Handphone) {
        Toast.makeText(this, "Kamu memilih " + handphone.name, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = MenuInflater(this)
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_linear_view -> {
                layoutManager = LinearLayoutManager(this)
                rvHandphone.layoutManager = layoutManager
                true
            }
            R.id.menu_grid_view -> {
                layoutManager = GridLayoutManager(this, 2)
                rvHandphone.layoutManager = layoutManager
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}