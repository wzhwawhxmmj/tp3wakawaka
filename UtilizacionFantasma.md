# Introducción #

Este texto explica cómo se utilizan las instancias de la clase Fantasma y cómo deben construirse sus herederas.

# Utilización de los fantasmas #

Los fantasmas poseen un único método que debería llamar la clase que los haga actuar. El método 'comportarse()' hace que el fantasma tomé decisiones dependiendo del estado en el que esté, de esta forma, lo único que debe hacerse para modificar su comportamiento es cambiar su estado a 'azul/asustado' o a 'modo separación' activando cada modo con los métodos 'activarModoSeparacion' y 'volverAzul'.

## Modos ##

### Modo Azul ###

_fantasma.volverAzul();_
_fantasma.volverNormal();_

Los fantasmas deambulan al azar por el escenario y, si mueren, regresan a la casa y se quedan encerrados ahí por un tiempo. El modo azul desactiva el modo separación automáticamente y no se activa si el fantasma está encerrado en la casa.

### Modo Separación ###

_fantasma.activarModoSeparación();_
_fantasma.desactivarModoSeparación();_

Al activarse el modo separación el fantasma se dirige a la posición que se le haya enviado por parámetro en el constructor para tal fin y, al llegar, comienza a realizar movimientos al azar hasta que se desactive el modo separación. Es importante recalcar que el modo separación puede desactivarse en cualquier momento y el fantasma volverá a su estrategia normal.

## Ejemplo ##

fantasma.comportarse(); -> Estrategia propia.

fantasma.modoAzul(); -> Activa el modo azul.

fantasma.comportarse(); -> Se comporta de acuerdo a estar azul.

fantasma.activarModoSeparacion(); -> Activa el modo separación (desactiva automáticamente el modo azul y coloca al fantasma en modo separación).

fantasma.comportarse(); -> El fantasma actúa en modo separación.

fantasma.desactivarModoSeparacion(); -> En el siguiente comportarse() realiza su estrategia propia nuevamente.

---

# Herencia de los fantasmas #

Para construir fantasmas nuevos existen algunos métodos protegidos de la clase Fantasma que pueden resultarle útiles a los hijos, pero dejando de lado esto, la idea es que los hijos solo tengan que redefinir el método 'estrategizar()'

con su estrategia propia para tener su fantasma listo y dejar que el método comportarse de Fantasma se ocupe de lo demás.
Los métodos protegidos disponibles son:

_movimientoAlAzar()_ : Realiza un paso al azar en una dirección válida.

_moverHacia(Posicion)_ : Mueve al fantasma a la posición indicada, si no es válida arroja PosicionIlegalException.

_moverHacia(Direccion)_ : Mueve al fantasma un paso en la dirección indicada, si no es válida arroja PosicionIlegalException.

_retornarACasa_: Da un paso en la dirección de la casa.

_montarGuardiaHorizontal_ : Da un paso hacia la derecha hasta chocarse contra un obstáculo, si esto sucede, comienza a dar un paso hacia la izquierda hasta chocar con un obstáculo y comienza a ir nuevamente hacia la derecha.