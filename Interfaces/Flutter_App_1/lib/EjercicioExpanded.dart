import 'package:flutter/material.dart';

class EjercicioExpanded extends StatefulWidget {
  const EjercicioExpanded({super.key});

  @override
  State<EjercicioExpanded> createState() => _EjercicioExpandedState();
}

class _EjercicioExpandedState extends State<EjercicioExpanded> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
          child: Column(
            children: [
              Expanded(
                child: Row(
                  children: [
                    Expanded(
                      child: Column(
                        children: [
                          Expanded(
                            flex: 1,
                            child: Container(
                              color: Colors.blue,
                            ),
                          ),
                          Expanded(
                            flex: 2,
                            child: Container(
                              color: Colors.green,
                            ),
                          )
                        ],
                      )
                    ),
                    Expanded(
                        child: Container(
                          color: Colors.yellow,
                        )
                    )
                  ],
                ),
              ),
              Expanded(
                child: Container(
                  color: Colors.red,
                ),
              )
            ],
          )
      ),
    );
  }
}
