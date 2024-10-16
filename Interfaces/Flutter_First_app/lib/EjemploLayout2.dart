import 'package:flutter/material.dart';

class Ejemplolayout2 extends StatefulWidget {
  const Ejemplolayout2({super.key});

  @override
  State<Ejemplolayout2> createState() => _Ejemplolayout2();
}

class _Ejemplolayout2 extends State<Ejemplolayout2> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.red,
      // Container() -> por defecto es transparente
      body: SafeArea(
          child: Container(
            // Tamaño de contenedor width + height
            width: 250,
            height: 250,
            // Para hacer Padding o Margin EdgeInsets
            // margin: const EdgeInsets.all(50),
            // padding: const EdgeInsets.only(top: 50, left: 50),
            margin: const EdgeInsets.symmetric(vertical: 120),
            alignment: Alignment.centerLeft,
            color: Colors.greenAccent,
            child: const Text("Hola Mundo"),
          ),
      ),
    );
  }
}
