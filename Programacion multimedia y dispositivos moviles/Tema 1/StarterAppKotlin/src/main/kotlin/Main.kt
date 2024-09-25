import java.util.*

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main(args: Array<String>) {

    //basics()

    // Argumentos de entrada al main
//    args.forEach {
//        print("$it, ")
//    }

    // listOf() -> es de solo lectura y no es mutable (sea constante o no)
    val school: List<String> = listOf("mackerel", "trout", "halibut")

    print("Lo elementos de la lista son [foreach]:")
    school.forEach {
        print("$it, ")
    }

    // mutableListOf() -> es una lista modificable
    val myList = mutableListOf("tuna", "salmon", "shark")

    // en estos casos se añade siempre al penultimo elemento
    myList.add(myList.size-1,"sardina")
    myList.add(myList.lastIndex,"boquerones")

    println("\n$myList")

    print("Lo elementos de la lista son: ")
//    for ((index, element) in myList.withIndex()) {
//        if (index != myList.lastIndex) {
//            if (index == myList.lastIndex-1) {
//                print("$element y")
//            } else {
//                print("$element, ")
//            }
//        } else {
//            print(" $element.")
//        }
//    }

    for ((index, element) in myList.withIndex()) {
        when {
            index == myList.lastIndex-1 -> print("$element y")
            index != myList.lastIndex -> print("$element, ")
            else -> print(" $element.")
        }
    }

    println("\n suma 3 + 4 = ${sumaInt(3, 4)}")

    print(fishFood(randomDay()))

}

fun fishFood (day : String) : String {
    var food = ""
    when (day) {
        "Monday" -> food = "flakes"
        "Tuesday" -> food = "pellets"
        "Wednesday" -> food = "redworms"
        "Thursday" -> food = "granules"
        "Friday" -> food = "mosquitoes"
        "Saturday" -> food = "lettuce"
        "Sunday" -> food = "plankton"
    }
    return "Today is $day and food is $food"
}

fun feedTheFish() {
    val day = randomDay()
    val food = "pellets"
    println ("Today is $day and the fish eat $food")
}

fun randomDay() : String {
    val week = arrayOf ("Monday", "Tuesday", "Wednesday", "Thursday",
        "Friday", "Saturday", "Sunday")
    return week[Random().nextInt(week.size)]
}

fun sumaInt(x: Int, y: Int): Int {
    return x + y
}

// Por defecto las funciones pública
fun basics() {
    // println(sayHello())
    // println("Program argument: ${args.joinToString()}")

    // Variable normal tipada
    // El tipado es automático por defecto según el contenido
    var x: Int = 2

    // Constante
    // Variables Float -> 3.5f (Siempre f al final de número)
    val fixed: Float = 3.75f

    // Multiplica
    x = x.times(4) // = 8

    // Suma -> plus(10)
    // toInt() -> redondea hacia abajo siempre
    x = (fixed.plus(10).toInt()) + x

    // Para imprimir variables por pantalla $variable entre ""
    println("x = $x, fixed = $fixed")
    /*
    if (x > fixed) {
        println("$x es mayor que $fixed")
    } else if (x < fixed) {
        println("$x es menor que $fixed")
    } else {
        println("$x es igual que $fixed")
    }
    */

    /*
     * Es el equivalente al switch en otros lenguajes
     * otro ejemplo:
     * when (condition) {
     *      0 -> println("lo que sea")
     *      in 1..39 -> println("lo que sea 2")
     *      else -> println("lo que queda")
     * }
     */
    when {
        // Así podemos añadir más instrucciones
        x > fixed -> {
            println("$x es mayor que $fixed")
        }
        x < fixed -> println("$x es menor que $fixed")
        else -> println("$x es igual que $fixed")
    }

    var name: String? = null

    // readln() -> Devuelve un String (no puede ser nulo en el uso de fichero)
    // readLine() -> Devuelve un String (puede ser nulo en el uso de fichero)

    println("Dime tu nombre:")
    name = readLine()

    //name = "pepe"

    /**
     * Es la manera de llamar a variables que puedan ser nulas
     * sustituye el if (name != null)
     */
    name?.let {
        println("Hola $it, mi nombre ${it.length} letras")

        // ?: -> es el operador elvis
    } ?: println("Mi nombre es nulo")
}

fun sayHello(): String {
    return "Hello world! 2"
}