package com.nao.recyclerviewreuse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
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

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(
                top = bars.top,
                bottom = bars.bottom
            )
            insets
        }

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
