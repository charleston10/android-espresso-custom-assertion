package com.charleston.espressocustomassertion

import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main),
    CompoundButton.OnCheckedChangeListener {

    private val listAdapter by lazy { ListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        list.let {
            it.adapter = listAdapter
            it.layoutManager = LinearLayoutManager(this@MainActivity)
        }

        switch1.setOnCheckedChangeListener(this)

        listAdapter.addItems(getList())
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        listAdapter.addItems(getList(isChecked))
    }

    private fun getList(filter: Boolean = false): List<ItemModel> {
        val items = listOf(
            ItemModel("Herrera", 10),
            ItemModel("Young", 20),
            ItemModel("Watson", 30),
            ItemModel("Woods", 40),
            ItemModel("Berry", 50),
            ItemModel("Carter", 60),
            ItemModel("Mcdonald", 70),
            ItemModel("Payne", 80),
            ItemModel("Dunn", 90),
            ItemModel("Stone", 100)
        )

        return if (filter) {
            items.filter { it.age <= 50 }
        } else {
            items
        }
    }
}