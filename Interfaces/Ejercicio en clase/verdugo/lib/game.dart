import 'dart:io';

class Game {
  static String secretWord = "";

  // Queremos está lista para guardar orden de llegada
  List<String> letterList = [];

  // Lo usamos como referencia para encontrar la palabra secreta
  Set<String> setSecretLetter = {};  

  // Número de intentos
  int countTry = 7;

  String result = "COLGANDO";

  // Insertar palabra secreta
  void insertWordSecret() {
    secretWord = stdin.readLineSync()!;
  }

  // Mostrar palabra secreta
  String getSecretWord() {
    return secretWord;
  }

  // Devuelve el número de letras y añade las letras a la lista [setSecretLetter]
  int getNumLetters() {
    for (var i = 0; i < secretWord.length; i++) {
      setSecretLetter.add(secretWord[i]);
    }
    return setSecretLetter.length;
  }

  // Comparar letras
  String compareLetters(String userWord) {
    // Número de letras
    int numLetter = getNumLetters();

    // Contador
    int count = 0;

    // Este print es para comprobación
    // print(setSecretLetter);

    
    for (var i = 0; i < userWord.length; i++) {
      // Comprobamos si no contiene la letra la lista [setSecretLetter]
      // Está comprobación es para la lista de las letras de la palabra secreta
      if (!setSecretLetter.contains(userWord[i])) {
        // Este print es para comprobación
        // print(letterList);

        // Comprobamos si no contiene la letra la lista [letterList]
        // Está comprobación es para la lista de las letras de las palabras introducidas por el usuario
        if (!letterList.contains(userWord[i])) {
          // Reducimos el contador de intentos
          countTry--;
          // Añadimos la letras a la lista [letterList]
          letterList.add(userWord[i]);
        }

        // Si el contador es menor o igual que 0 (Puede llegar a ser negativo por las letras duplicadas [ejemplo: "elefante"])      
        if (countTry <= 0) {
          result = "AHORCADO";
          break;
        }
      } else {
        // En caso contrario aumentamos el contador
        count++;
        // Si es mayor o igual que numLetter "SALVADO"
        if (count >= numLetter) {
          result = "SALVADO";
          break;
        }
      }
    }
    return result;
  } 
}