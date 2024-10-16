class Persona {
  String nombre;
  int? edad;
  Persona(this.nombre, [this.edad]);

  void presentacion() {
    edad = edad ?? -1;
    print("Mi llamo $nombre y ${ (edad! > 0) ? "tengo $edad años" : "no he proporcionado mi edad." } ");

    // print("Me llamo $nombre y ${ ((edad ?? "no he proporcionado mi edad.") != null) ? "tengo $edad años" : "" }");
  }
}

void main(List<String> args) {
  // Ejercicio 4
  Persona p1 = Persona('Carlos', 25);
  p1.presentacion();

  Persona p2 = Persona('Ana'); 
  p2.presentacion();
}