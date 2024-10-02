class Producto {
  String nombre;
  double? precio;
  
  Producto(this.nombre, this.precio);
}

String? productoMasCaro(List<Producto> productos) {
  Producto? productoMasCaro;
  for (var element in productos) {
    if (element.precio != null) {
      productoMasCaro = productoMasCaro ?? element;
      if ((productoMasCaro.precio ?? 0) < (element.precio ?? 0)) {
        productoMasCaro = element;
      }
    }
  }
  return productoMasCaro?.nombre;
}


void main(List<String> args) {
  // Ejercicio 6
  List<Producto> productos = [
    Producto('Televisor', 500),
    Producto('Celular', null),
    Producto('Laptop', 1000),
    Producto('Tablet', null)
  ];
  print(productoMasCaro(productos));
}