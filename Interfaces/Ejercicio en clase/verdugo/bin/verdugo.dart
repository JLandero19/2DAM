import 'package:verdugo/game.dart';
import 'package:verdugo/player.dart';

void main(List<String> arguments) {
  Game game = Game();
  game.insertWordSecret();
  print("Palabra introducida es ${game.getSecretWord()}");

  Player player = Player();
  player.insertKeyboard();

  print(game.compareLetters(player.getUserWord()));
  
}
