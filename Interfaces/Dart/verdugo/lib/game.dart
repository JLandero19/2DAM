import 'dart:io';

mixin Game {
  int tryGame = 7;
  static bool validate = false;
  String secretString = "";
  Set<String> lettersMencioned = {};
  List<String> confirmSecretString = [];
  String convertArrToString = "";

  // Convierte una lista de String en un String
  convertArrayToString() {
    convertArrToString = "";
    for (var i = 0; i < secretString.length; i++) {
      if (confirmSecretString[i] == secretString[i]) {
        convertArrToString += confirmSecretString[i];
      } else {
        convertArrToString += "_";
      }
    }
    convertArrToString = convertArrToString.toUpperCase();
    return convertArrToString;
  }

  // Inicia el juego
  startGame() {
    // Este bucle es para evitar salir de aquí sin la palabra secreta
    do {
      // Guardamos la palabra secreta
      print("Introduce tu palabra secreta [1-100 letras]:");
      secretString = stdin.readLineSync()!;

      // Evitamos que sea nulo, pase de 100 caracteres y no sea un número
      if (secretString != "" && secretString.length <= 100 
          && (int.tryParse(secretString) == null || double.tryParse(secretString) == null)) {
        // Convertimos en mayusculas
        secretString = secretString.toUpperCase();
        
        // Realizar está operación nos ayuda a controlar las letras y como imprimen por consola
        for (var i = 0; i < secretString.length; i++) {
          // Rellenamos el array confirmSecretString
          confirmSecretString.add("_");

          // Rellenamos el string convertArrToString
          convertArrToString += "_";
        }

        print(convertArrToString);
      } else {
        print("Has introducido un valor inválido.");
      }
    } while (secretString.length <= 1 || 
            int.tryParse(secretString) != null || 
            double.tryParse(secretString) != null);
    
  }

  // Está función es para hacer el bucle del juego es la que permite su continuación
  continueGame() {
    do {
      propose();
      // print(secretString);
      if (tryGame >= 1 && convertArrToString == secretString) {
        return print("SALVADO... la palabra secreta es $secretString");
      }
    } while (tryGame > 0 && secretString != convertArrToString);
    
    
  }

  // Esté sería el menú para intentar adivinar por letra o por palabra
  propose() {
    // option -> es para elegir una opcion
    int option = 0;
    /**
     * operator -> es para poder pasar un número aunque ponga String
     * El motivo de esto es porque stdin.readLineSync()!; no admite variables (int)
     */
    String operator = "";
    do {
      // Motramos las opciones por consola
      print("Elige tu intento:");
      print("[1] Adivinar Letra");
      print("[2] Adivinar Palabra");

      // en operator recogemos el número
      operator = stdin.readLineSync()!;
      
      if (int.tryParse(operator) != null) {
        // Lo parseamos en número entero
        option = int.parse(operator);

        switch (option) {
          case 1:
            proposeLetter();
            break;
          case 2:
            proposeWord();
            break;
        }
      } else {
        print("Has introducido un valor inválido.");
      }

    } while (operator == "" || int.tryParse(operator) == null || option < 1 || option > 2); 
  }

  // Este método se encarga proponer letras
  proposeLetter() {
    String letter = "";
    validate = false;
    do {
      // Te informa de intentos que te quedan y te pide la siguiente letra
      print("Introduce tu letra [Tienes $tryGame intentos]:");
      letter = stdin.readLineSync()!;
      letter = letter.toUpperCase();
      if (letter.length == 1 && int.tryParse(letter) == null) {
        // Aqui comprobamos que las letras coincidan
        for (var i = 0; i < secretString.length; i++) {
          if (secretString[i] == letter) {
            // Si coinciden el validate pasa a true que lo usamos más adelante
            validate = true;
            // En confirmSecretString cambiamos la letra por la que se ha recibido actualmente
            if (lettersMencioned.contains(letter) == false) {
              confirmSecretString[i] = letter;
            }
          }
        }
      } else {
        print("Has introducido un valor inválido.");
      }      
    } while (letter.length != 1 || int.tryParse(letter) != null);

    if (validate == false) {
      if (lettersMencioned.contains(letter)) {
        return print("COLGANDO... letra ya mencionada");
      } else {
        tryGame--;

        // Si tryGame es mayor que 0 es que todavía le quedan intentos
        if (tryGame <= 0) {
          // Si no entra en el if anterior es AHORCADO
          return print("AHORCADO");
        } else {
          return print("COLGANDO... te quedan $tryGame intentos");
        }
      }
    }
    lettersMencioned.add(letter);

    // Imprimo la actualización del ahorcado para para que el jugador tenga 1 pista
    print(convertArrayToString());
  }

  proposeWord() {
    String wordPlayer = "";
    validate = false;
    do {
      // Te informa de intentos que te quedan y te pide la siguiente letra
      print("Introduce tu palabra [Tienes $tryGame intentos]:");
      wordPlayer = stdin.readLineSync()!;
      wordPlayer = wordPlayer.toUpperCase();

      if (wordPlayer == secretString) {
        convertArrToString = wordPlayer;
      } else {
        if (int.tryParse(wordPlayer) == null) {
          tryGame--;
          return print("COLGANDO... te quedan $tryGame intentos");
        } else {
          print("Has introducido un valor inválido.");
        }
        
      }

    } while (wordPlayer.length <= 1 || int.tryParse(wordPlayer) != null);
  }
}