package cajero;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

/**
 * Fecha de entrega jueves 09 de febrero a las 22 h.
 * Entrega: archivo .java
 * Impresi�n de pantalla: men�, �ltimo movimiento antes de salir.
 * 
 * Comenzar con $10,000.00
 * 
 * Hacer un men� cajero autom�tico
 *  Opci�n 1: retirar dinero (restar la cantidad)
 *            - No retirar una cantidad mayor a la disponible.
 *            - Mostrar cantidad disponible a retirar
 *            - No se puede retirar m�s de $6,000
 *            - Solo retirar m�ltiplos de 50 al 100
 *  Opci�n 2: hacer dep�sitos
 *              - Mostrar nuevo men�
 *              �D�nde deseo depositar?
 *              opci�n 1: cuenta de cheques (sumar la cantidad a nuestra cuenta)
 *                  -Solo se puede depositar en m�ltiplos de 50 y 100
 *              opcion 2: tarjeta de cr�dito (restar la cantidad)
 *                  - hacer pago de $200.10
 *                  - No retirar una cantidad mayor a la disponible.
 *              opci�n 3: cuenta de terceros (restar la cantidad)
 *                  - transferencia $666.22
 *                  - No retirar una cantidad mayor a la disponible.
 *  Opci�n 3: estado de cuenta (indicar la cantidad disponible)
 *  Opci�n 4: quejas (No te contestas y termina la sesi�n)
 *  Opci�n 5: �ltimo movimiento
 *              - fecha YYYY/MM/DD y hora y �ltimo movimiento realizado
 *              - ej. 2023/02/03 12:01:23 h. Retiro de $550.00
 *                ej. 2023/02/03 12:05:01 h. Dep�sito de $800.00
 *  opci�n 7: hablar con un asesor (enviar mensaje que no es horario de atenci�n)
 *  Opci�n 9: salir del cajero
 *  Opci�n desconocida: indica el error y vuelve a preguntar,
 *                  - Si se equivoca 3 veces seguidas, termina la sesi�n.
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
			System.out.print("Introduce una opci�n: ");

			opcion = scanner.nextInt();

			switch (opcion) {
			case 1:
				contadorErrores=0;
				System.out.println("Dinero disponible: $" + dinero);
				System.out.println("Introduce la cantidad a retirar (en m�ltiplos de 50): ");
				int cantidad = scanner.nextInt();
				if (cantidad % 50 != 0) {
					System.out.println("La cantidad debe ser un m�ltiplo de 50.");
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
				System.out.print("Introduce una opci�n: ");
				int opcionDepositar = scanner.nextInt();
				switch (opcionDepositar) {
				case 1:
					System.out.print("Introduce la cantidad a depositar (m�ltiplos de 50 y 100): ");
					int cantidadSumar = scanner.nextInt();
					if (cantidadSumar % 50 != 0) {
						System.out.println("La cantidad debe ser un m�ltiplo de 50 o 100.");
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
					System.out.println("Opci�n inv�lida.");
					break;
				}
				break;
			case 3:
				contadorErrores=0;
				System.out.println("La cantidad disponible en su cuenta es de: $" + dinero);
				break;
			case 4:
				contadorErrores=0;
				System.out.println("El sistema no est� disponible para quejas, intente mas tarde");
				break;
			case 5:
				contadorErrores=0;
				System.out.println("Seleccionaste la opci�n 5");
				System.out.println(guardarOperacion.get(guardarOperacion.size() - 1));

				break;
			case 7:
				contadorErrores=0;
				System.out.println("El horario de atencion es de 18:00 a 18:01");
				break;
			case 9:
				System.out.println("Adi�s!");
				break;
			default:
				contadorErrores++;
				System.out.println("Opci�n inv�lida. Intenta de nuevo.");

			}
			if (contadorErrores > 3) {
				System.out.println("\nDemasiados errores. El programa ha finalizado");
			}
		}
	}
}
