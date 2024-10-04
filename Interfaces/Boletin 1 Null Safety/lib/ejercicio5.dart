int? buscarElemento(List<int> lista, int numero) {
  // Sacamos el indice si lo encuentra, si no lo encuentra indice = -1
  int? indice = lista.indexOf(numero);
  // Si indice es >= 0 contiene indice en el caso contrario será null (para poder utilizar null safety)
  indice = (indice >= 0) ? indice : null;

  return indice;
}

String obtenerResultado(List<int> lista, int numero) {
  int? indice = buscarElemento(lista, numero);
  String result = "";
  indice = indice ?? -1;

  // Opción comprimida
  result = (indice >= 0) ? "El número está en el índice $indice" : "Elemento no encontrado";

  // Opción estándar
  // if (indice >= 0) {
  //   result = "El número está en el índice $indice";
  // } else {
  //   result = "Elemento no encontrado";
  // }

  return result;
}

void main(List<String> args) {
  // Ejercicio 5
  List<int> lista = [10, 20, 30, 40];
  print(obtenerResultado(lista, 30));
  print(obtenerResultado(lista, 50));
}