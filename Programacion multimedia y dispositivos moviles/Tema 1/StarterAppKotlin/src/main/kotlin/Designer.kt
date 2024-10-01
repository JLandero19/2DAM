// Es una clase heredada de Person
class Designer(name: String, age : Int) : Person(name,age) {
    // Está sobreescribiendo el metodo introducePerson()
    // se puede hacer porque en el padre lo tiene como [open]
    override fun introducePerson() {
        // super -> te permite acceder a los atributos/propiedades y metodos del padre
        //super.introducePerson()
        println("Soy muy original, mi nombre es $name y soy diseñador.")
    }
}