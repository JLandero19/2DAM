import 'package:flutter/material.dart';

class EjemploStack extends StatefulWidget {
  const EjemploStack({super.key});

  @override
  State<EjemploStack> createState() => _EjemploStackState();
}

class _EjemploStackState extends State<EjemploStack> {
  @override
  // Widget build(BuildContext context) {
  //   return Scaffold(
  //     backgroundColor: Colors.green,
  //     body: SafeArea(
  //         // Stack -> es apilar capas dentro de otras capas
  //         child: Stack(
  //           // clipBehavior -> define como se corta la capa superior respeto al que se encuentra por debajo
  //           // clipBehavior: Clip.none,
  //           children: [
  //             Container(
  //               width: 100,
  //               height: 100,
  //               color: Colors.grey[200],
  //             ),
  //
  //             // Positioned -> es para cambiar la posición de una capa
  //             Positioned(
  //               right: 10,
  //               bottom: 10,
  //               child: point(),
  //             ),
  //
  //             Positioned(
  //               top: 10,
  //               right: 10,
  //               child: point(),
  //             ),
  //
  //             Positioned(
  //               top: 10,
  //               left: 10,
  //               child: point(),
  //             ),
  //
  //             Positioned(
  //               bottom: 10,
  //               left: 10,
  //               child: point(),
  //             ),
  //
  //             Positioned(
  //               bottom: 45,
  //               left: 10,
  //               child: point(),
  //             ),
  //
  //             Positioned(
  //               bottom: 45,
  //               right: 10,
  //               child: point(),
  //             ),
  //           ],
  //         )
  //     ),
  //   );
  // }

  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.green,
      body: SafeArea(
        // Stack -> es apilar capas dentro de otras capas
          child: SizedBox(
            width: 100,
            height: 100,
            child: Stack(
              children: <Widget>[
                Container(
                  width: 100,
                  height: 100,
                  color: Colors.grey[100],
                ),
                Container(
                  alignment: Alignment.bottomLeft,
                  margin: const EdgeInsets.all(10),
                  child: point(),
                ),
                Container(
                  alignment: Alignment.bottomRight,
                  margin: const EdgeInsets.all(10),
                  child: point(),
                ),
                Container(
                  alignment: Alignment.centerLeft,
                  margin: const EdgeInsets.all(10),
                  child: point(),
                ),
                Container(
                  alignment: Alignment.centerRight,
                  margin: const EdgeInsets.all(10),
                  child: point(),
                ),
                Container(
                  alignment: Alignment.topLeft,
                  margin: const EdgeInsets.all(10),
                  child: point(),
                ),
                Container(
                  alignment: Alignment.topRight,
                  margin: const EdgeInsets.all(10),
                  child: point(),
                ),
              ],
            ),
          )
      ),
    );
  }

  Widget point() {
    return Container(
      width: 10,
      height: 10,
      color: Colors.black,
    );
  }
}
