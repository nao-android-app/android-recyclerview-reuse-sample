package com.nao.recyclerviewreuse

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.nao.recyclerviewreuse.databinding.ItemSampleBinding
import com.nao.recyclerviewreuse.model.Item

class SampleAdapter(
    private val items: List<Item>,
    private val sampleMode: SampleMode
) : RecyclerView.Adapter<SampleAdapter.ViewHolder>() {

    private companion object {
        var viewHolderCount = 0
    }

    class ViewHolder(
        val binding: ItemSampleBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val holderId = ++viewHolderCount

        // 検証用。
        // このViewHolderが直前に表示していたisClickableの値を保持し、
        // Viewの再利用を可視化するために使用する。
        var previousClickableState: Boolean? = null
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding = ItemSampleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        Log.d("RecyclerSample", "ViewHolder #${viewHolderCount + 1} created")

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val item = items[position]
        val previousIsClickable = holder.previousClickableState
        val binding = holder.binding

        binding.tvTitle.text =
            """
            Item ${item.id}
            ViewHolder #${holder.holderId}
            """.trimIndent()
        binding.tvInfo.text =
            """
            Current : ${item.isClickable}
            Previous : ${previousIsClickable ?: "-"}
            """.trimIndent()

        val backgroundColor =
            getBackgroundColor(previousIsClickable, item.isClickable)

        binding.root.setBackgroundColor(
            ContextCompat.getColor(
                holder.itemView.context,
                backgroundColor
            )
        )

        binding.root.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "Parent Click : ${item.id}",
                Toast.LENGTH_SHORT
            ).show()

        }

        binding.tvDelete.alpha = if (item.isClickable) 1f else 0.4f
        binding.tvDelete.text = if (item.isClickable) {
            "🗑 Delete"
        } else {
            "Delete (Disabled)"
        }

        if (item.isClickable) {
            binding.tvDelete.setOnClickListener {
                Toast.makeText(
                    holder.itemView.context,
                    "Delete Item ${item.id}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else if (sampleMode.resetClickListener) {
            binding.tvDelete.setOnClickListener(null)
        }

        holder.previousClickableState = item.isClickable
    }

    private fun getBackgroundColor(
        previous: Boolean?,
        current: Boolean
    ): Int = when {
        // true -> false（今回の記事で使用）
        previous == true && !current ->
            R.color.background_reused_true_to_false

        // false -> true（検証用）
        // ViewHolderの再利用を分かりやすくするため、
        // false → true に再利用された場合も色分けしています。
        // （今回の記事で再現したい不具合には直接関係ありません）
        previous == false && current ->
            R.color.background_reused_false_to_true

        else ->
            R.color.background_default
    }

    override fun getItemCount() = items.size

}
