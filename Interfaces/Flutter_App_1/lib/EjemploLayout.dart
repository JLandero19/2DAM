import 'package:flutter/material.dart';

class EjemploLayout extends StatefulWidget {
  const EjemploLayout({super.key});

  @override
  State<EjemploLayout> createState() => _EjemploLayout();
}

class _EjemploLayout extends State<EjemploLayout> {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text("My App"),
          backgroundColor: Colors.greenAccent,
          centerTitle: true,
          actions: const [
            Text("Botón 1"),
          ],
          leading: const Center(
            child: Text("VOLVER"),
          ),
        ),
        body: Center(
          child: Image.network(
            "https://picsum.photos/200/300",
          ),
        ),
        // backgroundColor: Colors.grey[200],
      ),

    );
  }
}
