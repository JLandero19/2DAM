// open -> permite que tenga herencia
// protected var nombreVariable -> permite que los hijos cambien el contenido de la variable
open class Person (protected var name : String, protected var age : Int) {
    // open -> permite el acceso a los hijos y su modificación
    open fun introducePerson() {
        println("Hola, mi nombre es $name y mi edad es $age")
    }
}