package com.nadin.city_locator.data.datasource

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nadin.city_locator.domain.model.City

class JsonCity(private val context: Context, private val fileName: String) {

    /**
     * Reads a JSON file from the assets folder and returns its content as a string.
     *
     * This function is useful for loading static JSON data that is bundled with the application's
     * assets. It opens the specified JSON file from the assets directory, reads the entire content
     * using a BufferedReader, and returns it as a single string. This string can then be parsed into
     * a model or data structure as needed.
     *
     * @param context The context used to access the application's assets.
     * @param fileName The name of the JSON file to be read from the assets folder.
     * @return A string containing the entire content of the specified JSON file.
     **/
    fun readJsonFromAssets(): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }

    /**
     * Parses a JSON string into a list of City objects.
     *
     * This function takes a JSON string representing a collection of cities and parses it into
     * a list of `City` objects. It is typically used after reading the JSON content from the file.
     * The parsing logic can be implemented using a JSON library such as Gson
     *
     * @param jsonString The JSON string to be parsed.
     * @return A list of `City` objects parsed from the JSON string.
     **/
    fun parseJsonToModel(jsonString: String): List<City> {
        val gson = Gson()
        return gson.fromJson(jsonString, object : TypeToken<List<City>>() {}.type)
    }
}