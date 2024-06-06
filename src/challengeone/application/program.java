package challengeone.application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import challengeone.entities.Sale;

public class program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Entre o caminho da arquivo: ");
		String path = sc.nextLine();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			
			List<Sale> sales = new ArrayList<>();
			
			String line = br.readLine();
			while(line != null) {
				String[] fields= line.split(",");
				Sale sale = new Sale(Integer.valueOf(fields[0]),Integer.valueOf(fields[1]),fields[2], Integer.valueOf(fields[3]) ,Double.valueOf(fields[4]));
				sales.add(sale);
				line = br.readLine();
			}
			
			
			System.out.println("\nTotal de vendas por vendedor:");
			
			Map<String, DoubleSummaryStatistics> result = sales.stream()
				.collect(Collectors.groupingBy(Sale::getSeller, Collectors.summarizingDouble(Sale::getTotal)));
		
			result.forEach((k,v)->System.out.println(k + " - R$ " + String.format("%.2f",v.getSum())));
	
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		sc.close();
	}
}
