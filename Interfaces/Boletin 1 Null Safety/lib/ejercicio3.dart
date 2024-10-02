List<String> filtrarLista(List<String?> lista) {
  List<String> list = [];
  for (var element in lista) {
    if (element != null) {
      list.add(element);
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