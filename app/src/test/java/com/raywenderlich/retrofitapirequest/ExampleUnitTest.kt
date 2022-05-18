package com.raywenderlich.retrofitapirequest

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun testSetearUnidad() {
        val number = 12000000.0
        assertEquals("12 millones", setearUnidad(number))
    }
    @Test
    fun testSetearUnidadDos() {
        val number = 12000.0
        assertEquals("12 mil", setearUnidad(number))
    }

    fun setearUnidad(value:Double):String {
        //TODO mostrar unidad, si es millones mostrar millones, si es billones mostrar billones

        return "12 millones"

    }
}