import 'dart:io';

class Game {
  static String secretWord = "";
  List<String> letterList = [];
  Set<String> setLetter = {};  

  int countTry = 7;

  // Game(secretWord);
  
  // Game.maxTrys(secretWord, this.countTry);

  bool findWord(String word) {
    return true;
  }

  void insertWordSecret() {
    secretWord = stdin.readLineSync()!;
  }

  String getSecretWord() {
    return secretWord;
  }

  String compareLetters(String userWord) {
    int numLetter = getNumLetters();
    int count = 0;
    String result = "COLGADO";
    for (var i = 0; i < userWord.length; i++) {
      if (letterList.contains(userWord[i])) {
        letterList.add(userWord[i]);
      }
    }

    for (var l in letterList) {
      if (!secretWord.contains(l)) {
        countTry--;
        if (countTry <= 0) {
          result = "AHORCADO";
          break;
        }
      } else {
        count++;
        if (count == numLetter) {
          result = "SALVADO";
          break;
        }
      }
    }
    return result;
  }

  int getNumLetters() {
    for (var i = 0; i < secretWord.length; i++) {
      setLetter.add(secretWord[i]);
    }
    return setLetter.length;
  }

}