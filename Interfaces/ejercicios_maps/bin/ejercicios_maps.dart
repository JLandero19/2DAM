// import 'package:ejercicios_maps/ejercicios_maps.dart' as ejercicios_maps;

void main(List<String> arguments) {
  Map<String, String> misDatos = {
    'profesion': 'Estudiante DAM',
    'pais': 'España',
    'ciudad': 'Ayamonte'
  };

  misDatos.forEach((key, value) => print('$key -> $value'));

  misDatos['pais'] = 'Portugal';
  misDatos['ciudad'] = 'Lisboa';

  print("\nMap modificado ...");
  
  // Para probar el .forEach con {}
  misDatos.forEach((key, value) {
    print('$key -> $value');
  });

  

}
