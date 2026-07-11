package com.nao.recyclerviewreuse

import androidx.annotation.ColorRes

/**
 * サンプルアプリの動作モード。
 */
enum class SampleMode(
    val title: String,
    @ColorRes val colorRes: Int,
    val resetClickListener: Boolean
) {

    /** ClickListenerが残るバグを再現 */
    BUG(
        title = "Bug Mode",
        colorRes = android.R.color.holo_red_dark,
        resetClickListener = false
    ),

    /** ClickListenerを解除して修正版を確認 */
    FIXED(
        title = "Fixed Mode",
        colorRes = android.R.color.holo_green_dark,
        resetClickListener = true
    )
}
