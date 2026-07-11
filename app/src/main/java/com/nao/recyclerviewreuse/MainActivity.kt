package com.nao.recyclerviewreuse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.nao.recyclerviewreuse.databinding.ActivityMainBinding
import com.nao.recyclerviewreuse.model.Item

/**
 * 起動時の動作モード。
 */
private val sampleMode = SampleMode.FIXED

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = SampleAdapter(
                items = createSampleItems(),
                sampleMode = sampleMode
            )
        }

        binding.tvMode.text = sampleMode.title

        binding.tvMode.setTextColor(
            ContextCompat.getColor(
                this,
                sampleMode.colorRes
            )
        )
    }

    private fun createSampleItems(): List<Item> =
        List(40) { index ->
            Item(
                id = index,
                isClickable = index < 10
            )
        }
}
