// Ejercicio 1
int? suma(int? a, int? b) {
  return (a ?? 0) + (b ?? 0);
}

void main(List<String> args) {
  // Ejercicio 1
  print(suma(5, null));
  print(suma(null, 10));
  print(suma(null,null));
  print(suma(2, 3));
}