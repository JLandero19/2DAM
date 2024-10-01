//Clase que solo puede tomar el conjunto de valores declarados.
//Tiene dos principales atributos: name (valor) y ordinal (posición)
enum class InsuranceCarrier(val title : String) {
    //por convención los valores se escriben en mayúsculas.
    MAPFRE("Mapfre"),
    VERTI("Verti"),
    MUTUA("Mutua Madrileña"),
    ALLIANZ("Allianz Seguros")
}
