package com.nao.recyclerviewreuse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.nao.recyclerviewreuse.databinding.ActivityMainBinding
import com.nao.recyclerviewreuse.model.Item

/**
 * サンプルの動作モード。
 *
 * false: ClickListenerが残るバグを再現
 * true : ClickListenerをリセットして修正版を確認
 */
private const val APPLY_FIX = false
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = SampleAdapter(
            items = createSampleItems(),
            applyFix = APPLY_FIX
        )

        binding.tvMode.text =
            if (APPLY_FIX) {
                "Bug Mode : OFF (Fixed)"
            } else {
                "Bug Mode : ON"
            }

        binding.tvMode.setTextColor(
            ContextCompat.getColor(
                this,
                if (APPLY_FIX) {
                    android.R.color.holo_green_dark
                } else {
                    android.R.color.holo_red_dark
                }
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
