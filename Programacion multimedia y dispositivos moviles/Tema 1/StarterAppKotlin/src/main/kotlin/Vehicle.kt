// las interfaces te obligan a que definas ciertas funcionalidades
interface Vehicle {
    // En este caso como mínimo tenemos que implementar
    // el atributo vehicle
    // el metodo navigate()
    val vehicle : String
    fun navigate()
    fun place() {
        println("He estacionado el $vehicle.")
    }
}