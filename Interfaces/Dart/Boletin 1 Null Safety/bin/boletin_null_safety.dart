import 'package:boletin_null_safety/ejercicio1.dart';
import 'package:boletin_null_safety/ejercicio2.dart';
import 'package:boletin_null_safety/ejercicio3.dart';
import 'package:boletin_null_safety/ejercicio4.dart';
import 'package:boletin_null_safety/ejercicio5.dart';
import 'package:boletin_null_safety/ejercicio6.dart';

void main(List<String> arguments) {
  // Ejercicio 1
  print(suma(5, null));
  print(suma(null, 10));
  print(suma(null,null));
  print(suma(2, 3));

  // Ejercicio 2
  Map<String, double?> productos1 = {
    'Manzana': 2.5,
    'Banana': null,
    'Naranja': 1.2,
    'Pera': null,
    'Fresa': 3.0
  };

  print(sumaPrecios(productos1));

  // Ejercicio 3
  print(filtrarLista(['Hola', null, 'Mundo']));
  print(filtrarLista([null, null]));

  // Ejercicio 4
  Persona p1 = Persona('Carlos', 25);
  p1.presentacion();

  Persona p2 = Persona('Ana'); 
  p2.presentacion();

  // Ejercicio 5
  List<int> lista = [10, 20, 30, 40];
  print(obtenerResultado(lista, 30));
  print(obtenerResultado(lista, 50));

  // Ejercicio 6
  List<Producto> productos2 = [
    Producto('Televisor', 500),
    Producto('Celular', null),
    Producto('Laptop', 1000),
    Producto('Tablet', null)
  ];
  print(productoMasCaro(productos2));
}
