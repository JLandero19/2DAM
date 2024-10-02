// Ejercicio 2
double sumaPrecios(Map<String, double?> productos) {
  var sumatorio = 0.0;
  for (var element in productos.values) {
    if (element != null) {
      sumatorio += element;
    }
  }
  return sumatorio;
}

void main(List<String> args) {
  // Ejercicio 2
  Map<String, double?> productos = {
    'Manzana': 2.5,
    'Banana': null,
    'Naranja': 1.2,
    'Pera': null,
    'Fresa': 3.0
  };

  print(sumaPrecios(productos));
}