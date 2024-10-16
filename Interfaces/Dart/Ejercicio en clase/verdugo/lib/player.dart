import 'dart:io';

class Player {
  static String userWord = "";

  // Insertar por teclado
  void insertKeyboard() {
    userWord = stdin.readLineSync()!;
  }

  // Devuelve palabra del usuario
  String getUserWord() {
    return userWord;
  }

  
}