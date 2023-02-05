package cajero;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

/**
 * Fecha de entrega jueves 09 de febrero a las 22 h.
 * Entrega: archivo .java
 * Impresión de pantalla: menú, último movimiento antes de salir.
 * 
 * Comenzar con $10,000.00
 * 
 * Hacer un menú cajero automático
 *  Opción 1: retirar dinero (restar la cantidad)
 *            - No retirar una cantidad mayor a la disponible.
 *            - Mostrar cantidad disponible a retirar
 *            - No se puede retirar más de $6,000
 *            - Solo retirar múltiplos de 50 al 100
 *  Opción 2: hacer depósitos
 *              - Mostrar nuevo menú
 *              ¿Dónde deseo depositar?
 *              opción 1: cuenta de cheques (sumar la cantidad a nuestra cuenta)
 *                  -Solo se puede depositar en múltiplos de 50 y 100
 *              opcion 2: tarjeta de crédito (restar la cantidad)
 *                  - hacer pago de $200.10
 *                  - No retirar una cantidad mayor a la disponible.
 *              opción 3: cuenta de terceros (restar la cantidad)
 *                  - transferencia $666.22
 *                  - No retirar una cantidad mayor a la disponible.
 *  Opción 3: estado de cuenta (indicar la cantidad disponible)
 *  Opción 4: quejas (No te contestas y termina la sesión)
 *  Opción 5: último movimiento
 *              - fecha YYYY/MM/DD y hora y último movimiento realizado
 *              - ej. 2023/02/03 12:01:23 h. Retiro de $550.00
 *                ej. 2023/02/03 12:05:01 h. Depósito de $800.00
 *  opción 7: hablar con un asesor (enviar mensaje que no es horario de atención)
 *  Opción 9: salir del cajero
 *  Opción desconocida: indica el error y vuelve a preguntar,
 *                  - Si se equivoca 3 veces seguidas, termina la sesión.
 */

public class Cajero {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int opcion = 0;
		double dinero = 10000;
		ArrayList<String> guardarOperacion = new ArrayList<String>();
		int contadorErrores = 0;

		while (opcion != 9 && contadorErrores <= 3) {
			System.out.println("*=*=*=*=	BIENVENIDO =*=*=*=*");
			System.out.println("1. Retirar dinero");
			System.out.println("2. Hacer deposito");
			System.out.println("3. Estado de cuenta");
			System.out.println("4. Quejas");
			System.out.println("5. Ultimo movimiento");
			System.out.println("7. Hablar con un asesor");
			System.out.println("9. Salir");
			System.out.print("Introduce una opción: ");

			opcion = scanner.nextInt();

			switch (opcion) {
			case 1:
				contadorErrores=0;
				System.out.println("Dinero disponible: $" + dinero);
				System.out.println("Introduce la cantidad a retirar (en múltiplos de 50): ");
				int cantidad = scanner.nextInt();
				if (cantidad % 50 != 0) {
					System.out.println("La cantidad debe ser un múltiplo de 50.");
				} else if (cantidad > 6000) {
					System.out.println("No puedes realizar un retiro mayor a 6000");
				} else if (dinero - cantidad < 0) {
					System.out.println("No tienes fondos suficientes en tu cuenta.");
				} else {
					dinero -= cantidad;
					System.out.println("Retiro exitoso. Dinero disponible: $" + dinero);
					guardarOperacion.add(
							new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()) + " - Retiro: " + cantidad);
				}
				break;
			case 2:
				contadorErrores=0;
				System.out.println("Donde desea depositar?");
				System.out.println("1. Cuenta de cheques");
				System.out.println("2. Tarjeta de credito");
				System.out.println("3. Cuenta de terceros");
				System.out.print("Introduce una opción: ");
				int opcionDepositar = scanner.nextInt();
				switch (opcionDepositar) {
				case 1:
					System.out.print("Introduce la cantidad a depositar (múltiplos de 50 y 100): ");
					int cantidadSumar = scanner.nextInt();
					if (cantidadSumar % 50 != 0) {
						System.out.println("La cantidad debe ser un múltiplo de 50 o 100.");
					} else {
						dinero += cantidadSumar;
						System.out.println("Deposito exitoso. Dinero disponible: $" + dinero);
						guardarOperacion.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())
								+ " - Deposito: $" + cantidadSumar + " a cuenta de cheques.");
					}
					break;
				case 2:
					if (dinero - 200.10 < 0) {
						System.out.println("No puedes tener una cantidad negativa en tu cuenta.");
					} else {
						dinero -= 200.10;
						System.out.println("Pago exitoso por $200.10. Dinero disponible: $" + dinero);
						guardarOperacion.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())
								+ " - Deposito: " + 200.10 + " a tarjeta de credito");
					}
					break;
				case 3:
					if (dinero - 666.22 < 0) {
						System.out.println("No puedes tener una cantidad negativa en tu cuenta.");
					} else {
						dinero -= 666.22;
						System.out.println("Pago exitoso por $666.22. Dinero disponible: " + dinero);
						guardarOperacion.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())
								+ " - Deposito: " + 666.22 + " a cuenta de terceros");
					}
					break;
				default:
					System.out.println("Opción inválida.");
					break;
				}
				break;
			case 3:
				contadorErrores=0;
				System.out.println("La cantidad disponible en su cuenta es de: $" + dinero);
				break;
			case 4:
				contadorErrores=0;
				System.out.println("El sistema no está disponible para quejas, intente mas tarde");
				break;
			case 5:
				contadorErrores=0;
				System.out.println("Seleccionaste la opción 5");
				System.out.println(guardarOperacion.get(guardarOperacion.size() - 1));

				break;
			case 7:
				contadorErrores=0;
				System.out.println("El horario de atencion es de 18:00 a 18:01");
				break;
			case 9:
				System.out.println("Adiós!");
				break;
			default:
				contadorErrores++;
				System.out.println("Opción inválida. Intenta de nuevo.");

			}
			if (contadorErrores > 3) {
				System.out.println("\nDemasiados errores. El programa ha finalizado");
			}
		}
	}
}
