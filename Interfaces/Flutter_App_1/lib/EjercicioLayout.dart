import 'package:flutter/material.dart';

class EjercicioLayout extends StatefulWidget {
  const EjercicioLayout({super.key});

  @override
  State<EjercicioLayout> createState() => _EjercicioLayoutState();
}

class _EjercicioLayoutState extends State<EjercicioLayout> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: Colors.green,
        body: SafeArea(
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Container(
                width: 100,
                // height: double.infinity,
                color: Colors.red,
              ),
              Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Container(
                    width: 100,
                    height: 100,
                    color: Colors.yellow,
                  ),
                  Container(
                    width: 100,
                    height: 100,
                    color: Colors.orange,
                  ),
                ],
              ),
              Container(
                width: 100,
                // height: double.infinity,
                color: Colors.blue,
              ),
            ],
          ),
        )
    );
  }
}
