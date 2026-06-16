package com.google.adk.githubactions.discussion

import com.google.adk.kt.runners.InMemoryRunner
import com.google.adk.kt.agents.LlmAgent
import com.google.adk.kt.agents.Instruction
import com.google.adk.kt.models.Gemini
import com.google.adk.kt.types.Content
import com.google.adk.kt.agents.RunConfig
import java.io.File
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Error: Please provide the path to a local file as the first argument.")
        exitProcess(1)
    }

    val filePath = args[0]
    val file = File(filePath)
    if (!file.exists()) {
        println("Error: File not found at $filePath")
        exitProcess(1)
    }

    val fileContent = file.readText()

    val apiKey = System.getenv("GEMINI_API_KEY") ?: System.getenv("GOOGLE_API_KEY") ?: "dummy_key"
    if (apiKey == "dummy_key") {
        println("Warning: GEMINI_API_KEY or GOOGLE_API_KEY is not set. The model call will likely fail unless a real API key is configured.")
    }

    val agent = LlmAgent(
        name = "hello_world_agent",
        model = Gemini(name = "gemini-2.5-flash", apiKey = apiKey),
        instruction = Instruction("You are a friendly greeting agent. Greet the user with a cheery Hello World message!")
    )

    val runner = InMemoryRunner(agent)
    
    println("Reading content from file: $filePath")
    val content = Content.fromText("user", fileContent)
    
    try {
        val responseIterator = runner.run("user-1", "session-1", content, RunConfig())
        
        println("Agent Response:")
        while (responseIterator.hasNext()) {
            val event = responseIterator.next()
            event.content?.parts?.forEach { part ->
                print(part.text)
            }
        }
        println()
    } catch (e: Exception) {
        println("Error running agent: ${e.message}")
        e.printStackTrace()
        exitProcess(1)
    }
}
