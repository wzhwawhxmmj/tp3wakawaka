# Funcionamiento #

El algoritmo en si, de un principio fue pensado como un recorrido completo del tablero,  usando recursividad, similar a los recorridos de arboles binarios que se ven en algoritmos 2.
Se van almacenando temporalmente los pasos, cuando se encuentra al Pacman se almacena el recorrido en otra variable, si ya hay uno almacenado y el ultimo es mejor, se sustituye por el anterior.

# Optimizaciones/Necesidades #

El algoritmo, necesitaba de una optimizacion, que mas que una optimizacion era una necesidad, sin eso el algoritmo funcionaria infinitamente. Esta se trataba de que, cada vez que daba un paso nuevo, se aseguraba que el paso no habia sido hecho en el recorrido actual.
Si esto no se hace, el recorrido podria ser infinito, dando vueltas y vueltas, porque el tablero tiene muchas comunicaciones,  no es como un arbol que tiene un fin.


Otra optimizacion, menos importante pero que economizaba bastante, se me ocurrio despues. Esta se trata de que antes de hacer un paso nuevo, se asegure que no esta haciendo mas pasos que el camino mas corto almacenado.
Si estaria pasandose de esa cantidad, estaria buscando un camino en vano, ya que la idea siempre es seguir buscando para encontrar un camino mas corto al anterior.


# Historia, problemas y soluciones #

El calculador surgió como necesidad para poder decirles a los fantasmas a donde dirigirse, instante a instante.
En un principio surgió como una clase estática, Utilitario, pero solo fue para no molestar al integrante que estaba haciendo la clase Escenario, después de complicarme mucho, me di cuenta que estaba perdiendo tiempo y le saque ese atributo.
Quedo entonces como una clase que iba a ser parte del escenario en el futuro, o bien que iba a ser instanciada por este, como si fuera un iterador.


La próxima decisión, fue que sea instanciado por el escenario, esto debido a que habia muchas variables que debían inicializarse al comienzo del algoritmo, entonces aproveche que se inicialize al usar el constructor.
Lo malo de esto era que la instancia se podía usar solo una vez, a la segunda vez ya daria datos inválidos, porque no borraba todo lo que había almacenado del llamado anterior.

Esto por el momento se paso por alto, y se siguió con otras cosas.


Bueno, básicamente eso era el algoritmo.
Hubo un problema al principio con las instancias de las posiciones y la recursividad, nunca llegamos a entender bien porque, pero se soluciono mandando una instancia nueva de la posición en cada llamado nuevo.

Luego se intento solucionar esto, primero usando un método para reinicializar. Pero luego me di cuenta que este método no se estaba ejecutando , debido a la recursividad. Entonces lo que hice fue hacer que el método que se llamaba desde el exterior, llamaba a otro método interno.
Pero por alguna razón seguía sin funcionar, finalmente implemente algo medio extraño, que lo que hacia era hacer una instancia nueva y ejecutar a partir de ahí.

Luego, no hubo mas problemas relevantes, hasta el momento de testear gráficamente.

En ese momento, nos dimos cuenta de que el algoritmo estaba consumiendo muchos recursos, a tal punto que hacia muy lento el juego.
Lo primero que hice para intentar solucionarlo, fue eliminar la cantidad innecesaria de instancias que tenia, cada fantasma llamaba una instancia nueva para cada movimiento, aparte el método de calcular, llamaba a una instancia nueva también.
No me costo mucha modificación hacer que todos usen la misma instancia. Y pude implementar finalmente que las variables se re-inicializen sin problemas.

Pero esto no soluciono el problema.
Trate de estimar que era lo que estaba pasando, y evidentemente el problema era en que bajo ciertas busquedas estaba recorriendo excesivamente el tablero sin encontrar al pacman. Luego de pensar en muchas optimizaciones, se me ocurrio una bastante simple, basandome en el problema.
Lo que hice fue limitar la cantidad de "pisadas" de un recorrido a una cantidad razonable, por ejemplo en el tablero del nivel 1 eran 60. Total nunca va a haber un camino entre el pacman y el fantasma que sea mayor a eso.

Luego lo implemente un poco mas complejo, ya que los "puntos de escape" de los fantasmas era la zona mas distante, entonces use la distancia entre esos puntos como cantidad de pasos máxima.


Ahí entonces si, el calculador fué lo que es ahora.