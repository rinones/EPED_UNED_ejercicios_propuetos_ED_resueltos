/**
Para cumplir con lo especificado en el ejercicio cuatro, basta con añadir la siguiente precondición a las funciones 
insert(), push() y queue() de las interfaces ListIF<E>, StackIF<E> y QueueIF<E> respectivamente:

@pre: maxSize() >= size() + 1

De este modo, solo será posible introducir un nuevo elemento si el tamaño resultante no supera el límite máximo establecido, 
ya que dicho límite debe ser mayor o igual que el nuevo tamaño de la secuencia.
*/