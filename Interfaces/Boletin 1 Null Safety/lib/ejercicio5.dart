int? buscarElemento(List<int> lista, int numero) {
  int? indice;
  for (var i = 0; i < lista.length; i++) {
    if (lista[i] == numero) {
      indice = i;
    }
  }

  return indice;
}

String obtenerResultado(List<int> lista, int numero) {
  int? indice = buscarElemento(lista, numero);
  String result = "";

  indice = indice;

  if (indice != null) {
    result = "El número está en el índice $indice";
  } else {
    result = "Elemento no encontrado";
  }


  return result;
}

void main(List<String> args) {
  // Ejercicio 5
  List<int> lista = [10, 20, 30, 40];
  print(obtenerResultado(lista, 30));
  print(obtenerResultado(lista, 50));
}