package com.nao.recyclerviewreuse

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SampleModeTest {

    @Test
    fun BUGモードではClickListenerをリセットしない() {
        assertFalse(SampleMode.BUG.resetClickListener)
    }

    @Test
    fun FIXEDモードではClickListenerをリセットする() {
        assertTrue(SampleMode.FIXED.resetClickListener)
    }
}
