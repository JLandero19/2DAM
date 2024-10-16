// Ejercicio 2
double sumaPrecios(Map<String, double?> productos) {
  double? sumatorio;
  for (var element in productos.values) {
    sumatorio = (element ?? 0)!;    
  }
  return sumatorio!;
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