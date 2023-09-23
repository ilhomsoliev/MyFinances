package com.ilhomsoliev.myfinances.app.navigation

sealed class Graph(val route: String) {
    object RootGraph : Graph("RootGraph")
    object GoalsGraph : Graph("GoalsGraph")
    object AccountGraph : Graph("AccountGraph")
    object MainGraph : Graph("MainGraph")
    object NotesGraph : Graph("NotesGraph")
    object TodoListGraph : Graph("TodoListGraph")
}