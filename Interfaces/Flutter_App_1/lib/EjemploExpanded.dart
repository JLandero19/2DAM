import 'package:flutter/material.dart';

class EjemploExpanded extends StatefulWidget {
  const EjemploExpanded({super.key});

  @override
  State<EjemploExpanded> createState() => _EjemploExpandedState();
}

class _EjemploExpandedState extends State<EjemploExpanded> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: Column(
          children: [
            // Expanded -> si todos los contenedores son hijo de un Expanded se reparten espacio
            Expanded(
              // flex -> coge el tamaño proporcional
              flex: 1,
              child: Container(
                width: 100,
                color: Colors.red,
              ),
            ),
            Expanded(
              flex: 1,
              child: Container(
                width: 100,
                color: Colors.yellow,
              ),
            ),
            Expanded(
              flex: 2,
              child: Container(
                width: 100,
                color: Colors.green,
              ),
            ),
          ],
        )
      ),
    );
  }
}
