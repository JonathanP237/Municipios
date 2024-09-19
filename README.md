# README: Proyecto de Cálculo de la Ruta Más Corta entre Municipios o Ciudades

## Descripción

Este proyecto tiene como objetivo calcular la ruta más corta entre dos municipios o ciudades utilizando diferentes algoritmos de búsqueda de caminos. El usuario puede seleccionar tanto la ciudad de origen como la de destino, además de elegir el algoritmo que desea usar para el cálculo de la ruta. Los algoritmos disponibles son:

- Dijkstra
- Algoritmo Avaro (Greedy)
- A\*

El sistema muestra en un mapa la ruta calculada, incluyendo los lugares por los que pasa, y calcula la distancia total del trayecto.

## Características

- **Selección de ciudades**: Los usuarios pueden seleccionar tanto la ciudad de origen como la ciudad de destino desde un menú desplegable.
- **Elección del algoritmo**: Los usuarios pueden elegir entre tres algoritmos de cálculo de rutas (Dijkstra, Avaro y A\*) para determinar el camino más corto.
- **Visualización en el mapa**: La ruta calculada se muestra en un mapa interactivo que permite ver los puntos por los que pasa la ruta.
- **Distancia total**: Se muestra la distancia total del trayecto calculado entre el origen y el destino.

## Requisitos

- Java 8 o superior
- Maven para la gestión de dependencias
- API de mapas (como Google Maps API o similar) para la visualización de la ruta en el mapa
- Biblioteca gráfica (como JavaFX o similar) para la interfaz de usuario

## Instalación

1. **Clonar el repositorio**:

   ```bash
   git clone https://github.com/JonathanP237/Municipios.git
   ```

2. **Instalar dependencias**: Asegúrate de tener Maven instalado. Luego ejecuta el siguiente comando:
   ```bash
   mvn install
   ```

## Uso

1. **Seleccionar ciudades**: En la vista principal, selecciona la ciudad de origen y la ciudad de destino desde los menús desplegables.
2. **Seleccionar algoritmo**: Elige uno de los algoritmos de búsqueda disponibles (Dijkstra, Avaro o A\*) en la opción correspondiente.
3. **Calcular ruta**: Presiona el botón "Calcular Ruta" para que el sistema determine y muestre la ruta más corta en el mapa.
4. **Visualización en mapa**: La ruta calculada aparecerá en el mapa junto con los lugares intermedios por los que pasa.
5. **Distancia total**: Se mostrará la distancia total de la ruta calculada debajo del mapa.

## Algoritmos

### 1. **Dijkstra**

Este algoritmo encuentra la ruta más corta desde un nodo origen hasta todos los demás nodos, asegurando que la ruta más corta entre dos nodos se encuentre de manera eficiente.

### 2. **Avaro (Greedy)**

El algoritmo avaro siempre selecciona el camino más prometedor en cada paso, sin tener en cuenta si esa elección conduce a la solución óptima global.

### 3. **A\***

El algoritmo A\* utiliza una combinación de búsqueda por costo y heurísticas para encontrar la ruta más eficiente. Es especialmente útil cuando se tiene información adicional sobre las distancias estimadas.
