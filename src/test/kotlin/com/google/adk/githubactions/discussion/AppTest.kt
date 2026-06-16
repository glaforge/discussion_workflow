package com.google.adk.githubactions.discussion

import com.google.adk.kt.agents.Instruction
import com.google.adk.kt.agents.LlmAgent
import com.google.adk.kt.models.Gemini
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class AppTest {
    @Test
    fun agentIsConfiguredCorrectly() {
        val apiKey = "dummy_key"
        val agent = LlmAgent(
            name = "hello_world_agent",
            model = Gemini(name = "gemini-2.5-flash", apiKey = apiKey),
            instruction = Instruction("You are a friendly greeting agent. Greet the user with a cheery Hello World message!")
        )
        assertNotNull(agent)
        assertEquals("hello_world_agent", agent.name)
    }
}
