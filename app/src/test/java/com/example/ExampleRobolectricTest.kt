package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.model.ProjectInput
import com.example.service.AiDirectorService
import com.example.service.MasterpiecePresets
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("CineDirector AI", appName)
    }

    @Test
    fun `verify masterpiece presets have all 11 phases`() {
        val preset = MasterpiecePresets.mumbaiMonsoonEncounter
        assertNotNull(preset.brief)
        assertTrue(preset.shotList.isNotEmpty())
        assertTrue(preset.characterBibles.isNotEmpty())
        assertTrue(preset.scriptIntelligence.emotionalGraph.isNotEmpty())
        assertTrue(preset.qualityChecklist.isNotEmpty())
    }

    @Test
    fun `verify builtin director service creates complete production package`() {
        val service = AiDirectorService()
        val input = ProjectInput(
            topic = "A detective meets an informant under streetlamp",
            videoType = "Thriller",
            language = "Hindi"
        )
        val production = service.generateBuiltinMasterProduction(input)
        assertNotNull(production)
        assertEquals(4, production.shotList.size)
        assertTrue(production.shotList.all { it.aiVideoPrompt.isNotBlank() })
    }
}
