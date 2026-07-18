package com.nao.recyclerviewreuse

/**
 * サンプルアプリの動作モード。
 */
enum class SampleMode {
    /** ClickListenerが残るバグを再現 */
    BUG,

    /** ClickListenerを解除して修正版を確認 */
    FIXED;

    val shouldResetClickListener: Boolean
        get() = this == FIXED
}
