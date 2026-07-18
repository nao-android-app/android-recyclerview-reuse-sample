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
private var sampleMode = SampleMode.BUG

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

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        setBugMode(sampleMode)

        binding.switchMode.setOnCheckedChangeListener { _, checked ->
            val selectedMode = if (checked) {
                SampleMode.FIXED
            } else {
                SampleMode.BUG
            }
            setBugMode(selectedMode)
        }
    }

    private fun createSampleItems(): List<Item> = List(40) { index ->
        Item(
            id = index,
            isClickable = index < 10
        )
    }

    private fun setBugMode(selectedSampleMode: SampleMode) {
        sampleMode = selectedSampleMode
        binding.recyclerView.adapter = SampleAdapter(
            items = createSampleItems(),
            sampleMode = sampleMode
        )
    }
}
