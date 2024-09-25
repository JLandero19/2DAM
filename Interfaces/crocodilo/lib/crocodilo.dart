class Crocodilo {
  String banner;
  // Para que funcione .contains tiene que ser un Array
  static const vocals = ['a', 'e', 'i', 'o', 'u'];
  
  // Set -> es un tipo de array que no permite duplicados
  Set<String> differentVocals = {};

  // Método constructor
  Crocodilo(this.banner);

  checkCry() {
    // .toLowerCase() -> convierte los String en minusculas
    String bannerToLowercase = banner.toLowerCase();

    for (var i = 0; i < bannerToLowercase.length; i++) {
      // arr.contains(valorBuscado) -> sirve para buscar en este caso un String dentro del Array
      if (vocals.contains(bannerToLowercase[i])) {
        // .add -> añade un elemento al array
        // .remove -> eliminar un elemento del array
        differentVocals.add(bannerToLowercase[i]);
      }
    }
    
    if (differentVocals.length == 2) {
      return "El cocodrilo $banner está llorando...";
    }

    return "El cocodrilo $banner está contento";
  }
}