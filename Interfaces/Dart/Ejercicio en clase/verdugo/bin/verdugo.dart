import 'package:verdugo/game.dart';
import 'package:verdugo/player.dart';

void main(List<String> arguments) {
  // Instancia el Game
  Game game = Game();

  // Elige palabra secreta
  print("Introduce palabra secreta");
  game.insertWordSecret();
  print("Palabra secreta introducida es: ${game.getSecretWord()}");

  // Instancia el Player
  Player player = Player();

  do {
    // Inserta palabra del Player
    print("Intenta encontrar la palabra secreta");
    player.insertKeyboard();

    // Comparación
    print(game.compareLetters(player.getUserWord()));
    
    // Mostrar número de intentos
    print("Número de intentos restantes: ${game.countTry}");
  } while (game.result == "COLGANDO" && game.countTry > 0 && player.getUserWord() != ".");
}
