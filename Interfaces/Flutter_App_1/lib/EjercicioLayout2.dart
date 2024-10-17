import 'package:flutter/material.dart';

class EjercicioLayout2 extends StatefulWidget {
  const EjercicioLayout2({super.key});

  @override
  State<EjercicioLayout2> createState() => _EjercicioLayout2State();
}

class _EjercicioLayout2State extends State<EjercicioLayout2> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: Colors.green,
        body: SafeArea(
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Column(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Container(
                    width: 100,
                    height: 100,
                    color: Colors.red,
                  ),
                  Container(
                    width: 100,
                    height: 100,
                    color: Colors.red,
                  ),
                ],
              ),
              Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Container(
                    width: 100,
                    height: 100,
                    color: Colors.yellow,
                  ),
                ],
              ),
              Column(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Container(
                    width: 100,
                    height: 100,
                    color: Colors.blue,
                  ),
                  Container(
                    width: 100,
                    height: 100,
                    color: Colors.blue,
                  ),
                ],
              ),
            ],
          ),
        )
    );
  }
}