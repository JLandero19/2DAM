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
                  Container(
                    width: 100,
                    height: 100,
                    color: Colors.grey[100],
                    child: Column(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          Container(
                            width: 10,
                            height: 10,
                            color: Colors.black,
                          )
                        ],
                    ),
                  ),
                  Container(
                    width: 100,
                    height: 100,
                    color: Colors.grey[200],
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        Column(
                          children: [
                            Container(
                              margin: const EdgeInsets.all(10),
                              width: 10,
                              height: 10,
                              color: Colors.black,
                            )
                          ],
                        ),
                        Column(
                          verticalDirection: VerticalDirection.up,
                          children: [
                            Container(
                              margin: const EdgeInsets.all(10),
                              width: 10,
                              height: 10,
                              color: Colors.black,
                            )
                          ],
                        )
                      ],
                    ),
                  ),
                  Container(
                    width: 100,
                    height: 100,
                    color: Colors.grey[300],
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        Column(
                          children: [
                            Container(
                              margin: const EdgeInsets.all(10),
                              width: 10,
                              height: 10,
                              color: Colors.black,
                            )
                          ],
                        ),
                        Column(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            Container(
                              margin: const EdgeInsets.all(10),
                              width: 10,
                              height: 10,
                              color: Colors.black,
                            )
                          ],
                        ),
                        Column(
                          verticalDirection: VerticalDirection.up,
                          children: [
                            Container(
                              margin: const EdgeInsets.all(10),
                              width: 10,
                              height: 10,
                              color: Colors.black,
                            )
                          ],
                        )
                      ],
                    ),
                  ),
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
                            Container(
                              margin: const EdgeInsets.all(10),
                              width: 10,
                              height: 10,
                              color: Colors.black,
                            ),
                            Container(
                              margin: const EdgeInsets.all(10),
                              width: 10,
                              height: 10,
                              color: Colors.black,
                            )
                          ],
                        ),
                        Column(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            Container(
                              margin: const EdgeInsets.all(10),
                              width: 10,
                              height: 10,
                              color: Colors.black,
                            ),
                            Container(
                              margin: const EdgeInsets.all(10),
                              width: 10,
                              height: 10,
                              color: Colors.black,
                            )
                          ],
                        )
                      ],
                    ),
                  ),
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
                            Container(
                              margin: const EdgeInsets.all(10),
                              width: 10,
                              height: 10,
                              color: Colors.black,
                            ),
                            Container(
                              margin: const EdgeInsets.all(10),
                              width: 10,
                              height: 10,
                              color: Colors.black,
                            )
                          ],
                        ),
                        Column(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            Container(
                              margin: const EdgeInsets.all(10),
                              width: 10,
                              height: 10,
                              color: Colors.black,
                            )
                          ],
                        ),
                        Column(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            Container(
                              margin: const EdgeInsets.all(10),
                              width: 10,
                              height: 10,
                              color: Colors.black,
                            ),
                            Container(
                              margin: const EdgeInsets.all(10),
                              width: 10,
                              height: 10,
                              color: Colors.black,
                            )
                          ],
                        )
                      ],
                    ),
                  ),
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
                            Container(
                              margin: const EdgeInsets.all(10),
                              width: 10,
                              height: 10,
                              color: Colors.black,
                            ),
                            Container(
                              margin: const EdgeInsets.all(10),
                              width: 10,
                              height: 10,
                              color: Colors.black,
                            ),
                            Container(
                              margin: const EdgeInsets.all(10),
                              width: 10,
                              height: 10,
                              color: Colors.black,
                            )
                          ],
                        ),
                        Column(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            Container(
                              margin: const EdgeInsets.all(10),
                              width: 10,
                              height: 10,
                              color: Colors.black,
                            ),
                            Container(
                              margin: const EdgeInsets.all(10),
                              width: 10,
                              height: 10,
                              color: Colors.black,
                            ),
                            Container(
                              margin: const EdgeInsets.all(10),
                              width: 10,
                              height: 10,
                              color: Colors.black,
                            )
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
}