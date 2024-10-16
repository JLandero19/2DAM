import 'package:flutter/material.dart';

class EjemploLayout3RowColumn extends StatefulWidget {
  const EjemploLayout3RowColumn({super.key});

  @override
  State<EjemploLayout3RowColumn> createState() => _EjemploLayout3RowColumn();
}

class _EjemploLayout3RowColumn extends State<EjemploLayout3RowColumn> {
  @override
  Widget build(BuildContext context) {
    // Trabajamos con Column
    // return Scaffold(
    //   backgroundColor: Colors.red,
    //   body: SafeArea(
    //     child: Column(
    //       mainAxisSize: MainAxisSize.max,
    //       verticalDirection: VerticalDirection.down,
    //       // mainAxisAlignment: MainAxisAlignment.spaceAround,
    //       // CrossAxisAlignment.stretch -> completa la columna
    //       // CrossAxisAlignment.start -> se ajusta al inicio de la columna
    //       // CrossAxisAlignment.center -> se ajusta al centro de la columna
    //       // CrossAxisAlignment.end -> se ajusta al final de la columna
    //       // Para probarlo tienes que ponerle a una columna más width
    //       crossAxisAlignment: CrossAxisAlignment.center,
    //
    //       children: [
    //         Container(
    //           width: double.infinity,
    //           height: 100,
    //           color: Colors.black,
    //           margin: const EdgeInsets.only(bottom: 100),
    //         ),
    //         Container(
    //           width: 100,
    //           height: 100,
    //           color: Colors.orange,
    //         ),
    //         // Crea un espacio
    //         const SizedBox(
    //           height: 100,
    //         ),
    //         Container(
    //           width: 100,
    //           height: 100,
    //           color: Colors.greenAccent,
    //         ),
    //         Container(
    //           width: 100,
    //           height: 100,
    //           color: Colors.blueAccent,
    //         ),
    //       ],
    //     ),
    //   )
    // );
    return Scaffold(
        backgroundColor: Colors.red,
        body: SafeArea(
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceAround,
            mainAxisSize: MainAxisSize.min,
            children: [
              Container(
                width: 100,
                height: 100,
                color: Colors.black,
              ),
              Container(
                width: 100,
                height: 100,
                color: Colors.orange,
              ),
              Container(
                width: 100,
                height: 100,
                color: Colors.greenAccent,
              ),
            ],
          ),
        )
    );
  }
}
