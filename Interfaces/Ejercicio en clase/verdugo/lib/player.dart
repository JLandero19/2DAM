import 'dart:io';

class Player {
  static String userWord = "";

  void insertKeyboard() {
    userWord = stdin.readLineSync()!;
  }

  String getUserWord() {
    return userWord;
  }
  
}