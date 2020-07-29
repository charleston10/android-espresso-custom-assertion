package com.charleston.espressocustomassertion

import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main),
    CompoundButton.OnCheckedChangeListener,
    View.OnClickListener {

    private val listAdapter by lazy { ListAdapter() }

    private val items = listOf(
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        list.let {
            it.adapter = listAdapter
            it.layoutManager = LinearLayoutManager(this@MainActivity)
        }

        switch1.setOnCheckedChangeListener(this)
        button.setOnClickListener(this)

        listAdapter.addItems(getList())
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        listAdapter.addItems(getList(isChecked))
    }

    override fun onClick(v: View?) {
        listAdapter.addItems(getListMapped())
    }

    private fun getList(filter: Boolean = false): List<ItemModel> {
        return if (filter) {
            items.filter {
                (it.age?.let { value ->
                    value <= 50
                } ?: false)
            }
        } else {
            items
        }
    }

    private fun getListMapped(): List<ItemModel> {
        return items.map {
            it.age = null
            it
        }
    }
}