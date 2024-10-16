import 'package:flutter/material.dart';

class EjemploWidget extends StatelessWidget {
  const EjemploWidget({super.key});

  @override
  Widget build(BuildContext context) {
    // return Image.asset("assets/images/image-200x300.jpg");
    // return Image.network("https://picsum.photos/id/237/200/300");
    return Center(
      child: Image.network(
        "https://picsum.photos/200/300",
        scale: 1.0,
        fit: BoxFit.none,
        alignment: Alignment.center,
      ),
    );

    // return Opacity(
    //   opacity: 1.0,
    //   child: Image.network(
    //     "https://picsum.photos/id/237/200/300",
    //     scale: 1.0,
    //     fit: BoxFit.none,
    //     alignment: Alignment.center,
    //   ),
    // );
  }
}

