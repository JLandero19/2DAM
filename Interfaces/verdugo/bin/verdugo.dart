// El objetivo de esta tarea es poner en práctica las características aprendidas en las clases anteriores. 
// Aunque en este caso no es siempre la mejor opción, estamos obligados a usar para resolver este problema: 
// 1. Más de una clase. ✔
// 2. Atributos o métodos estáticos. ✔
// 3. Mixins ✔
// 4. Listas ✔
// 5. Constructores nombrados. ✔
// 6. Parámetros de entrada de métodos/funciones nombrados y opcionales. 

// Jugador y Verdugo (Quién propone la palabra secreta)
import 'package:verdugo/player.dart';

void main(List<String> arguments) {
  // Empezamos el juego
  var play = Player();
  // Se continua el juego hasta terminar la partida
  play.continueGame();
}
