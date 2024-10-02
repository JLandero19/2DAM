class Persona {
  String nombre;
  int? edad;
  Persona(this.nombre, [this.edad]);

  void presentacion() {
    print("Mi llamo $nombre y ${ (edad != null) ? "tengo $edad años" : "no he proporcionado mi edad." } ");
  }
}

void main(List<String> args) {
  // Ejercicio 4
  Persona p1 = Persona('Carlos', 25);
  p1.presentacion();

  Persona p2 = Persona('Ana'); 
  p2.presentacion();
}