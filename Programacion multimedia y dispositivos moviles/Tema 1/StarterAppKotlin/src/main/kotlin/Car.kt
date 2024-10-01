class Car(
    private var _brand : String,
    private var _model : String = "Challenger",
    private var _year: Int,
    private val _engine : Car.Engine
    // Vehicle es una interfaz
): Vehicle {

    // atributo vehicle de la interface Vehicle
    override val vehicle: String
        get() = "coche: $brand $model del año $year"

    // metodo navigate de la interface Vehicle
    override fun navigate() {
        println("El coche está circulando por la carretera.")
    }

    var isClassic: Boolean = false

    var brand: String
        get() = _brand
        set(value) {
            _brand = value
        }

    var model: String
        get() = _model
        private set(value) {
            _model = value
        }

    var year: Int
        get() = _year
        set(value) {
            _year = value
        }

    val engine: Engine
        get() = _engine

    //bloque de inicialización. Se puede eliminar por la
    //inicialización en la propia declaración.
    init {
        year?.let {
            if (it < 2000) {
                isClassic = true
            }
        } ?: true
        this.brand = brand
        this.model = model
        this.year = year
    }

    fun setClassic() {
        year?.let {
            if (it < 2000) {
                isClassic = true
            }
        } ?: true
    }

    override fun toString(): String {
        return "El coche ${brand} ${model} del año ${year} tiene ${engine.horsePower} caballos de potencia"
    }

    //Clase anidada
    class Engine(private val _horsePower : Int) {
        //getter
        val horsePower : Int
            get() = _horsePower
    }

    //Clase interna (InnerClass) -> Puede acceder a atributos de la clase externa.
    inner class Insurance (private val _insurance : InsuranceCarrier,
                           private val _amount : Float) {
        //getters
        val insurance : String
            get() = _insurance.title

        val amount : Float
            get() = _amount

        override fun toString(): String {
            return "El seguro ${insurance} cuesta ${amount} €"
        }
    }

}