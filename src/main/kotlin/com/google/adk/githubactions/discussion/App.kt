package com.google.adk.githubactions.discussion

class App {
    val greeting: String
        get() {
            return "Hello, ADK Discussion Workflow!"
        }
}

fun main() {
    println(App().greeting)
}
