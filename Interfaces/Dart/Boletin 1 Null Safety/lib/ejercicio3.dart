List<String> filtrarLista(List<String?> lista) {
  List<String> list = [];
  for (var element in lista) {
    String? valor = element ?? "";
    if (valor != "") {
      list.add(valor);
    }
  }
  list.isEmpty ? list.add('No hay datos') : list;

  return list;
}

void main(List<String> args) {
  // Ejercicio 3
  print(filtrarLista(['Hola', null, 'Mundo']));
  print(filtrarLista([null, null]));
}