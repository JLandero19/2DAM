
import 'dart:io';

class Game {
  static String secretWord = "";
  int countTry = 7;
  
  Game(secretWord);

  Game.maxTry(secretWord, [this.countTry = 7]);

  static void insertWord() {
    print("Introduce tu palabra secreta");
    String? word = stdin.readLineSync();
    secretWord = word!;
  }

  static String getSecretWord() {
    return secretWord;
  }

  static compareLetter(String userWord) {
    for (var i = 0; i < secretWord.length; i++) {
      
    }
  }
}