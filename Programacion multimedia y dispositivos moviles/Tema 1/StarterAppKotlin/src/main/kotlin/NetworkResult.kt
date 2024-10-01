import kotlin.random.Random
// Definimos una sealed class para representar diferentes estados de una operación de red. Incluimos un companion object.
sealed class NetworkResult {
    // son clases especializadas en datos
    data class Success(val data: String) : NetworkResult()
    data class Error(val exception: Exception) : NetworkResult()

    // Solo se puede crear una instancia
    object Loading : NetworkResult()

    // es la configuración por defecto, en este caso solo son el número de resultados
    companion object {
        const val NUM_RESULTS = 3
    }
}

// Simulamos una función que devuelve un resultado de red (puede ser éxito, error o cargando)
fun fetchDataFromNetwork(): NetworkResult {
    Thread.sleep(500)
    when(Random.nextInt(NetworkResult.NUM_RESULTS*3)) {
        0 -> { return NetworkResult.Success("Datos recibidos correctamente") }
        1 -> { return NetworkResult.Error(Exception("Error de conexión")) }
        else -> { return NetworkResult.Loading }
    }
}

// Función que procesa el resultado de la operación de red
fun handleNetworkResult(result: NetworkResult) {
    when (result) {
        is NetworkResult.Success -> {
            println("Operación exitosa: ${result.data}")
        }
        is NetworkResult.Error -> {
            println("Ocurrió un error: ${result.exception.message}")
        }
        NetworkResult.Loading -> {
            println("Cargando datos...")
        }
    }
}
