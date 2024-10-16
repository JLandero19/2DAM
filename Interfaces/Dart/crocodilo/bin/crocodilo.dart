// import 'package:crocodilo/crocodilo.dart' as crocodilo;
import 'dart:io';

import '../lib/crocodilo.dart';

void main(List<String> arguments) {
  
  List<Crocodilo> listCrocodilo = [];

  do {
    print("Introduce un texto que tengo entre [1-10] letras:");
    String? stringParameter = stdin.readLineSync();

    /*
     * int.tryParse(stringParameter) == null -> comprobar que no es un entero
     * double.tryParse(stringParameter) == null -> comprobar que no es un double
     */

    if (stringParameter != null && 
        stringParameter.length <= 10 && 
        int.tryParse(stringParameter) == null && 
        double.tryParse(stringParameter) == null) {

      listCrocodilo.add(Crocodilo(stringParameter));
      print("Valor correcto...");
    } else {
      print("Valor incorrecto, solo se permite texto");
    }
  } while (listCrocodilo.length < 3);  

  for (var element in listCrocodilo) {
    print(element.checkCry());
  }

  // Crocodilo coco1 = Crocodilo("Antonio");
  // Crocodilo coco2 = Crocodilo("Javier");
  // Crocodilo coco3 = Crocodilo("Jesus");
  
  // print(coco1.checkCry());
  // print(coco2.checkCry());
  // print(coco3.checkCry());
}

