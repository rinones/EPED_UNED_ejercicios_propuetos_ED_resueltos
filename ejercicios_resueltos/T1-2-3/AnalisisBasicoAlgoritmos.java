/**
 * 1ero Definir el tamaño del problema, que sera n
 * 
 * 2o Calculcular el coste de cada una de las funciones e ir acumulándolo
 * -------------------------------------
 * + Operaciones básicas, no dependen del tamaño del problema.
 * > Operaciones de entrada y salida (return, print simple)
 * > Asignaciones (int x = 5, x = y)
 * > Expresiones (aritméticas: +,-,*,/,%; comparaciones: ==,!=,<,>; lógicas:
 * &&,||,!)
 * 
 * Coste constante O(1), el coste de una secuencia de sentencias viene definido
 * por la sentencia mas costosa.
 * -------------------------------------
 * + Sentencias condicionales
 * > if(e) {S1} else {S2}
 * 
 * El coste de una sentencia condicional if será el máximo entre calcular la
 * expresión e, ejecutar S1 y ejecutar S2.
 * Esto se debe a que, en tiempo de ejecución, solo se ejecuta uno de los
 * bloques S1 o S2, pero en análisis de coste asintótico se considera el caso
 * peor, es decir, el bloque de mayor coste.
 * 
 * > switch(e) { case V1 : S1 (e;S1); case V2 : S2 (e;S2); case Vn : Sn (e;Sn)}
 * 
 * El coste de una sentencia condicional switch es una generalización de la
 * sentencia condicional if.
 * El coste será el máximo entre calcular la expresión e y ejecutar todos los
 * posibles bloques S1, S2, ..., Sn.
 * Nuevamente, se toma el bloque de mayor coste por análisis del caso peor.
 * -------------------------------------
 * + Bucles
 * > for(ini;e;inc) {S}
 * - En cada vuelta del bucle (n vueltas) se
 * * Calculara e
 * * Si e es True ejecuta S y inc
 * * Si e es False sale
 * 
 * El coste sera el máximo entre calcular la inic y multiplicar el numero de
 * vueltas por el coste de calcular la expresión, el cuerpo del bucle y el
 * incremento.
 * 
 * > while(e) {S}
 * - En cada vuelta del bucle (n vueltas) se
 * * Calculara e
 * * Si e es True ejecuta S
 * * Si e es False sale
 * 
 * El coste sera el máximo entre multiplicar el numero de vueltas por el coste
 * de calcular la expresión y ejecutar el cuerpo del bucle.
 * 
 * > do {S} while (e)
 * - Ejecuta S al menos una vez
 * - En cada vuelta del bucle (n vueltas) se
 * * Calculara e
 * * Si e es True ejecuta S
 * * Si e es False sale
 * 
 * El coste sera el máximo entre multiplicar el numero de vueltas por el coste
 * de calcular la expresión y ejecutar el cuerpo del bucle.
 * -------------------------------------
 * + Llamadas a subprogramas
 * > f(e1, e2, e3, ..., en)
 * - Calcular e1, e2, e3, ..., en
 * - Ejecutar el cuerpo del subprograma f
 * 
 * El coste sera el maximo entre calcular los parametros del subprograma y
 * ejecutar el subprograma.
 * -------------------------------------
 * +++++++++++++++++++++++++++++++++++++
 * -------------------------------------
 * + Resolución de recurrencias mediante sustración
 * > En una recursión lineal (solo hay una llamada recursiva), se realizará un
 * numero de llamadas igual a n. El coste será la suma de:
 * - n multiplicado por el coste de las operaciones no recursivas que se
 * realizan en cada llamada, más el de los casos base.
 * 
 * > En una recursión multiple, se realizará un numero de llamadas igual a a^(n
 * div b). El coste sera:
 * - Las suma del coste de las operaciones no recursivas que se realizan en cada
 * llamada, más el coste de los casos base, multiplicado
 * por el numero de llamadas recursivas a^(n div b)
 * -------------------------------------
 * + Resolución de recurrencias mediante división
 * > En una recursión lineal (solo hay una llamada recursiva), se realizará un
 * numero de llamadas igual a log_b(n). El coste será la suma de:
 * - log_b(n) multiplicado por el coste de las operaciones no recursivas que se
 * realizan en cada llamada, más el de los casos base.
 * 
 * > En una recursión multiple, se realizará un numero de llamadas igual a
 * n^(log_b(a)). El coste sera:
 * - Las suma del coste de las operaciones no recursivas que se realizan en cada
 * llamada, más el coste de los casos base, multiplicado
 * por el numero de llamadas recursivas n^(log_b(a)).
 * -------------------------------------
 */

