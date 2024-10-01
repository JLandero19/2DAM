class Programmer(
    nombre: String,
    edad: Int,
    private val language : ProgrammingLang) : Person(nombre, edad) {

    private var programsCreated : MutableList<String> = mutableListOf()

    // Está sobreescribiendo el metodo introducePerson()
    // se puede hacer porque en el padre lo tiene como [open]
    override fun introducePerson() {
        // super -> te permite acceder a los atributos/propiedades y metodos del padre
        super.introducePerson()
        println("Además, soy programador especialista en $language")
    }
    // añade un programa a la lista mutable programsCreated
    fun createProgram(program : String) {
        programsCreated.add(program)
    }

    // muestra los programas creados
    fun showPrograms() {
        // pregunta la longitud de la lista programsCreated
        if(programsCreated.size==0) {
            // si es 0 muestra lo siguente
            println("Todavía no he creado ningún programa.")
        } else {
            // en caso contrario, muestra los programas
            println("Ya he creado ${programsCreated.size} programas. Estos programas son:")
            programsCreated.forEach {
                println(it)
            }
        }
    }
}
// Clase enumerada con los lenguajes
enum class ProgrammingLang {
    JAVA,
    PHP,
    CPP,
    KOTLIN,
    PYTHON
}