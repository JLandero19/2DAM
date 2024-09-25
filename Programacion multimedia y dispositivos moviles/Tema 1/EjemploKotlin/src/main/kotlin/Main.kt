package org.example
import java.util.Arrays
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {

    //  Forma de declarar variable con tipado
    var x: Int = 5

    // Para variables que pueden ser nullable se pone en el tipado ?
    var y: Int = 10

    // Encadenar pruebas nullable
    var fishFoodTreats = 6
    fishFoodTreats = fishFoodTreats?.dec() ?: 0
    println("fishFoodTreats = $fishFoodTreats")

    // Constante -> val ...
    val fixed: Int = 7

    /**
     * Para concatenar cadenas con variables en Kotlin es
     * "cadena de texto $nombreVariable"
     */

    // Sumar -> .plus()
    // x = x.plus(3)
    x += 5
    println("x = $x")

    // Restar -> .minus()
    // x = x.minus(2)
    x -= 2
    println("x = $x")

    // Multiplicar -> .times()
    x = x.times(5)
    println("x = $x")

    if (y != null) {
        y = y.times(fixed)
    }
    println("y = $y")

    if (x > y) {
        println("x = $x es mayor y = $y")
    } else if (x < y) {
        println("x = $x es menor y = $y")
    } else {
        println("x = $x es igual y = $y")
    }

    // Esto es para averiguar si una variable entre valores predeterminados
    if (x in 1..30) {
        println("x = $x -> esta entre 1 y 30")
    } else if (x in 31..40) {
        println("x = $x -> esta entre 31 y 40")
    }

    // Es parecido a un switch
    when (x) {
        0 -> println("hola 0")
        in 1..30 -> println("hola entre 1 y 30")
        else -> println("hola el resto")
    }

    // Ejemplo when con funciones
    evaluateData("Hellow World")
    evaluateData(10.5)
    evaluateData(19)
    evaluateData(true)

    // Lista estática -> listOf()
    val alumnos = listOf("Javier", "Jesús", "Antonio")
    println(alumnos)

    // Lista dinámica -> mutableListOf()
    val peces = mutableListOf("atun", "salmon", "tiburon")
    println("lista de peces: $peces")
    peces.add("pez espada")
    println("lista de peces (añadiendo un elemento): $peces")
    peces.remove("tiburon")
    println("lista de peces (quitando un elemento): $peces")

    // arrayOf() -> No tiene tipados (tienes libertad con los tipos de cada elemento del array)
    var alumnos2 = arrayOf("Javier", "Jesús", "Antonio")

    // No necesitas importarlo pero no es lo más cómodo
    // println(java.util.Arrays.toString(alumnos2))

    // Si lo quieres hacer así te toca importarlo "import java.util.Arrays"
    println(Arrays.toString(alumnos2))

    // Puedes anidar los arrayOf con los listOf
    val numbers = intArrayOf(1, 2, 3)
    val oceans = listOf("Atlantic", "Pacific")
    val oddList = listOf(numbers, oceans, "salmon")

    //println(oddList)
    /**
     * numbers -> es una matriz por eso en lugar de imprimir como el resto imprime la dirección
     * DUDA: para ver el valor de numbers deduzco que es primero accediendo a la posición
     * y despues Arrays.toString(numbers)
     */

    //var example = oddList[0] as IntArray
    //println(Arrays.toString(example))

    for (odd in oddList) {
        when (odd) {
            is IntArray -> { println( "Tipo intArrayOf -> " + Arrays.toString(odd)) }
            else -> println("Tipo listOf -> $odd")
        }
    }

    // De esta forma podemos conseguir el valor de las claves y el valor
    val school = arrayOf("shark", "salmon", "minnow")
    for ((index, element) in school.withIndex()) {
        println("Clave: $index - Valor: $element\n")
    }

    

}

// El tipado Any se traga cualquier tipo de Dato
fun evaluateData(data: Any) {
    when (data) {
        in 1..100 -> println("Valor introducido = $data -> El número está en el rango de 1 a 100")
        is String -> println("Valor introducido = $data -> Es una cadena de texto")
        is Boolean -> println("Valor introducido = $data -> Es un valor booleano")
        else -> println("Valor introducido = $data -> Dato no reconocido")
    }
}
