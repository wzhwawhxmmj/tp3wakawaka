

# Escenario #
Contiene un
  * UEB

## Responsabilidades ##
  * Retornar un "calculador" , que busca el camino mas corto entre dos puntos (y retorna la próxima dirección)
  * Retornar un iterador de los ueb

# Piso #
Contiene una lista de
  * noJugador

## Responsabilidades ##
  * Retornar un iterador de los noJugador
  * Permitir el avance al Pacman y Fantasmas


# Pildora #
Conoce
  * Juego

## Responsabilidades ##
  * Modificar el juego al activarse (en este caso ordena poner los fantasmas azules)

# Fantasma #
Conoce
  * Pacman
  * Escenario

## Responsabilidades ##
  * Comportarse segun su inteligencia y las cosas que conoce
  * Activar: morir si esta azul, matar al pacman si no.

# Pacman #
Conoce
  * noJugador (por parametro)

## Responsabilidades ##
  * Activar el noJugador que se encuentre en su camino
  * Acumular el puntaje de lo que come
  * Moverse en la direccion actual, si puede
  * Cambiar su direccion si se lo ordenan

# Juego #
Instancia y conoce
  * Fantasma
  * Pacman
  * Tablero
  * Input
  * Graficador

## Responsabilidades ##
  * ?