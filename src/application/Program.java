package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		System.out.println("Enter rental data: ");
		System.out.print("Car model: ");
		String model = sc.nextLine();
		System.out.print("Pickup (dd/MM/yyyy hh:mm): ");
		LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
		System.out.print("Return (dd/MM/yyyy hh:mm): ");
		LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);
		
		CarRental carRental = new CarRental(start, finish, new Vehicle(model));
		
		System.out.print("Enter price per hour: ");
		double priceHour = sc.nextDouble();
		System.out.print("Enter price per day: ");
		double priceDay = sc.nextDouble();
		
		RentalService rentalService = new RentalService(priceDay, priceHour, new BrazilTaxService());
		
		rentalService.processInvoice(carRental);
		System.out.println();
		System.out.println("INVOICE: ");
		System.out.printf("Basic payment: %.2f%n", carRental.getInvoice().getBasicPayment());
		System.out.printf("Tax: %.2f%n", carRental.getInvoice().getTax());
		System.out.printf("Total payment: %.2f%n", carRental.getInvoice().getTotalPayment());
		
		sc.close();		
	}

}