public class AnalisisBasicoAlgoritmos {

// 1. Calcular el coste del Algoritmo de Euclides:

public int mcd(int A, int B) {
if ( B == 0 ) { return (A); }
else { return (mcd(B, A % B)); }
}

/*
 * ANÁLISIS CORRECTO:
 * ✓ El tamaño del problema se define en base al valor de B (CORRECTO - tomamos el menor de los dos números)
 * ✓ El coste de las operaciones básicas es constante O(1) (CORRECTO)
 * ✓ El coste de la sentencia condicional es constante O(1) (CORRECTO)
 * ✓ El coste del caso base y del caso no recursivo es constante O(1) (CORRECTO)
 * ✓ a = 1 porque solamente hay una recursividad (CORRECTO - recursión lineal)
 * 
 * ANÁLISIS INCORRECTO:
 * ✗ b = B (INCORRECTO - El factor de reducción NO es constante)
 * ✗ La recurrencia se reduce por división (PARCIALMENTE CORRECTO - sí se reduce, pero no con factor constante)
 * ✗ O(log_B(n) * O(1) + O(1)) = O(log_B(n)) (INCORRECTO - no se puede aplicar la fórmula estándar)
 * 
 * EXPLICACIÓN DEL PROFESOR:
 * - El factor de reducción no es constante de una llamada a otra
 * - No se pueden aplicar las recurrencias estándar para calcular el coste
 * - Sin embargo, cada DOS llamadas el parámetro menor se reduce al menos a la mitad
 * - Tomando el menor de los números como tamaño del problema, se reduce por división con factor ≥ 2
 * - El número de llamadas es del orden del doble del logaritmo en base 2 de y
 * - RESULTADO FINAL: O(log y) donde y es el menor de los dos números
 * 
 * MÉTODO CORRECTO - EXPLICACIÓN DETALLADA:
 * 
 * 1. PROPIEDADES MATEMÁTICAS CLAVE:
 *    - Para cualquier x > y: x mod y < y (por definición del módulo)
 *    - Para cualquier x > y: x mod y < x/2 (propiedad matemática)
 * 
 * 2. EJEMPLO PASO A PASO:
 *    Supongamos mcd(48, 18):
 *    Llamada 1: mcd(48, 18) → mcd(18, 48%18) = mcd(18, 12)
 *    Llamada 2: mcd(18, 12) → mcd(12, 18%12) = mcd(12, 6)
 *    Llamada 3: mcd(12, 6)  → mcd(6, 12%6)  = mcd(6, 0)
 *    Llamada 4: mcd(6, 0)   → return 6
 * 
 * 3. ANÁLISIS DE LA REDUCCIÓN:
 *    - Cada DOS llamadas el segundo parámetro (menor) se reduce AL MENOS a la mitad
 *    - Llamadas 1→3: el menor pasa de 18 → 6 (se reduce a 18/3 < 18/2)
 *    - En el peor caso, cada 2 llamadas se reduce exactamente a la mitad
 * 
 * 4. SECUENCIA GENERAL:
 *    (x,y) → (y, x mod y) → (x mod y, y mod (x mod y))
 *           ↑                              ↑
 *      1ra llamada                    2da llamada
 *    
 *    Donde: y mod (x mod y) < y/2 (en el peor caso)
 * 
 * 5. CÁLCULO DEL COSTE:
 *    - Si cada 2 llamadas el problema se reduce por factor ≥ 2
 *    - Número máximo de pares de llamadas: log₂(y)
 *    - Número total de llamadas: 2 * log₂(y)
 *    - Por tanto: O(2 * log₂(y)) = O(log y)
 */


// 2. Calcular el coste del algoritmo “multiplicación rusa”:

public int mult_rusa(int A, int B) {
if( A == 1 ){ return (B); }
if ( A%2 != 0 ) { return(B+mult_rusa( A/2 , B*2)); }
else { return(mult_rusa( A/2 , B*2)); }
}

/*
 * El tamaño del problema va definido por el valor de A
 * El coste de las operaciones basicas es constante O(1)
 * El coste del caso base es constante O(1)
 * 
 * El coste del caso recursivo es constante O(1)
 *      ❌ Incorrecto. El coste de cada llamada recursiva es constante, pero el coste
 *              total de la recursión no lo es.
 *      🔄 Corrección: El coste de las operaciones no recursivas en cada llamada
 *              recursiva es constante O(1), pero el coste total es logarítmico respecto a A.
 * 
 * El coste de las expresiones condicionales será el maximo entre calcular
 *      la expresión y ejecutar el cuerpo del condicional, constante en este caso O(1)
 * a = 1
 * b = 2
 * 
 * O(log_2(A) * O(1) + O(1)) = O(log_2(A)) = O(log(A))
 */


// 3. Calcular el coste del algoritmo “potencia”:

public int potencia(int B, int N) {
if( N == 0 ){ return (1); }
else { return(B*potencia(B,N-1)); }
}

/*
 * El tamaño del problema va definido por el valor de N
 * El coste de las operaciones basicas es constante O(1)
 * El coste del caso base es constante O(1)
 * El coste de las operaciones no recursivas en cada llamada recursiva es constante O(1)
 * El coste de las expresiones condicionales será el maximo entre calcular
 *      la expresión y ejecutar el cuerpo del condicional, constante en este caso O(1)
 * a = 1
 * b = 1
 * 
 * O(N * O(1) + O(1)) = O(N)
 */




// 4. Calcular el coste del algoritmo “potencia optimizada”:

public int potencia2(int B, int N) {
if( N == 0 ){ return (1); }
int rec = potencia2(B,N/2);
if ( N%2 == 0 ) { return(rec*rec); }
else { return(B*rec*rec); }
}

/*
 * El tamaño del problema va definido por el valor de N
 * El coste de las operaciones basicas es constante O(1)
 * El coste del caso base es constante O(1)
 * El coste de las operaciones no recursivas en cada llamada recursiva es constante O(1)
 * El coste de las expresiones condicionales será el maximo entre calcular
 *      la expresión y ejecutar el cuerpo del condicional, constante en este caso O(1)
 * a = 1 (rec se llama dos veces en cada sentencia; al no pasar parametros se considera recursividad lineal)
 * b = 2
 * 
 * O(log_2(N) * O(1) + O(1)) = O(log_2(N)) = O(log(N))
 */


// 5. Calcular el coste de invertir un número:

public int invertir(int n) { return invertirAux(0, n); }
public int invertirAux(int ac, int n) {
if (n == 0 ) { return ac; }
return invertirAux(ac*10+(n % 10), n / 10);
}

/*
 * El tamaño del problema va definido por el valor de n
 * El coste de las operaciones basicas es constante O(1)
 * El coste del caso base es constante O(1)
 * El coste de las operaciones no recursivas en cada llamada recursiva es
 *      constante O(1)
 * El coste de las expresiones condicionales será el maximo entre calcular
 *      la expresión y ejecutar el cuerpo del condicional, constante en este caso O(1)
 * a = 1
 * b = 10
 *
 * O(log_10(n) * O(1) + O(1)) = O(log_10(n))
 */

}