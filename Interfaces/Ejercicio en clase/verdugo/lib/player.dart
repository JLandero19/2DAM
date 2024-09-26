import 'dart:io';

class Player {
  String userWord = "";

  void readKeyboard () {
    print("Introduce tu intento de palabra");
    String word = stdin.readLineSync()!;
    userWord = word;
  }

  String secretWord() {
    return userWord;
  }

}