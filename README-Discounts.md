# Extensión de descuentos (README)

Este proyecto incluye un motor de reglas de descuento extensible. Este README explica cómo agregar nuevas reglas de descuento y cómo ejecutar las pruebas.

Conceptos clave
- `DiscountRule`: interfaz que define `supports(Producto)` y `apply(Producto, precioActual)`.
- `DiscountEngine`: servicio que aplica la primera regla específica válida y, si no hay, aplica la regla por defecto.

Agregar una nueva regla (pasos)
1. Crear una clase que implemente `cl.patrones.examen.discount.rules.DiscountRule`.
2. Anotar la clase con `@Component` (o registrarla como bean) para que Spring la descubra.
3. Implementar `supports` para indicar cuándo la regla aplica.
4. Implementar `apply` para transformar el precio actual y devolver el nuevo precio.

Ejemplo mínimo:

```java
@Component
public class MyRule implements DiscountRule {
    public boolean supports(Producto p) { return p.getSku()!=null && p.getSku().contains("SPECIAL"); }
    public double apply(Producto p, double precioActual) { return precioActual * 0.85; }
}
```

Buenas prácticas
- Hacer las reglas pequeñas y enfocadas (una responsabilidad cada una).
- Preferir reglas inmutables y sin estado para facilitar tests.
- Añadir tests unitarios que construyan instancias de `Producto` y verifiquen `DiscountEngine.calculateFinalPrice(...)`.

Comandos útiles
- Ejecutar tests:

```bash
./gradlew.bat clean test
```

- Ejecutar la aplicación:

```bash
./gradlew.bat bootRun
```

Contacto
- Si quieres, puedo añadir una plantilla para nuevos tests o un ejemplo de commit.
