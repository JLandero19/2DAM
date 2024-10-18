import 'package:flutter/material.dart';

class EjercicioLayout3 extends StatefulWidget {
  const EjercicioLayout3({super.key});

  @override
  State<EjercicioLayout3> createState() => _EjercicioLayout3State();
}

class _EjercicioLayout3State extends State<EjercicioLayout3> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: Colors.green,
        body: SafeArea(
          child: Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Column(
                children: [
                  // Dado 1
                  Container(
                    width: 100,
                    height: 100,
                    color: Colors.grey[100],
                    child: Column(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          point(),
                        ],
                    ),
                  ),
                  // Dado 2
                  Container(
                    width: 100,
                    height: 100,
                    color: Colors.grey[200],
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        Column(
                          verticalDirection: VerticalDirection.up,
                          children: [
                            point(),
                          ],
                        ),
                        Column(
                          children: [
                            point(),
                          ],
                        )
                      ],
                    ),
                  ),
                  // Dado 3
                  Container(
                    width: 100,
                    height: 100,
                    color: Colors.grey[300],
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        Column(
                          verticalDirection: VerticalDirection.up,
                          children: [
                            point(),
                          ],
                        ),
                        Column(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            point(),
                          ],
                        ),
                        Column(
                          children: [
                            point(),
                          ],
                        )
                      ],
                    ),
                  ),
                  // Dado 4
                  Container(
                    width: 100,
                    height: 100,
                    color: Colors.grey[400],
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        Column(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            point(),
                            point()
                          ],
                        ),
                        Column(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            point(),
                            point()
                          ],
                        )
                      ],
                    ),
                  ),
                  // Dado 5
                  Container(
                    width: 100,
                    height: 100,
                    color: Colors.grey[500],
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        Column(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            point(),
                            point()
                          ],
                        ),
                        Column(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            point()
                          ],
                        ),
                        Column(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            point(),
                            point()
                          ],
                        )
                      ],
                    ),
                  ),
                  // Dado 6
                  Container(
                    width: 100,
                    height: 100,
                    color: Colors.grey[600],
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        Column(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            point(),
                            point(),
                            point()
                          ],
                        ),
                        Column(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            point(),
                            point(),
                            point()
                          ],
                        )
                      ],
                    ),
                  ),
                ],
              ),

            ],
          ),
        )
    );
  }

  Widget point() {
    return Container(
      margin: const EdgeInsets.all(10),
      width: 10,
      height: 10,
      color: Colors.black,
    );
  }
}